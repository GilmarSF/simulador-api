package br.com.banctec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.banctec.domain.Juros;

public interface JurosRepository extends JpaRepository <Juros, Integer> {
	
    @Query("SELECT jr FROM Juros jr WHERE :qtdParcela between parcelaMinima and parcelaMaxima")
    public Juros jurosAplicado(@Param("qtdParcela") int qtdParcela);

}
