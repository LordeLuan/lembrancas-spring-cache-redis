package br.com.lordeluan.lembrancas.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.lordeluan.lembrancas.services.LembrancaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lordeluan.lembrancas.domain.Lembranca;
import br.com.lordeluan.lembrancas.dtos.LembrancaDto;
import br.com.lordeluan.lembrancas.dtos.LembrancaProjecao;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/lembrancas")
public class LembrancasController {

    private final LembrancaService lembrancaService;

    public LembrancasController(LembrancaService lembrancaService) {
        this.lembrancaService = lembrancaService;
    }

    @GetMapping()
    public ResponseEntity<List<Lembranca>> listarTodasLembrancas() {
        List<Lembranca> lembrancas = lembrancaService.listarTodas();
        return ResponseEntity.ok(lembrancas);
    }

    @GetMapping("{id}")
    public ResponseEntity<Lembranca> findById(@PathVariable Integer id) {
        var titulo = lembrancaService.buscarPorId(id);
        System.out.println("Titulo: " + titulo);
        return ResponseEntity.ok(titulo);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LembrancaProjecao>> buscarPorTitulo(@PathVariable String titulo) {
        var lembrancas = lembrancaService.buscarPorTitulo(titulo);
        System.out.println("Buscando por título: " + titulo);
        return ResponseEntity.ok(lembrancas);
    }

    @PostMapping()
    public ResponseEntity<String> salvar(@RequestBody @Validated LembrancaDto dto) {
        Lembranca entity = new Lembranca();
        entity.setTitulo(dto.titulo());
        entity.setDescricao(dto.descricao());

        lembrancaService.salvarLembranca(entity);

        return ResponseEntity.created(null)
                .body("Lembrança salva com sucesso!");
    }

}
