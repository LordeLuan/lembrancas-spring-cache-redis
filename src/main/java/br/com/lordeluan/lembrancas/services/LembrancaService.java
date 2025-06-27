package br.com.lordeluan.lembrancas.services;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.lordeluan.lembrancas.domain.Lembranca;
import br.com.lordeluan.lembrancas.dtos.LembrancaProjecao;
import br.com.lordeluan.lembrancas.repository.LembrancaRepository;

@Service
public class LembrancaService {

    private final LembrancaRepository lembrancaRepository;

    public LembrancaService(LembrancaRepository lembrancaRepository) {
        this.lembrancaRepository = lembrancaRepository;
    }

    @Cacheable(value = "lembrancas", key = "'allLembrancas'")
    public List<Lembranca> listarTodas() {
        System.out.println("Buscando todas as lembranças do banco de dados...");
        var lista = lembrancaRepository.findAll();
        lista.get(0).setTitulo("Teste Setter");
        return lista;
    }

    @Cacheable(value = "lembrancas", key = "#id")
    public Lembranca buscarPorId(Integer id) {
        System.out.println("Buscando lembranças por ID: " + id);
        Lembranca proj = lembrancaRepository.findById(id).orElseThrow(() -> new RuntimeException("Lembrança não encontrada com ID: " + id));
        return proj;
    }

    @Cacheable(value = "lembrancas", key = "#titulo.toLowerCase().replaceAll(' ', '_')")
    public List<LembrancaProjecao> buscarPorTitulo(String titulo) {
        System.out.println("Buscando lembranças por título: " + titulo);
        List<LembrancaProjecao> proj = lembrancaRepository.findByTitulo(titulo);
        return proj;
    } 

    @CacheEvict(value = "lembrancas", allEntries = true)
    public void salvarLembranca(Lembranca lembranca) {
        lembrancaRepository.save(lembranca);
    }
}
