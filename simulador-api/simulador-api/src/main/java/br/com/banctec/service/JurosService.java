package br.com.banctec.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banctec.domain.Juros;
import br.com.banctec.repository.JurosRepository;

@Service
public class JurosService {

	@Autowired
	private JurosRepository jurosRepository;
	
	public Juros atualizar(Integer id, Juros juros) {
		Juros jurosSalvo = pesquisarJurosCodigo(id);
		
        BeanUtils.copyProperties(juros, jurosSalvo, "id");

        return jurosRepository.save(jurosSalvo);
	}
	
	public Juros pesquisarJurosCodigo(Integer id) {
		Juros jurosSalvo = jurosRepository.findOne(id);
		if (jurosSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return jurosSalvo;
	}
	
    
}
