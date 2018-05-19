package br.com.banctec.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banctec.domain.Simulador;
import br.com.banctec.repository.SimuladorRepository;

@Service
public class SimuladorService {

	@Autowired
	private SimuladorRepository simuladorRepository;
	
	public Simulador atualizar(Integer id, Simulador simulador) {
		Simulador simuladorSalvo = pesquisarSimuladorCodigo(id);
		
        BeanUtils.copyProperties(simulador, simuladorSalvo, "id");

        return simuladorRepository.save(simuladorSalvo);
	}
	
	public Simulador pesquisarSimuladorCodigo(Integer id) {
		Simulador simuladorSalvo = simuladorRepository.findOne(id);
		if (simuladorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return simuladorSalvo;
	}
}
