package br.com.banctec.controller;

import java.time.LocalDate;
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

import br.com.banctec.domain.Juros;
import br.com.banctec.domain.Simulador;
import br.com.banctec.presenters.SimuladorPresenters;
import br.com.banctec.repository.JurosRepository;
import br.com.banctec.repository.SimuladorRepository;
import br.com.banctec.service.SimuladorService;


@RestController
@RequestMapping(value = "/simula")
public class SimuladorController {
    @Autowired
    SimuladorRepository simuladorRepository;
    
    @Autowired
    JurosRepository jurosRepository;
    
    @Autowired
    SimuladorService simuladorService;
    
	@RequestMapping(method=RequestMethod.GET)
	public List<Simulador> Listar() {
		return simuladorRepository.findAll();
	}
	
	@RequestMapping("/calcular/{id}")
	public ResponseEntity<?> calcular(@PathVariable Integer id) {
		
		SimuladorPresenters  resultado;
		Simulador simulador = simuladorRepository.findOne(id);	
		
		if (simulador  == null) {
			resultado = null;
		} else {
			Juros juros = jurosRepository.jurosAplicado(simulador.getQtdeParcela());

			resultado = new SimuladorPresenters(simulador, 
                                                juros);

			simuladorService.enviarEmail(resultado, 
	                                     simulador);            		
		    
		}
	   return resultado  == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(resultado);
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Integer id) {

		Simulador simulador = simuladorRepository.findOne(id);	
	   return simulador  == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(simulador);
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Simulador> incluirSimulador (@RequestBody Simulador simulador, HttpServletResponse response) {
		
		if (simulador.getDataSimulacao() == null) {
			simulador.setDataSimulacao(LocalDate.now());
		} 
		
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
