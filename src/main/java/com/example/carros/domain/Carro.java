package com.example.carros.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Mapeamento da classe com a tabela
//name opcional quando nome da tabela e classe forem iguais
@Entity(name = "carro")
public class Carro {
	
	//Indicando campo chave da tabela
	@Id
	//Definindo campo como auto incremento
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//name opcional quando nome da coluna for igual atributo da classe
	@Column(name = "nome")
	private String nome;
	
	private String tipo;
	
	//Construtor obrigatório para o JPA com spring
	public Carro() {
		
	}
	
	public Carro(Long id, String nome) {		
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	

}
