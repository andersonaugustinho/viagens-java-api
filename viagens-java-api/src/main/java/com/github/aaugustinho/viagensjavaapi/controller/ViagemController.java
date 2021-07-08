package com.github.aaugustinho.viagensjavaapi.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.aaugustinho.viagensjavaapi.model.Viagem;
import com.github.aaugustinho.viagensjavaapi.service.ViagemService;

@RestController
@RequestMapping("/api-viagens/viagens")
public class ViagemController {
	
	private static final Logger logger = Logger.getLogger(ViagemController.class);
		
	@Autowired
	private ViagemService viagemService;
	
	@GetMapping
	public ResponseEntity<List<Viagem>> loalizar() {
		if(viagemService.localizar().isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
		logger.info(viagemService.localizar());
		return ResponseEntity.ok(viagemService.localizar());
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> remover() {
		try {
			viagemService.remover();
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			logger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Viagem> criar(@RequestBody JSONObject viagem) {
		try {
			if(viagemService.isJSONValido(viagem.toString())) {
				Viagem viagemCriada = viagemService.criar(viagem);
				var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(viagemCriada.getNumeroOrdem()).build().toUri();
				
				if(viagemService.isStartDateGreaterThanEndDate(viagemCriada)){
					logger.error("Data de Inicio não pode ser superior a Data de Dim.");
					return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
				} else {
					viagemService.adicionar(viagemCriada);
					return ResponseEntity.created(uri).body(null);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch(Exception e) {
			logger.error("Erro ao processar o JSON. " + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@PutMapping(path = "/{id}", produces = { "application/json" })
	public ResponseEntity<Viagem> update(@PathVariable("id") long id, @RequestBody JSONObject viagem) {
		try {
			if(viagemService.isJSONValido(viagem.toString())) {
				Viagem viagemParaAtualizar = viagemService.localizarPorId(id);
				if(viagemParaAtualizar == null){
					logger.error("Viagem não encontrada.");
					return ResponseEntity.notFound().build(); 
				}else {
					viagemParaAtualizar = viagemService.atualizar(viagemParaAtualizar, viagem);
					return ResponseEntity.ok(viagemParaAtualizar);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Erro ao processar o JSON. " + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
}
