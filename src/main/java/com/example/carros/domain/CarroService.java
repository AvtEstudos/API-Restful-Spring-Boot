package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.domain.dto.CarroDTO;

@Service
public class CarroService {
	
	//Deixando o spring injetar a dependência
	@Autowired
	private CarroRepository rep;
	
	public List<CarroDTO> getCarros(){		
		
		//".map(CarroDTO::new)": Sintaxe resumida da expressão lambda
		//.map(c -> new CarroDTO(c))
		return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
	}	

	public Optional<CarroDTO> getCarroById(Long id) {
		
		return rep.findById(id).map(CarroDTO::create);
		
	}

	public List<CarroDTO> getCarroByTipo(String tipo) {		
		
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());		
		
	}
	
	public List<Carro> getCarrosFake(){
		
		List<Carro> carros = new ArrayList<>();		
		//carros.add(new Carro( 1L, "Fusca"));
		//carros.add(new Carro( 2L, "Brasilia"));
		//carros.add(new Carro( 3L, "Chevette"));		
		return carros;
	}

	public Carro insert(Carro carro) {
		
		return rep.save(carro);		
		
	}
	
	public Carro update(Carro carro, Long id) {
		
		//Assert.notNull(id, message: "Não foi possível atualizar o registro.");
		
		Optional<Carro> optional = rep.findById(id);		
		
		if (optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			
			rep.save(db);
			
			return db;
			
		} else {
			throw new RuntimeException("Não foi possivel atualizar o registro.");
		}		
	}

	public void delete(Long id) {
		
		Optional<CarroDTO> carro = getCarroById(id);
		
		if(carro.isPresent()) {
			rep.deleteById(id);
		}
		
	}
	
}
