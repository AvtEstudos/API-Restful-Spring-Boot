package com.example.carros.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

	@Override
	//Quando executar o getAuthority retornará
	//o nome das nossas roles
	public String getAuthority() {
		// TODO Auto-generated method stub
		return nome;
	}    
}