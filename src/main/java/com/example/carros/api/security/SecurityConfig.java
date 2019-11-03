package com.example.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Como é uma classe de configuração ativamos a anotação @Configuration 
@Configuration
//Habilitando a segurança via Web
@EnableWebSecurity
//	Para utilizar a anotação @Secured nos serviços, 
//devemos devemos usar essa anotação
@EnableGlobalMethodSecurity(securedEnabled = true)
//Extendendo a classe de configuração do spring
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	//Definindo a classe 
	@Qualifier("userDatailsService")
	private UserDetailsService UserDetailsService; 
	
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
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 	A implementação padrão apenas retorna true, mas na documentação
		// existe uma sugestão de implementação (Copiada abaixo)
		//super.configure(auth);
		
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		
		auth.userDetailsService(UserDetailsService).passwordEncoder(enconder);		
		
		/*
		auth	 	
	 	.inMemoryAuthentication().passwordEncoder(enconder)
	 		.withUser("user").password(enconder.encode("user")).roles("USER")
	 		.and()
	 		.withUser("admin").password(enconder.encode("admin")).roles("USER", "ADMIN");
	 	*/
	}
	
}