package br.com.banctec.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.banctec.domain.Simulador;
import br.com.banctec.presenters.SimuladorPresenters;
import br.com.banctec.repository.SimuladorRepository;

@Service
public class SimuladorService {

	@Autowired 
	private JavaMailSender mailSender;
	
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
	
	public void enviarEmail(SimuladorPresenters resultado, 
			                Simulador simulador) {
		
		String mensagem = "Cliente: " + simulador.getCliente().getNome() + "\n";
	           mensagem = mensagem + "Código da Simulação: " + simulador.getCodSimulacao() + "\n";
     	       mensagem = mensagem + "Valor do Emprestimo: " + resultado.getValorEmprestimo() + "\n\n";
	           mensagem = mensagem + "Valor IOF: " + resultado.getValorIOF() + "\n";
	           mensagem = mensagem + "Valor CET Mensal: " + resultado.getValorCETMensal() + "\n";
	           mensagem = mensagem + "Valor a Pagar: " + simulador.getQtdeParcela() + " x " + resultado.getValorPorParcela() + "\n\n";
	           mensagem = mensagem + "Valor Total a Pagar: " + resultado.getValorTotalPagar() + "\n";
	       
	    sendMail("parse.bandtec.gmail.com", 
 		          simulador.getCliente().getEmail(), 
	              "Resultado da Simulação - Código: " + simulador.getCodSimulacao() , 
		          mensagem);
	
	}
	
    public String sendMail(String strDe,
    		               String strPara,
    		               String strSubject,
    		               String strCorpo) {
    	
        SimpleMailMessage message = new SimpleMailMessage();

        message.setText(strCorpo);
        message.setTo(strPara);
        message.setFrom(strDe);
        message.setSubject(strSubject);

    	mailSender.send(message);
        return "Email enviado com sucesso!";

    }
}
