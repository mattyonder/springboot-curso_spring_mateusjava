package com.remedio.mateus.curso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedio.mateus.curso.remedio.DadosAtualizarRemedio;
import com.remedio.mateus.curso.remedio.DadosCadastroRemedio;
import com.remedio.mateus.curso.remedio.DadosDetalhamentoRemedio;
import com.remedio.mateus.curso.remedio.DadosListagemRemedio;
import com.remedio.mateus.curso.remedio.Remedio;
import com.remedio.mateus.curso.remedio.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
	
	@Autowired
	private RemedioRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados) {
		var remedio = dados;
		repository.save(new Remedio(remedio));

		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	}
	
	@ GetMapping
	public ResponseEntity<List <DadosListagemRemedio>> listar (){
		var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
		
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir (@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar (@PathVariable long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("ativar/{id}")
	@Transactional
	public ResponseEntity<Void> ativar (@PathVariable long id) {
		var remedio = repository.getReferenceById(id);
		remedio.ativar();
		
		return ResponseEntity.noContent().build();
	}

}
