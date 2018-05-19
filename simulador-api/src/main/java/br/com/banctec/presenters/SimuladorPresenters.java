package br.com.banctec.presenters;

public class SimuladorPresenters {

	private double vlrEmprestimo;
	private double vlrTaxaJuros;
	private int qtdParcelas;
	private double vlrPercIOF = 0.038;
	
	public SimuladorPresenters(double vlrEmprestimo, double vlrTaxaJuros, int qtdParcelas) {
		super();
		this.vlrEmprestimo = vlrEmprestimo;
		this.vlrTaxaJuros = vlrTaxaJuros;
		this.qtdParcelas = qtdParcelas;
	}
	
	public double getValorIOF() {
		return(vlrEmprestimo * vlrPercIOF);
	}	
	
	public double getValorTotalAPagar() {
		double vlrAnual =  vlrEmprestimo + getValorIOF();
		
		for (int a = 0 ; a < qtdParcelas ; a++) {
			vlrAnual = vlrAnual + (vlrAnual  * vlrTaxaJuros);
		}
				
		return(vlrAnual);
	}	
	
	public double getValorCETMensal() {
		double vlrAnual =  getValorTotalAPagar();
		
		double vlrCETTotal = ((vlrAnual / vlrEmprestimo) - 1) * 100;
		double vlrCETMensal = vlrCETTotal / qtdParcelas;
		
		return(vlrCETMensal);
	}
	
}
