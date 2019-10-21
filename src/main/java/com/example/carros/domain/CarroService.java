package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.carros.domain.dto.CarroDTO;

import javassist.tools.rmi.ObjectNotFoundException;

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

	public CarroDTO getCarroById(Long id) throws ObjectNotFoundException
	{
		
		Optional<Carro> carro = rep.findById(id);		
		
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
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

	public CarroDTO insert(Carro carro) {
		
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro.");
		return CarroDTO.create(rep.save(carro));		
		
	}
	
	public CarroDTO update(Carro carro, Long id) {
						
		Optional<Carro> optional = rep.findById(id);		
		
		if (optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			
			rep.save(db);
			
			return CarroDTO.create(db);
			
		} else {
			return null;
		}		
	}

	public void delete(Long id) {		
		
		rep.deleteById(id);
				
	}
	
}
