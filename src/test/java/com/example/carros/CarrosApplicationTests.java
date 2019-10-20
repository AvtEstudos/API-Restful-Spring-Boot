package com.example.carros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosApplicationTests {
	
	@Autowired
	private CarroService service;

	@Test
	public void tstInserir() {
		
		//Inseri carro através do serviço
		Carro carro = new Carro();
		carro.setNome("Fiesta");
		carro.setTipo("popular");		
		CarroDTO c = service.insert(carro);
		
		//Verifica se carro foi incluído com sucesso, 
		//verificando se retorno não é null
		assertNotNull(c);
		
		//Valida se iD do carro incluído é válido
		Long id = c.getId();
		assertNotNull(id);
		
		//Recupera o carro 
		Optional<CarroDTO> op = service.getCarroById(id);
		
		//Verifica se carro recuperado existe
		assertTrue(op.isPresent());
		
		//Verifica se carro recuperado é igual ao inserido
		c = op.get();
		assertEquals("Fiesta", c.getNome());
		assertEquals("popular", c.getTipo());
		
		//Deleta o carro para eliminar sujeira
		service.delete(id);
		
		//Verificar se deletou
		assertFalse(service.getCarroById(id).isPresent());		
	}
	
	@Test
	public void tstListar() {
		
		List<CarroDTO> carros = service.getCarros();
		
		assertEquals(30, carros.size());
		
	}

}
