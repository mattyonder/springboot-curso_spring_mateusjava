package com.remedio.mateus.curso.remedio;

import java.time.LocalDate;

public record DadosListagemRemedio(Long id,String nome, Via via, String lote, int quantidade, Laboratorio laboratorio, LocalDate validade) {
	public DadosListagemRemedio(Remedio remedio) {
		this( remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getQuantidade(), remedio.getLaboratorio(), remedio.getValidade());
	}
}
