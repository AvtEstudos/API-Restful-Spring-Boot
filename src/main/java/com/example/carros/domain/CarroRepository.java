package com.example.carros.domain;

import org.springframework.data.repository.CrudRepository;

//Classe que irá fazer a conexão com o banco de dados
//P1: Classe da entidade
//P2: Tipo da variavel chave da tabela
public interface CarroRepository extends CrudRepository<Carro, Long>{

	Iterable<Carro> findByTipo(String tipo);

}
