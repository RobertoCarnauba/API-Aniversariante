package com.adin.caedu.jobs;

import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adin.caedu.model.CED_CampaingListModel;
import com.adin.caedu.model.DB_DimRFVModel;
import com.adin.caedu.model.VW_ContactList;
import com.adin.caedu.model.VW_InadimplentesModel;
import com.adin.caedu.model.DB_VucModel;
import com.adin.caedu.model.TesteExcel;
import com.adin.caedu.model.VW_AniversariantesModel;
import com.adin.caedu.repository.DB_DIMRFVRepository;
import com.adin.caedu.repository.VWContactListRepository;
import com.adin.caedu.repository.VW_AniversariantesRepository;
import com.adin.caedu.repository.VW_InadimplentesRepository;
import com.adin.caedu.repository.DB_VucRepository;
import com.adin.caedu.services.SendContactListService;
import com.adin.caedu.services.SendDimRFVService;
import com.adin.caedu.services.SendInadimplentes;
import com.adin.caedu.services.SendVucContacts;
import com.adin.caedu.services.SendVuc;
import com.adin.caedu.sett.ApplicationStatus;
import com.adin.caedu.sett.EmailSettings;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class Jobs_DBtoResponsys {

	@Autowired
	public VWContactListRepository vw_contactList_repository;

	@Autowired
	private DB_DIMRFVRepository db_dimrfv_repository;

	@Autowired
	private VW_InadimplentesRepository vw_inadimplentes_repository;

	@Autowired
	private DB_VucRepository vw_vuc_repository;

	@Autowired
	private SendContactListService sendList;

	@Autowired
	private SendDimRFVService sendRfv;

	@Autowired
	private SendInadimplentes sendInadinplentes;

	@Autowired
	private SendVucContacts sendVucContacts;

	@Autowired
	private SendVuc sendVuc;

	public String forceVucContacts(int start) {
		return baseJob("force_VUC_Contacts", vw_vuc_repository.getCount(), start);
	}

	public String forceVuc(int start) {
		return baseJob("force_VUC", vw_vuc_repository.getCount(), start);
	}

	public String forceContacts() {
		return baseJob("force_CONTACTS", vw_contactList_repository.getCount(), 0);
	}

	public String forceInadimplentes(int start) {
		return baseJob("force_INADIMPLENTES", vw_inadimplentes_repository.getCount(), start);
	}

	public String forceDimRFV(int start) {
		return baseJob("force_RFV", db_dimrfv_repository.getCount(), start);
	}

	public void coreJobVucContacts(int initialValue, int endValue) {
		List<DB_VucModel> vucdatas = vw_vuc_repository.getVWVuc(initialValue, endValue);

		vucdatas.forEach(c -> {
			sendVucContacts.add(c);
		});

		if (sendVucContacts.getContacts().size() > 0)
			sendVucContacts.getUp();

		vucdatas.clear();
	}

	public void coreJobContacts(int initialValue, int endValue) {
		List<VW_ContactList> contacts = vw_contactList_repository.getVWContactList(initialValue, endValue);
		System.out.println(
				" -------> START_coreJobContacts ready to job >>> on: " + new Date() + " -> Size: " + contacts.size());
		contacts.forEach(c -> {
			sendList.add(c);
		});

		if (sendList.getContacts().size() > 0)
			sendList.getUp();

		sendList.saveRiids(vw_contactList_repository);

		System.out
				.println("\n\n -------> END___coreJobContacts >>> on: " + new Date() + " -> Size: " + contacts.size());
	}

	public void coreJobDimRFV(int initialValue, int endValue) {
		List<DB_DimRFVModel> dimRFV = db_dimrfv_repository.getDBDimRFV(initialValue, endValue);
		dimRFV.forEach(d -> {
			sendRfv.add(d);
		});

		if (sendRfv.getDimRFV().size() > 0)
			sendRfv.getUp();

	}

	public void coreJobInadimplentes(int initialValue, int endValue) {
		List<VW_InadimplentesModel> inadimplentes = vw_inadimplentes_repository.getVWInabimplentes(initialValue,
				endValue);

		inadimplentes.forEach(i -> {
			sendInadinplentes.add(i);
		});

		if (sendInadinplentes.getInadimplentes().size() > 0)
			sendInadinplentes.getUp();
	}

	public void coreJobVuc(int initialValue, int endValue) {
		List<DB_VucModel> vucs = vw_vuc_repository.getVWVuc(initialValue, endValue);
		vucs.forEach(i -> {
			sendVuc.add(i);
		});

		if (sendVuc.getVucs().size() > 0)
			sendVuc.getUp();
	}

	public String baseJob(String nameJob, int totalCount, int start) {
		System.out.println("\n\n " + nameJob + " started " + new Date());
		int range = 10000;
		int initialValue = 1;
		int finalValue = range < totalCount ? range : totalCount;
		int rangeValue = finalValue;

		int runsLimit = (totalCount / range) + 1;

		for (int i = 0; i < runsLimit; i++) {
			if (i >= start) {
				try {
					StringBuffer messageAux = new StringBuffer();
					messageAux.append("START_JOB >>> ");
					messageAux.append(nameJob);
					messageAux.append(" to RSYS: on ");
					messageAux.append(new Date());
					messageAux.append(" -> Start value: ");
					messageAux.append(initialValue);
					messageAux.append(" -> Rang: ");
					messageAux.append(rangeValue);
					messageAux.append(" | Last value: ");
					messageAux.append(totalCount);

					System.out.println(messageAux);
					ApplicationStatus.lastDBtoResponsys.add(messageAux.toString());

					if (nameJob.contentEquals("force_VUC_Contacts")) {
						coreJobVucContacts(initialValue, finalValue);
					} else if (nameJob.contentEquals("force_CONTACTS")) {
						coreJobContacts(initialValue, finalValue);
					} else if (nameJob.contentEquals("force_VUC")) {
						coreJobVuc(initialValue, finalValue);
					} else if (nameJob.contentEquals("force_INADIMPLENTES")) {
						coreJobInadimplentes(initialValue, finalValue);
					} else if (nameJob.contentEquals("force_RFV")) {
						coreJobDimRFV(initialValue, finalValue);
					}

					messageAux = new StringBuffer();
					messageAux.append("END___JOB >>> ");
					messageAux.append(nameJob);
					messageAux.append(" -> Start value: ");
					messageAux.append(initialValue);
					messageAux.append(" | end value: ");
					messageAux.append(finalValue);
					messageAux.append(" -> Rang: ");
					messageAux.append(rangeValue);
					messageAux.append(" | Last value: ");
					messageAux.append(totalCount);

					System.out.println(messageAux.toString());
					ApplicationStatus.lastDBtoResponsys.add(messageAux.toString());

				} catch (Exception e) {
					String message = "Jobs_DBtoResponsys -> " + nameJob + " -> Erro base job, intervalo: " + i
							+ "total: " + runsLimit + " Erro ao tentar PEGAR, ENVIAR ou SALVAR. \n";
					e.printStackTrace();
					System.out.println(message);
					sendEmail(message);
				}
			}
			initialValue = finalValue;
			finalValue = (finalValue + rangeValue) < totalCount ? (finalValue + rangeValue) : totalCount;
		}

		return "Job " + nameJob + " terminou. Time: " + new Date();
	}

//	public String forceVuc() {
//		System.out.println("\n\n forceVuc started " + new Date());
//		int range = 5000;
//		int totalCount = vw_vuc_repository.getCount();
//		int initialValue = 1;
//		int endValue = range < totalCount ? range : totalCount;
//		int rangeValue = endValue;
//
//		boolean continuar = true;
//		int runs = 0;
//		int runsLimit = (totalCount / range) + 3;
//		while (continuar && runs <= runsLimit) {
//			try {
//				String mensagemAux = "START_JOB >>> VUC VW to RSYS: on " + new Date() + " -> Start value: "
//						+ initialValue + " | end value: " + endValue + " -> Rang: " + rangeValue + " | Last value: "
//						+ totalCount;
//
//				System.out.println(mensagemAux);
//				ApplicationStatus.lastSizeDBtoRsys.add(mensagemAux);
//
//				List<DB_VucModel> vucs = vw_vuc_repository.getVWVuc(initialValue, endValue);
//
//				System.out.println("vucs SIZE " + vucs.size());
//				vucs.forEach(i -> {
//					sendVuc.add(i);
//				});
//
//				if (sendVuc.getVucs().size() > 0)
//					sendVuc.getUp();
//
//				mensagemAux = "END___JOB >>> VUC VW to RSYS: on " + new Date() + " -> Start value: " + initialValue
//						+ " | end value: " + endValue + " -> Rang: " + rangeValue + " | Last value: " + totalCount;
//
//				ApplicationStatus.lastSizeDBtoRsys.add(mensagemAux);
//				System.out.println(mensagemAux);
//
//				initialValue = endValue;
//				endValue = (endValue + rangeValue) < totalCount ? (endValue + rangeValue) : totalCount;
//				if (initialValue >= endValue) {
//					continuar = false;
//				}
//			} catch (Exception e) {
//				System.out.println(
//						"Jobs_DBtoResponsys -> forceVuc -> Erro ao tentar PEGAR, ENVIAR ou SALVAR inadimplentes. \n"
//								+ e);
//				sendEmail(e.getMessage());
//			}
//			runs++;
//		}
//		return "{\"response\" : ok}";
//	}

//	public String forceInadimplentes() {
//		System.out.println("\n\n forceInadimplentes started " + new Date());
//		int range = 5000;
//		int totalCount = vw_inadimplentes_repository.getCount();
//		int initialValue = 1;
//		int endValue = range < totalCount ? range : totalCount;
//		int rangeValue = endValue;
//
//		boolean continuar = true;
//		int runs = 0;
//		int runsLimit = (totalCount / range) + 3;
//
//		while (continuar && runs <= runsLimit) {
//			try {
//				String mensagemAux = "START_JOB >>> Inadimplentes VW to RSYS: on " + new Date() + " -> Start value: "
//						+ initialValue + " | end value: " + endValue + " -> Rang: " + rangeValue + " | Last value: "
//						+ totalCount;
//
//				System.out.println(mensagemAux);
//				ApplicationStatus.lastSizeDBtoRsys.add(mensagemAux);
//
//				List<VW_InadimplentesModel> inadimplentes = vw_inadimplentes_repository.getVWInabimplentes(initialValue,
//						endValue);
//
//				System.out.println("inadimplentes SIZE " + inadimplentes.size());
//				inadimplentes.forEach(i -> {
//					if (i.getEmail() != null) {
//						if (i.getEmail().contains("@")) {
//							sendInadinplentes.add(i);
//						}
//					}
//				});
//
//				if (sendInadinplentes.getInadimplentes().size() > 0)
//					sendInadinplentes.getUp();
//
//				mensagemAux = "END___JOB >>> Inadimplentes VW to RSYS: on " + new Date() + " -> Start value: "
//						+ initialValue + " | end value: " + endValue + " -> Rang: " + rangeValue + " | Last value: "
//						+ totalCount;
//
//				ApplicationStatus.lastSizeDBtoRsys.add(mensagemAux);
//
//				System.out.println(mensagemAux);
//
//				initialValue = endValue;
//				endValue = (endValue + rangeValue) < totalCount ? (endValue + rangeValue) : totalCount;
//				if (initialValue >= endValue) {
//					continuar = false;
//				}
//			} catch (Exception e) {
//				System.out.println(
//						"Jobs_DBtoResponsys -> forceInadimplentes -> Erro ao tentar PEGAR, ENVIAR ou SALVAR inadimplentes. \n"
//								+ e);
//				sendEmail(e.getMessage());
//			}
//			runs++;
//		}
//		return "{\"response\" : ok}";
//	}

//	public String forceContacts() {
//		try {
//			System.out.println("\n\n forceContacts started " + new Date());
//
//			int range = 100;
//			int totalCount = vw_contactList_repository.getCount();
//			int initialValue = 1;
//			int endValue = range < totalCount ? range : totalCount;
//			int rangeValue = endValue;
//
//			boolean continuar = true;
//			int runs = 0;
//			int runsLimit = (totalCount / range) + 3;
//
//			while (continuar && runs <= runsLimit) {
//				try {
//
//					String mensagemAux = "START_JOB >>> Contacts DB to RSYS: on " + new Date() + " -> Start value: "
//							+ initialValue + " | end value: " + endValue + " -> Rang: " + rangeValue + " | Last value: "
//							+ totalCount;
//
//					System.out.println(mensagemAux);
//					ApplicationStatus.lastSizeDBtoRsys.add(mensagemAux);
//
//					List<VW_ContactList> contacts = vw_contactList_repository.getVWContactList(initialValue, endValue);
//
//					System.out.println("Force contacts " + contacts.size());
//
//					contacts.forEach(c -> {
//						System.out.println("C -> " + c.toString());
////						sendList.add(c);
//					});
//
////					if (sendList.getContacts().size() > 0)
////						sendList.getUp();
////
////					sendList.saveRiids(vw_contactList_repository);
//
//					mensagemAux = "END___JOB >>> Contacts DB to RSYS: on " + new Date() + " -> Start value: "
//							+ initialValue + " | end value: " + endValue + " -> Rang: " + rangeValue + " | Last value: "
//							+ totalCount;
//
//					ApplicationStatus.lastSizeDBtoRsys.add(mensagemAux);
//					System.out.println(mensagemAux);
//					initialValue = endValue;
//					endValue = (endValue + rangeValue) < totalCount ? (endValue + rangeValue) : totalCount;
//					if (initialValue >= endValue) {
//						continuar = false;
//					}
//					continuar = false;
//				} catch (Exception e) {
//					System.out.println("Jobs_DBtoResponsys -> forceContacts -> Erro ao tentar salvar contatos \n" + e);
//					sendEmail("WHILE   -> " + e.getMessage());
//				}
//				runs++;
//			}
//
//		} catch (Exception e) {
//			System.out.println(
//					"Jobs_VWtoResponsys -> forceContacts -> Erro ao tentar PEGAR, ENVIAR ou SALVAR contatos. \n" + e);
//			sendEmail(e.getMessage());
//		}
//		return "{\"response\" : ok}";
//	}

//	public String forceDimRFV() {
//		try {
//			System.out.println("\n\n forceDimRFV started " + new Date());
//			List<DB_DimRFVModel> dimRFV = db_dimrfv_repository.getDBDimRFV();
//
//			dimRFV.forEach(d -> {
//				sendRfv.add(d);
//				System.out.println("D -> " + d.toString());
//			});
//
//			System.out.println("sendRfv.getDimRFV().size() " + sendRfv.getDimRFV().size());
//
//			if (sendRfv.getDimRFV().size() > 0)
//				sendRfv.getUp();
//
//		} catch (Exception e) {
//			System.out.println(
//					"Jobs_DBtoResponsys -> forceDimRFV -> Erro ao tentar PEGAR, ENVIAR ou SALVAR DIMRFV. \n" + e);
//			sendEmail(e.getMessage());
//		}
//		return "{\"response\" : ok}";
//	}

	public String sendEmail(String message) {
		try {
			Email email = EmailBuilder.startingBlank().from(EmailSettings.name, EmailSettings.email)
					.to("Roberto", "roberto.carnauba@claravista.com.br").withSubject("[CAEDU] ERRO API").withPlainText(message)
					.buildEmail();

			MailerBuilder
					.withTransportStrategy(TransportStrategy.SMTP_TLS).withSMTPServer(EmailSettings.server,
							EmailSettings.port, EmailSettings.email, EmailSettings.password)
					.buildMailer().sendMail(email);
		} catch (Exception e) {
			System.out.println("\n\n\n\n EMAIL ---> Jobs_DBtoResponsys -> Email Failed \n\n\n\n" + e);
		}

		return "{\"response\" : \"OK\"}";
	}
}