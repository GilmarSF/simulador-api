package br.com.banctec.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.banctec.domain.Cliente;
import br.com.banctec.repository.ClienteRepository;
import br.com.banctec.service.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Cliente> Listar() {
		return clienteRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Integer id) {

		Cliente cliente = clienteRepository.findOne(id);	
	   return cliente  == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(cliente);
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Cliente> incluirCliente (@RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);	
	}
    
	@RequestMapping(method=RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Cliente> alterarCliente (@PathVariable Integer id, @Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		
		Cliente clienteSalvo = clienteService.atualizar(id, cliente);
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteSalvo);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/{id}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {

		clienteRepository.delete(id);
	}	
	
}
