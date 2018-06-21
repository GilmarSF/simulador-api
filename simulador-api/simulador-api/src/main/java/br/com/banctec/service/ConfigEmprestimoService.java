package br.com.banctec.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banctec.domain.ConfigEmprestimo;
import br.com.banctec.repository.ConfigEmprestimoRepository;

@Service
public class ConfigEmprestimoService {

	@Autowired
	private ConfigEmprestimoRepository configEmprestimoRepository;
	
	public ConfigEmprestimo atualizar(Integer id, ConfigEmprestimo configEmprestimo) {
		ConfigEmprestimo configEmprestimoSalvo = pesquisarConfigEmprestimoCodigo(id);

		if (configEmprestimo.getDataConfiguracao() == null) {
			configEmprestimo.setDataConfiguracao(configEmprestimoSalvo.getDataConfiguracao());
		}
        BeanUtils.copyProperties(configEmprestimo, configEmprestimoSalvo, "id");

        return configEmprestimoRepository.save(configEmprestimoSalvo);
	}
	
	public ConfigEmprestimo pesquisarConfigEmprestimoCodigo(Integer id) {
		ConfigEmprestimo configEmprestimoSalvo = configEmprestimoRepository.findOne(id);
		if (configEmprestimoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return configEmprestimoSalvo;
	}
}
