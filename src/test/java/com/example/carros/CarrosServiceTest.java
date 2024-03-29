package com.example.carros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.Console;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosServiceTest {
	
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
		c = service.getCarroById(id);
		
		assertNotNull(c);		
		
		//Verifica se carro recuperado é igual ao inserido		
		assertEquals("Fiesta", c.getNome());
		assertEquals("popular", c.getTipo());
		
		//Deleta o carro para eliminar sujeira
		service.delete(id);
		
		//Verificar se deletou
		try {
			service.getCarroById(id);
	        fail("O carro não foi excluído");
	    } catch (ObjectNotFoundException e) {
	    	// OK
	    }		
	}
	
	@Test
	public void tstListar() {
		
		List<CarroDTO> carros = service.getCarros();	
		
		for (CarroDTO c : carros) {
			System.out.println(c.getNome());
		}
		
		assertEquals(30, carros.size());
		
	}
	
	@Test
	public void tstListarTipo() {
		
		assertEquals(10, service.getCarroByTipo("classicos").size());
		assertEquals(10, service.getCarroByTipo("esportivos").size());
		assertEquals(10, service.getCarroByTipo("luxo").size());
		
		assertEquals(0, service.getCarroByTipo("x").size());
	}
	
	@Test
	public void testGet() throws ObjectNotFoundException {
	
		CarroDTO c = service.getCarroById(11L);
		
		assertNotNull(c);
		
		assertEquals("Ferrari FF", c.getNome());
	}
}
