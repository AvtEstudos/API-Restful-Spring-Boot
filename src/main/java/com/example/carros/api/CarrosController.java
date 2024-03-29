package com.example.carros.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service;
	
	@GetMapping	
	//@Secured({ "ROLE_USER" })
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> get(@PathVariable("id") Long id) throws ObjectNotFoundException {
		
		CarroDTO carro = service.getCarroById(id);		
		
		return ResponseEntity.ok(carro);		
				
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getCarroByTipo(tipo) ;
		
		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);	
		
	}
	
	//@RequestBody: Converte o JSon no objeto carro, basta 
	//              o objeto JSon ter os atributos de carro
	@PostMapping
	//Define que o perfil para acesso é ADMIN
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity post(@RequestBody Carro carro){
		
		CarroDTO c = service.insert(carro);		
		URI location = getUri(c.getId());		
		return ResponseEntity.created(location).build();		
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {
	
		carro.setId(id);
		
		CarroDTO c = service.update(carro, id);
		
		return c != null ?
				ResponseEntity.ok(c) :
				ResponseEntity.notFound().build(); 
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		
		service.delete(id);		
		
		return ResponseEntity.ok().build();		
		
	}
	
	
}
