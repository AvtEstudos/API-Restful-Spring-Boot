package com.example.carros.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
public class Carro {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;	
	
	private String nome;	
	private String tipo;
	private String descricao;
	private String url_Foto;
	private String url_Video;
	private String latitude;
	private String longitude;
	

}
