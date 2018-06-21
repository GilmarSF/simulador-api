package br.com.banctec.service;

public class Emprestimo {

	private double vlrEmprestimo;
	private double vlrTaxaJuros;
	private int qtdParcelas;
	private double vlrPercIOF = 0.038;

	public double valorIOF() {
		return(vlrEmprestimo * vlrPercIOF);
	}
	
	public double valorTotalAPagar() {
		double vlrAnual =  vlrEmprestimo + valorIOF();
		
		for (int a = 0 ; a < qtdParcelas ; a++) {
			vlrAnual = vlrAnual + (vlrAnual  * vlrTaxaJuros);
		}
				
		return(vlrAnual);
	}
	
	public double valorCETMensal() {
		double vlrAnual =  valorTotalAPagar();
		
		double vlrCETTotal = ((vlrAnual / vlrEmprestimo) - 1) * 100;
		double vlrCETMensal = vlrCETTotal / qtdParcelas;
		
		return(vlrCETMensal);
	}

	
	public double getVlrEmprestimo() {
		return vlrEmprestimo;
	}

	public void setVlrEmprestimo(double vlrEmprestimo) {
		if (vlrEmprestimo < 0) {
			throw new IllegalArgumentException("O valor do emprestimo deve ser maior que zero.");
		}

		this.vlrEmprestimo = vlrEmprestimo;
	}

	public double getVlrTaxaJuros() {
		return vlrTaxaJuros;
	}

	public void setVlrTaxaJuros(double vlrTaxaJuros) {
		if (vlrEmprestimo < 0) {
			throw new IllegalArgumentException("O valor da taxa de juros deve ser maior que zero.");
		}	
		this.vlrTaxaJuros = vlrTaxaJuros;
	}

	public int getQtdParcelas() {

		return qtdParcelas;
	}

	public void setQtdParcelas(int qtdParcelas) {

		if (qtdParcelas < 12 || qtdParcelas > 120) {
			throw new IllegalArgumentException("O número mínimo de parcelas deve ser 12 e o máximo 120.");
		}

		this.qtdParcelas = qtdParcelas;
	}

}
