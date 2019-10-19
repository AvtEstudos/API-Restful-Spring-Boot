package com.example.carros.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//Classe que irá fazer a conexão com o banco de dados
public interface CarroRepository extends JpaRepository<Carro, Long>{

	List<Carro> findByTipo(String tipo);

}
