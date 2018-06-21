package br.com.banctec.presenters;

import java.text.NumberFormat;

import br.com.banctec.domain.Juros;
import br.com.banctec.domain.Simulador;

public class SimuladorPresenters {

	private String nomeCliente;
	public String getNomeCliente() {
		return nomeCliente;
	}

	private double vlrEmprestimo;
	private double vlrTaxaJuros;
	private int qtdParcelas;


	private double vlrPercIOF = 0.038;
	
	
/*	resultado = new SimuladorPresenters(simulador.getValorEmprestimo(), 
            juros.getPercentualJuros(),
            simulador.getConfigEmprestimo().getPercentualIof(), 
            simulador.getQtdeParcela())*/;
	
	public SimuladorPresenters(Simulador simulador, 
			                   Juros juros) {
		super();
		this.nomeCliente = simulador.getCliente().getNome();
		this.vlrEmprestimo = simulador.getValorEmprestimo();
		this.vlrPercIOF = simulador.getConfigEmprestimo().getPercentualIof();
		this.vlrTaxaJuros = juros.getPercentualJuros();
		this.qtdParcelas = simulador.getQtdeParcela();
	}
	
	public int getQtdParcelas() {
		return qtdParcelas;
	}
	
	private double getVrIOF() {
		return(vlrEmprestimo * vlrPercIOF);
	}	
	
	private double getValorTotalAPagar() {
		double vlrAnual =  vlrEmprestimo + getVrIOF();
		
		for (int a = 0 ; a < qtdParcelas ; a++) {
			vlrAnual = vlrAnual + (vlrAnual  * vlrTaxaJuros);
		}
				
		return(vlrAnual);
	}	
	
	public String getValorEmprestimo() {
		return(NumberFormat.getCurrencyInstance().format(this.vlrEmprestimo));
	}
	
	public String getValorIOF() {
		return(NumberFormat.getCurrencyInstance().format(getVrIOF()));
	}
	
	public String getValorTotalPagar() {
				
		return(NumberFormat.getCurrencyInstance().format(getValorTotalAPagar()));
	}	
	
	public String getValorCETMensal() {
		double vlrAnual =  getValorTotalAPagar();
		
		double vlrCETTotal = ((vlrAnual / vlrEmprestimo) - 1) * 100;
		double vlrCETMensal = vlrCETTotal / qtdParcelas;
		
		return(NumberFormat.getCurrencyInstance().format(vlrCETMensal));
	}
	
	public String getValorPorParcela() {
		return(NumberFormat.getCurrencyInstance().format(getValorTotalAPagar() / qtdParcelas));
	}	
	
}
