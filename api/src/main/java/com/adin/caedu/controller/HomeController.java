package com.adin.caedu.controller;

import java.text.SimpleDateFormat;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adin.caedu.jobs.Jobs_SFTPaccess;
import com.adin.caedu.jobs.Jobs_DBtoResponsys;
import com.adin.caedu.sett.*;

@Controller
public class HomeController {

	@Autowired
	Jobs_DBtoResponsys jobsDBtoRpsys;

	@Autowired
	Jobs_SFTPaccess jobsSFTPtoOracleDB;

	@Scheduled(cron = "0 0 2 * * *")
	@RequestMapping("forceDBtoRSYS")
	public String forceDBtoRSYS() {
		System.out.println("\n \n \n \n \n ----------------- START JOB ----------------- \n \n \n \n \n");
		ApplicationStatus.resetDBtoResponsys();
		StringBuffer messageAux = new StringBuffer();
		// messageAux.append(forceContacts(0));
		messageAux.append(forceVUC(0));
		messageAux.append(forceInadimplentes(0));
		// messageAux.append(forceRFV(0));
		String today = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis() - (1 * 24 * 60 * 60 * 1000));
		messageAux.append(forceCED(today));
		System.out.println(messageAux.toString());
		return "{\"response\" : " + messageAux.toString() + "}";
	}

	@Scheduled(cron = "0 0 16 * * *")
	@RequestMapping("startDBtoSFTP")
	public String startDBtoSFTP() {
		ApplicationStatus.resetDBtoSFTP();
		StringBuffer messageAux = new StringBuffer();
		messageAux.append(forceAniversariantes());
		// messageAux.append(forceRetencaoClientes());
		System.out.println(messageAux.toString());
		return "{\"response\" : " + messageAux.toString() + "}";
	}

	@RequestMapping("forceVucContacts/{start}")
	@ResponseBody
	public String forceContacts(@PathVariable(value = "start") int start) {
		try {
			return "Sucesso endpoint forceVucContacts: " + jobsDBtoRpsys.forceVucContacts(start);
		} catch (Exception e) {
			sendEmail("Erro forceVucContacts: " + e.getMessage());
			return "Erro endpoint forceVucContacts: " + e.getMessage();
		}
	}

	@RequestMapping("forceVUC/{start}")
	@ResponseBody
	public String forceVUC(@PathVariable(value = "start") int start) {
		try {
			return "Sucesso endpoint forceVUC: " + jobsDBtoRpsys.forceVuc(start);
		} catch (Exception e) {
			sendEmail("Erro forceVUC: " + e.getMessage());
			return "Erro endpoint forceVUC: " + e.getMessage();
		}
	}

	@RequestMapping("forceInadimplentes/{start}")
	@ResponseBody
	public String forceInadimplentes(@PathVariable(value = "start") int start) {
		try {
			return "Sucesso endpoint forceInadimplentes: " + jobsDBtoRpsys.forceInadimplentes(start);
		} catch (Exception e) {
			sendEmail("Erro forceInadimplentes: " + e.getMessage());
			return "Erro endpoint forceInadimplentes: " + e.getMessage();
		}
	}

	@RequestMapping("forceRFV/{start}")
	@ResponseBody
	public String forceRFV(@PathVariable(value = "start") int start) {
		try {
			return "Sucesso endpoint forceDimRFV: " + jobsDBtoRpsys.forceDimRFV(start);
		} catch (Exception e) {
			sendEmail("Erro forceRFV: " + e.getMessage());
			return "Erro endpoint forceRFV: " + e.getMessage();
		}
	}

	@RequestMapping("forceCED/{data}")
	@ResponseBody
	public String forceCED(@PathVariable(value = "data") String data) {
		ApplicationStatus.resetCEDtoDB();
		try {
			return "Sucesso endpoint forceCED: " + jobsSFTPtoOracleDB.jobCEDfilesToDB(data);
		} catch (Exception e) {
			sendEmail("Erro forceCED: " + e.getMessage());
			return "Erro endpoint forceCED: " + e.getMessage();
		}
	}

	@RequestMapping("forceAniversariantes")
	@ResponseBody
	public String forceAniversariantes() {
		try {
			return "Sucesso endpoint forceAniversariantes: " + jobsSFTPtoOracleDB.forceAniversariantes();
		} catch (Exception e) {
			sendEmail("Erro forceAniversariantes: " + e.getMessage());
			return "Erro endpoint forceAniversariantes: " + e.getMessage();
		}
	}

	@RequestMapping("forceRetencaoClientes")
	@ResponseBody
	public String forceRetencaoClientes() {
		try {
			return "Sucesso endpoint forceRetencaoClientes: " + jobsSFTPtoOracleDB.forceRetencaoClientes();
		} catch (Exception e) {
			sendEmail("Erro forceRetencaoClientes: " + e.getMessage());
			return "Erro endpoint forceRetencaoClientes: " + e.getMessage();
		}
	}

	@RequestMapping("status")
	@ResponseBody
	public String applicationStatus() {
		return ApplicationStatus.getToString();
	}

	public String sendEmail(String message) {

		Email email = EmailBuilder.startingBlank().from(EmailSettings.name, EmailSettings.email)
				.to("Isac", "isac.junior@adin.com.br").withSubject("[CAEDU] API ERRO").withPlainText(message)
				.buildEmail();

		MailerBuilder.withTransportStrategy(TransportStrategy.SMTP_TLS)
				.withSMTPServer(EmailSettings.server, EmailSettings.port, EmailSettings.email, EmailSettings.password)
				.buildMailer().sendMail(email);

		return "{\"response\" : \"OK\"}";
	}

}