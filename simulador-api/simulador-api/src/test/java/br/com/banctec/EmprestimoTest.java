package br.com.banctec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import br.com.banctec.service.Emprestimo;

public class EmprestimoTest {
	
	@Test
	public void testeValorIOF() {
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setVlrEmprestimo(1000);
		double resultado = emprestimo.valorIOF();
		assertEquals("Erro no calculo", 38, resultado, 0);
		
		emprestimo.setVlrEmprestimo(1500);
		resultado = emprestimo.valorIOF();
		assertNotEquals("Erro no calculo", 38, resultado, 0);
	}
	
	@Test
	public void testevalorTotalAPagar() {
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setVlrEmprestimo(1000);
		emprestimo.setQtdParcelas(12);
		emprestimo.setVlrTaxaJuros(0.03);
		double resultado = emprestimo.valorTotalAPagar();
		assertEquals("Erro no calculo", 1479.9398005463336, resultado, 0);
		
		emprestimo.setVlrEmprestimo(3350);
		emprestimo.setQtdParcelas(24);
		emprestimo.setVlrTaxaJuros(0.0125);
		resultado = emprestimo.valorTotalAPagar();
		assertEquals("Erro no calculo", 4685.143807605827, resultado, 0);
		
		emprestimo.setVlrEmprestimo(3350);
		emprestimo.setQtdParcelas(24);
		emprestimo.setVlrTaxaJuros(0.0125);
		resultado = emprestimo.valorTotalAPagar();
		assertNotEquals("Erro no calculo", 1826.70, resultado, 0);
	}
	
	@Test
	public void testevalorCETMensal() {
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setVlrEmprestimo(1200);
		emprestimo.setQtdParcelas(18);
		emprestimo.setVlrTaxaJuros(0.0215);
		double resultado = emprestimo.valorCETMensal();
		assertEquals("Erro no calculo", 2.901428773597819, resultado, 0);
		
		emprestimo.setVlrEmprestimo(2255);
		emprestimo.setQtdParcelas(12);
		emprestimo.setVlrTaxaJuros(0.0175);
		resultado = emprestimo.valorCETMensal();
		assertNotEquals("Erro no calculo", 2.31, resultado, 0);
	}
}
