package com.adin.caedu.jobs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adin.caedu.model.CED_CampaingListModel;
import com.adin.caedu.model.RetriveCampaings;
import com.adin.caedu.model.VW_AniversariantesModel;
import com.adin.caedu.model.VW_RetencaoModel;
import com.adin.caedu.recipients.AuthResponsysRecipient;
import com.adin.caedu.recipients.LoginResponsysRecipient;
import com.adin.caedu.repository.CED_CampaingResultRepository;
import com.adin.caedu.repository.VW_AniversariantesRepository;
import com.adin.caedu.repository.VW_RetencaoRepository;
import com.adin.caedu.services.DadosCampanhas;
import com.adin.caedu.services.WebService;
import com.adin.caedu.sett.ApplicationStatus;
import com.adin.caedu.sett.Settings;
import com.adin.caedu.util.GsonConverter;
import com.adin.internal_projects.sftp.SFTP;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.bean.comparator.LiteralComparator;
import com.adin.caedu.util.CustomMappingStrategy;

@Component
public class Jobs_SFTPaccess {

	@Autowired
	private CED_CampaingResultRepository ced_campainResult_repository;

	private String SFTP_USER_NAME = null;
	private String SFTP_HOST = null;
	private String SFTP_IDENTITY = null;
	private String SFTP_FOLDER_FILES = null;
	private String SFTP_DIV = null;
	private File storage_sftp = null;

	@Autowired
	private DadosCampanhas dadosCamapnhas;

	int idAtual = 0;
	int iderrors = 0;

	@Autowired
	private VW_AniversariantesRepository vw_aniversariantes_repository;

	@Autowired
	private VW_RetencaoRepository vw_retencao_repository;

	public String forceRetencaoClientes() {
		int retencaoCount = vw_retencao_repository.getCount();
		if (retencaoCount > 100) {
			retencaoCount = 100;
		}
		StringBuffer messageAux = new StringBuffer();
		messageAux.append("START_JOB >>> forceRetencao DB to SFTP on: ");
		messageAux.append(new Date());
		messageAux.append(" -> Total DB: ");
		messageAux.append(retencaoCount);
		ApplicationStatus.lastDBtoSFTP.add(messageAux.toString());
		System.out.println(messageAux);

		List<VW_RetencaoModel> retencao_dirty = vw_retencao_repository.getVWRetencao(0, retencaoCount);
		List<VW_RetencaoModel> retencao_clean = new ArrayList<VW_RetencaoModel>();
		List<String> cpf_clean = new ArrayList<String>();

		retencao_dirty.forEach(dirty -> {
			if (dirty != null) {
				if (!cpf_clean.contains(dirty.getCPF())) {
					cpf_clean.add(dirty.getCPF());
					dirty.setID_CAMPANHA(13);
					dirty.setDATA_INICIO(new SimpleDateFormat("dd/MM/yyyy")
							.format(new java.sql.Date(System.currentTimeMillis() + 86400000)));
					dirty.setDATA_FIM(new SimpleDateFormat("dd/MM/yyyy")
							.format(new java.sql.Date(System.currentTimeMillis() + 86400000 + (10 * 86400000))));
					dirty.setGENERO(dirty.getGENERO().equals("FEMININO") ? "1"
							: dirty.getGENERO().equals("MASCULINO") ? "0" : "2");

					String dataNascDB = dirty.getDATA_NASCIMENTO();
					String[] parts_1 = dataNascDB.split("-");
					String[] parts_2 = parts_1[2].split(" ");
					String dataNascFinal = parts_2[0] + "/" + parts_1[1] + "/" + parts_1[0];
					dirty.setDATA_NASCIMENTO(dataNascFinal);

					retencao_clean.add(dirty);
				}
			}
		});

		Date date = new Date();
		String name = "retencao";
		String ano = new SimpleDateFormat("yyyy").format(date);
		String mes = new SimpleDateFormat("MM").format(date);
		String dia = new SimpleDateFormat("dd").format(date);
		String minuto = new SimpleDateFormat("mm").format(date);
		String hora = String.valueOf(Integer.valueOf(new SimpleDateFormat("hh").format(date)) + 12);
		if (new SimpleDateFormat("aa").format(date).contains("AM"))
			hora = new SimpleDateFormat("hh").format(date);
		try {
			setSftpConfigs();
			String local_file_ = storage_sftp.getAbsolutePath() + "/" + name + ano + mes + dia + hora + minuto + ".csv";
			System.out.println("local_file_ " + local_file_);
			String remote_file = "campanhas_zanthus" + "/" + name + ano + mes + dia + hora + minuto + ".csv";
			Writer writer = new FileWriter(local_file_);
			CustomMappingStrategy<VW_RetencaoModel> mappingStrategy = new CustomMappingStrategy<>();
			mappingStrategy.setType(VW_RetencaoModel.class);

			StatefulBeanToCsv<VW_RetencaoModel> beanToCsv = new StatefulBeanToCsvBuilder<VW_RetencaoModel>(writer)
					.withSeparator(';').withMappingStrategy(mappingStrategy).withApplyQuotesToAll(false).build();
			beanToCsv.write(retencao_clean);
			writer.close();
			FileReader reader = new FileReader(local_file_);
			BufferedReader buff = new BufferedReader(reader);
			String text = "";
			while (buff.ready()) {
				String line = buff.readLine();
				if (!line.isEmpty())
					text += line + "\n";
			}

			text = text.substring(0, text.length() - 1);
			buff.close();
			reader.close();
			FileWriter new_writer = new FileWriter(local_file_);
			new_writer.write(text);
			new_writer.close();

			final SFTP sftp = new SFTP(SFTP_IDENTITY, SFTP_USER_NAME, SFTP_HOST);

			if (sftp.openSession()) {
				if (sftp.openChannel()) {
					// sftp.put(local_file_, remote_file);
					messageAux = new StringBuffer();
					messageAux.append("Arquivo retencao colocado no diretorio -> ");
					messageAux.append(remote_file);
					ApplicationStatus.lastDBtoSFTP.add(messageAux.toString());
					System.out.println(messageAux.toString());
					sftp.closeChannel();
				}
				sftp.closeChannel();
				sftp.closeSession();
			}

			sftp.closeSession();

		} catch (Exception e) {
			System.out.println("ERROR CONVERT " + e.getMessage());
		}

		messageAux = new StringBuffer();
		messageAux.append("END_JOB >>> forceRetencao DB to SFTP on: ");
		messageAux.append(new Date());
		messageAux.append(" -> Total SFTP: ");
		messageAux.append(retencao_clean.size());
		ApplicationStatus.lastDBtoSFTP.add(messageAux.toString());

		return "{\"response\" : \"Job terminou com sucesso - OK\"}";
	}

	public String forceAniversariantes() {
		int aniversariantesCount = vw_aniversariantes_repository.getCount();
		System.out.println("Count aniversariantes: " + aniversariantesCount);
		if (aniversariantesCount > 10000) {
			aniversariantesCount = 10000;
		}

		StringBuffer messageAux = new StringBuffer();
		messageAux.append("START_JOB >>> forceAniversariantes DB to SFTP on: ");
		messageAux.append(new Date());
		messageAux.append(" -> Total DB: ");
		messageAux.append(aniversariantesCount);
		ApplicationStatus.lastDBtoSFTP.add(messageAux.toString());
		System.out.println(messageAux);

		List<VW_AniversariantesModel> aniversariantes_dirty = vw_aniversariantes_repository.getVWAniversariantes(0,
				aniversariantesCount);

		List<VW_AniversariantesModel> aniversariantes_clean = new ArrayList<VW_AniversariantesModel>();
		List<String> cpf_clean = new ArrayList<String>();

		aniversariantes_dirty.forEach(dirty -> {
			if (dirty.getCPF() != null && dirty.getCPF().length() <= 15 && dirty.getNOME_COMPLETO() != null
					&& dirty.getEMAIL().length() <= 255) {
				if (!cpf_clean.contains(dirty.getCPF())) {
					cpf_clean.add(dirty.getCPF());
					dirty.setID_CAMPANHA(25);
					dirty.setDATA_INICIO(new SimpleDateFormat("dd/MM/yyyy")
							.format(new java.sql.Date(System.currentTimeMillis() + 86400000)));
					dirty.setDATA_FIM(new SimpleDateFormat("dd/MM/yyyy")
							.format(new java.sql.Date(System.currentTimeMillis() + 86400000 + (10 * 86400000))));

					String nome = dirty.getNOME_COMPLETO();
					dirty.setNOME_COMPLETO(nome.length() <= 44 ? nome : nome.substring(0, 44));

					if (dirty.getGENERO() != null) {
						String genero = dirty.getGENERO().equals("FEMININO") ? "1"
								: dirty.getGENERO().equals("MASCULINO") ? "0" : "2";
						dirty.setGENERO(genero);
					}

					String dataNascDB = dirty.getDATA_NASCIMENTO();
					String[] parts_1 = dataNascDB.split("-");
					String[] parts_2 = parts_1[2].split(" ");
					String dataNascFinal = parts_2[0] + "/" + parts_1[1] + "/" + parts_1[0];
					dirty.setDATA_NASCIMENTO(dataNascFinal);

					if (dirty.getDdd_Telefone_Fixo() != null) {
						String dddTel = dirty.getDdd_Telefone_Fixo();
						dirty.setDdd_Telefone_Fixo(dddTel.length() <= 20 ? dddTel : dddTel.substring(0, 20));
					}

					if (dirty.getTELEFONE_RESIDENCIAL() != null) {
						String telFixo = dirty.getTELEFONE_RESIDENCIAL();
						dirty.setTELEFONE_RESIDENCIAL(telFixo.length() <= 20 ? telFixo : telFixo.substring(0, 20));
					}

					if (dirty.getTELEFONE_CELULAR_DDD() != null) {
						String dddCel = dirty.getTELEFONE_CELULAR_DDD();
						dirty.setTELEFONE_CELULAR_DDD(dddCel.length() <= 15 ? dddCel : dddCel.substring(0, 15));
					}

					if (dirty.getTELEFONE_CELULAR() != null) {
						String telCel = dirty.getTELEFONE_CELULAR();
						dirty.setTELEFONE_CELULAR(telCel.length() <= 15 ? telCel : telCel.substring(0, 15));
					}

					if (dirty.getCEP() != null) {
						String cep = dirty.getCEP();
						dirty.setCEP(cep.length() <= 10 ? cep : cep.substring(0, 10));
					}

					if (dirty.getLogradouro() != null) {
						String logradouro = dirty.getLogradouro();
						dirty.setLogradouro(logradouro.length() <= 60 ? logradouro : logradouro.substring(0, 60));
					}

					if (dirty.getNumero() != null) {
						String numero = dirty.getNumero();
						dirty.setNumero(numero.length() <= 20 ? numero : numero.substring(0, 20));
					}

					if (dirty.getComplemento() != null) {
						String complemento = dirty.getComplemento();
						dirty.setComplemento(complemento.length() <= 20 ? complemento : complemento.substring(0, 30));
					}

					if (dirty.getBairro() != null) {
						String bairro = dirty.getBairro();
						dirty.setBairro(bairro.length() <= 32 ? bairro : bairro.substring(0, 32));
					}

					if (dirty.getCidade() != null) {
						String cidade = dirty.getCidade();
						dirty.setCidade(cidade.length() <= 44 ? cidade : cidade.substring(0, 44));
					}

					if (dirty.getUf() != null) {
						String uf = dirty.getUf();
						dirty.setUf(uf.length() <= 2 ? uf : uf.substring(0, 2));
					}

					aniversariantes_clean.add(dirty);
				}
			}
		});

		Date date = new Date();
		String name = "aniversariantes";
		String ano = new SimpleDateFormat("yyyy").format(date);
		String mes = new SimpleDateFormat("MM").format(date);
		String dia = new SimpleDateFormat("dd").format(date);
		String minuto = new SimpleDateFormat("mm").format(date);
		String hora = String.valueOf(Integer.valueOf(new SimpleDateFormat("hh").format(date)) + 12);
		if (new SimpleDateFormat("aa").format(date).contains("AM"))
			hora = new SimpleDateFormat("hh").format(date);
		try {
			setSftpConfigs();
			String local_file_ = storage_sftp.getAbsolutePath() + "/" + name + ano + mes + dia + hora + minuto + ".csv";
			String remote_file = "campanhas_zanthus" + "/" + name + ano + mes + dia + hora + minuto + ".csv";
			Writer writer = new FileWriter(local_file_);

			CustomMappingStrategy<VW_AniversariantesModel> mappingStrategy = new CustomMappingStrategy<>();
			mappingStrategy.setType(VW_AniversariantesModel.class);

			StatefulBeanToCsv<VW_AniversariantesModel> beanToCsv = new StatefulBeanToCsvBuilder<VW_AniversariantesModel>(
					writer).withSeparator(';').withMappingStrategy(mappingStrategy).withApplyQuotesToAll(false).build();
			beanToCsv.write(aniversariantes_clean);
			writer.close();
			FileReader reader = new FileReader(local_file_);
			BufferedReader buff = new BufferedReader(reader);
			String text = "";
			while (buff.ready()) {
				String line = buff.readLine();
				if (!line.isEmpty())
					text += line + "\n";
			}

			text = text.substring(0, text.length() - 1);
			buff.close();
			reader.close();
			FileWriter new_writer = new FileWriter(local_file_);
			new_writer.write(text);
			new_writer.close();

			final SFTP sftp = new SFTP(SFTP_IDENTITY, SFTP_USER_NAME, SFTP_HOST);

			if (sftp.openSession()) {
				if (sftp.openChannel()) {
					sftp.put(local_file_, remote_file);
					messageAux = new StringBuffer();
					messageAux.append("Arquivo aniversariantes colocado no diretorio -> ");
					messageAux.append(remote_file);
					ApplicationStatus.lastDBtoSFTP.add(messageAux.toString());
					sftp.closeChannel();
				}
				sftp.closeChannel();
				sftp.closeSession();
			}

			sftp.closeSession();

		} catch (Exception e) {
			System.out.println("ERROR CONVERT " + e.getMessage());
		}

		messageAux = new StringBuffer();
		messageAux.append("END_JOB >>> forceAniversariantes DB to SFTP on: ");
		messageAux.append(new Date());
		messageAux.append(" -> Total SFTP: ");
		messageAux.append(aniversariantes_clean.size());
		ApplicationStatus.lastDBtoSFTP.add(messageAux.toString());
		return "{\"response\" : \"Job terminou com sucesso - OK\"}";
	}

	public String jobCEDfilesToDB(String data) {
		gerarLog(true, "START_JOB >>> jobCEDfilesToDB", "");
		setSftpConfigs(); // Setar SFTP configs
		dadosCamapnhas.getDadosCampanhas(); // Pegar dados de campanhas
		iderrors = 0;

		final SFTP sftp = new SFTP(SFTP_IDENTITY, SFTP_USER_NAME, SFTP_HOST);
		String vet[] = { "OPEN", "CLICK", "BOUNCE", "LAUNCH_STATE", "SENT", "COMPLAINT" };
		for (int i = 0; i < vet.length; i++) {
			if (sftp.openSession()) {
				if (sftp.openChannel()) {
					try {
						String remote_path = "download/CED";
						gerarLog(true, "START_JOB >>> jobCEDfilesToDB", "Arquivo -> " + vet[i] + " Dia -> " + data);
						List<String> listFiles = sftp.listFiles(remote_path);
						File file_ = null;
						idAtual = ced_campainResult_repository.lastId();
						for (String file : listFiles) {
							if (file.toUpperCase().contains(vet[i])) {
								String splited;
								if (vet[i].equals("LAUNCH_STATE")) {
									splited = file.split("_")[3];
								} else {
									splited = file.split("_")[2];
								}

								if (splited.contains(data)) {
									String local_file = storage_sftp + "/" + file;
									sftp.get(remote_path + "/" + file, local_file);
									file_ = new File(local_file);
									sendFileToDB(file_, file);
								}
							}
						}
						sftp.closeChannel();
						sftp.closeSession();
					} catch (Exception e) {
						gerarLog(true, "ERRO_____ >>> jobCEDfilesToDB", "Erro ao executar servico \n" + e.getMessage());
						sftp.closeChannel();
						sftp.closeSession();
					}
				}
			}
			sftp.closeChannel();
			sftp.closeSession();
		}

		sftp.closeSession();
		return "{\"response\" : \"Job terminou com sucesso - OK\"}";
	}

	public void sendFileToDB(File file_, String fileName) {
		try {
			FileReader fileReader = new FileReader(file_);
			List<CED_CampaingListModel> beans = new CsvToBeanBuilder<CED_CampaingListModel>(fileReader)
					.withSeparator('	').withType(CED_CampaingListModel.class).build().parse();
			gerarLog(true, "START_JOB >>> jobCEDfilesToDB",
					"START sendFileToDB --> Arquivo: " + fileName + " Size: " + beans.size() + " ID first: " + idAtual);
			dadosCamapnhas.setCampaingsSubject(beans);

			beans.forEach(o -> {
				try {
					idAtual++; // = idAtual.add(new BigInteger("1"));
					o.setId(idAtual);
					ced_campainResult_repository.saveAndFlush(o);
				} catch (Exception e) {
					iderrors++;
					gerarLog(false, "jobCEDfilesToDB", "sendFileToDB --> Erro foreach: \n" + e.getMessage());
				}
			});
			gerarLog(true, "END___JOB >>> jobCEDfilesToDB",
					"END sendFileToDB --> Arquivo: " + fileName + " Size: " + beans.size() + " ID last: " + idAtual);
		} catch (Exception e) {
			gerarLog(true, "ERRO_____ >>> jobCEDfilesToDB",
					"sendFileToDB --> Erro: Erro convert to CSV \n" + e.getMessage());
		}
	}

	public void setSftpConfigs() {
		SFTP_USER_NAME = "caeducome_scp";
		SFTP_HOST = "sftp.rsys9.net";
		SFTP_DIV = System.getProperty("file.separator");


// 		--------------------- Acesso SERVIDOR
//		SFTP_IDENTITY = "C:\\Users\\ADIN\\Desktop\\testes\\sftp_privateKey.pem";
//		SFTP_FOLDER_FILES = "C:\\Users\\ADIN\\Desktop\\testes\\sftp_files";

		SFTP_IDENTITY = "/root/caeduAUX/v9/sftp_privateKey.pem";
		SFTP_FOLDER_FILES = "/root/caeduAUX/v9/sftp_files";

		storage_sftp = new File(SFTP_FOLDER_FILES);
	}

	public void gerarLog(boolean saveOnLog, String nameJob, String message) {
		StringBuffer messageAux = new StringBuffer();
		messageAux.append("START_JOB >>> ");
		messageAux.append(nameJob);
		messageAux.append(" to RSYS: on ");
		messageAux.append(new Date());
		messageAux.append(message);

		System.out.println(messageAux);
		if (saveOnLog) {
			ApplicationStatus.lastDBtoResponsys.add(messageAux.toString());
		}
	}
}
