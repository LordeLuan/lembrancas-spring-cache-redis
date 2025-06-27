package br.com.lordeluan.lembrancas.repository;

import br.com.lordeluan.lembrancas.domain.Lembranca;
import br.com.lordeluan.lembrancas.dtos.LembrancaProjecao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LembrancaRepository extends JpaRepository<Lembranca, Integer>{

    List<LembrancaProjecao> findByTitulo(String titulo);
}
