package com.dcyrillo.curmc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dcyrillo.curmc.services.DBServices;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBServices dbService;
	@Bean
	public boolean instanciateDatabase() throws ParseException {
		dbService.InstanciateTestDatabase();
		return true;
	}
}
