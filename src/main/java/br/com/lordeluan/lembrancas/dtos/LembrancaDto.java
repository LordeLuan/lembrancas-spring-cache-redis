package br.com.lordeluan.lembrancas.dtos;

import java.io.Serializable;

public record LembrancaDto(String titulo, String descricao) implements Serializable {
}
