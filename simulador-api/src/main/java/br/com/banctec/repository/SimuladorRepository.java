package br.com.banctec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banctec.domain.Simulador;

public interface SimuladorRepository extends JpaRepository <Simulador, Integer> {

}
