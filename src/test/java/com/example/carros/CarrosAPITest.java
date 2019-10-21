package com.example.carros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.carros.domain.Carro;
import com.example.carros.domain.dto.CarroDTO;

@RunWith(SpringRunner.class)
/*
classes = CarrosApplication.class: Indica a classe da aplicação que será rodada
webEnvironment = RANDOM_PORT: Atributo que fará subir a parte web do spring e 
                              permitira que você faça as requisições HTTP                                     
*/                                   
@SpringBootTest(classes = CarrosApplication.class,
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest {
	
	//Faz as requisições HTTP
	@Autowired
	protected TestRestTemplate rest;
	
	private ResponseEntity<CarroDTO> getCarro(String url){
		return rest.getForEntity(url, CarroDTO.class);
	}
	
	private ResponseEntity<List<CarroDTO>> getCarros(String url){
		
		return rest.exchange(
				url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<CarroDTO>>() {
					
				});		
	}
	
	@Test
	public void testLista() {
		List<CarroDTO> carros = getCarros("/api/v1/carros").getBody();
		assertNotNull(carros);
		assertEquals(30, carros.size());
	}
	
	@Test
	public void testListPorTipo() {
		
		assertEquals(10, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
		assertEquals(10, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
		assertEquals(10, getCarros("/api/v1/carros/tipo/luxo").getBody().size());
		
		assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());		
	}
	
	@Test
	public void testGetOK() {
		
		ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
		CarroDTO c = response.getBody();
		assertEquals("Ferrari FF", c.getNome());
	}
	
	@Test
	public void testGetNotFound() {
		
		ResponseEntity response = getCarro("/api/v1/carros/1100");
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testSave() {
		
		Carro carro = new Carro();
		carro.setNome("Porshe");
		carro.setTipo("esportivos");
		
		//Insert
		//rest.postForEntity("/api/v1/carros", carro, null): convertento o obejto criado
		//                                                   para o formato JSON
		ResponseEntity response = rest.postForEntity("/api/v1/carros", carro, null);
		System.out.println(response);
		
		//Verifica se criou
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		//Buscar o objeto
		String location = response.getHeaders().get("location").get(0);
		CarroDTO c = getCarro(location).getBody();
		
		assertNotNull(c);
		assertEquals("Porshe", c.getNome());
		assertEquals("esportivos", c.getTipo());
		
		//Deletar o objeto
		rest.delete(location);
		
		//Verificar se deletou
		assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
	}	

}
