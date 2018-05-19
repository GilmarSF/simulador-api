package br.com.banctec.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.banctec.domain.Simulador;
import br.com.banctec.presenters.SimuladorPresenters;
import br.com.banctec.repository.SimuladorRepository;
import br.com.banctec.service.SimuladorService;

@RestController
public class SimuladorController {
    @Autowired
    SimuladorRepository simuladorRepository;
    
    @Autowired
    SimuladorService simuladorService;
	@RequestMapping(method=RequestMethod.GET)
	public List<Simulador> Listar() {
		return simuladorRepository.findAll();
	}
	
	@RequestMapping("/calcular")
	public ResponseEntity<?> calcular() {
		
		SimuladorPresenters  resultado = new SimuladorPresenters(1000l, 0.03, 36);

	   return resultado  == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(resultado);
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Integer id) {

		Simulador simulador = simuladorRepository.findOne(id);	
	   return simulador  == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(simulador);
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Simulador> incluirSimulador (@RequestBody Simulador simulador, HttpServletResponse response) {
		Simulador simuladorSalvo = simuladorRepository.save(simulador);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(simuladorSalvo);	
	}
    
	@RequestMapping(method=RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Simulador> alterarSimulador (@PathVariable Integer id, @Valid @RequestBody Simulador simulador, HttpServletResponse response) {
		
		Simulador simuladorSalvo = simuladorService.atualizar(id, simulador);
		
		return ResponseEntity.status(HttpStatus.OK).body(simuladorSalvo);
	}
    
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {

		simuladorRepository.delete(id);
	}	
}
