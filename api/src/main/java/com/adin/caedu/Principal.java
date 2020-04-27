package com.adin.caedu;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.adin.caedu.jobs.Jobs_SFTPaccess;

@SpringBootApplication
@EnableScheduling
public class Principal {

	public static void main(String[] sdada) {
//		File path = new File(Settings.FOLDER_IDENTITY);
//		if (!path.exists())
//			path.mkdirs();
//		Jobs_SFTPtoOracleDB aux = new Jobs_SFTPtoOracleDB();
//		aux.setSftpConfigs();
		SpringApplication.run(Principal.class, sdada);
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("crm");
		dataSource.setPassword("caeducrm19");
		dataSource.setUrl("jdbc:oracle:thin:@129.213.149.115:1521/crm_iad1c2.crmlan.vcncrm.oraclevcn.com");
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		dataSource.setConnectionProperties(properties);
		return dataSource;
	}

	@Bean
	public Date date() {
		return new Date();
	}
}
