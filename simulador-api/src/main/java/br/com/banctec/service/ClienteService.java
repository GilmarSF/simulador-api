package br.com.banctec.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banctec.domain.Cliente;
import br.com.banctec.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente atualizar(Integer id, Cliente cliente) {
		Cliente clienteSalvo = pesquisarClienteCodigo(id);
		
        BeanUtils.copyProperties(cliente, clienteSalvo, "id");

        return clienteRepository.save(clienteSalvo);
	}
	
	public Cliente pesquisarClienteCodigo(Integer id) {
		Cliente clienteSalvo = clienteRepository.findOne(id);
		if (clienteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return clienteSalvo;
	}
}
