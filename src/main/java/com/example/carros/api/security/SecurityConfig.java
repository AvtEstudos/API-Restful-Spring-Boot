package com.example.carros.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//Como é uma classe de configuração ativamos a anotação @Configuration 
@Configuration
//Habilitando a segurança via Web
@EnableWebSecurity
//Extendendo a classe de configuração do spring
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//.anyRequest().authenticated(): Define que todo request 
		//                               deverá ser autenticado.		
		//.formLogin().and(): Com a retirada dessa linha não 
		//                    teremos a tela de login
		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and().httpBasic()
				.and().csrf().disable();			
	}
}