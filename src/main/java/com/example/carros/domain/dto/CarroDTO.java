package com.example.carros.domain.dto;

import org.modelmapper.ModelMapper;

import com.example.carros.domain.Carro;
import lombok.Data;

@Data
public class CarroDTO {
	
	private Long id;		
	private String nome;	
	private String tipo;	
	
	//Com isso eliminamos a necessidade do construtor que 
	//transformava um carro em um CarroDTO 
	public static CarroDTO create(Carro c) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
		
	}

}
