package com.github.aaugustinho.viagensjavaapi.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aaugustinho.viagensjavaapi.enumeration.ViagemTipoEnum;
import com.github.aaugustinho.viagensjavaapi.factory.ViagemFactory;
import com.github.aaugustinho.viagensjavaapi.factory.impl.ViagemFactoryImpl;
import com.github.aaugustinho.viagensjavaapi.model.Viagem;

@Service
public class ViagemService {

	private ViagemFactory factory;
	public List<Viagem> viagens;
	
	public void criarViagemFactory() {
		if(factory == null) {
		   factory = new ViagemFactoryImpl();
		}
	}
	
	public void criarViagemList() {
		if(viagens == null) {
		   viagens = new ArrayList<>();
		}
	}
	
	public boolean isJSONValido(String jsonInString) {
	    try {
	       return new ObjectMapper().readTree(jsonInString) != null;
	    } catch (IOException e) {
	    	System.out.println("json invalido");
	       return false;
	    }
	}
	
	private long parseId(JSONObject viagem) {
		return Long.valueOf((int) viagem.get("id"));
	}
	
	private BigDecimal parseValor(JSONObject viagem) {
		return new BigDecimal((String) viagem.get("valor"));
	}
	
	private LocalDateTime parseDataInicio(JSONObject viagem) {
		var dataInicio= (String) viagem.get("dataInicio");
		return ZonedDateTime.parse(dataInicio).toLocalDateTime();
	}
	
	private LocalDateTime parseDataFim(JSONObject viagem) {
		var dataFim= (String) viagem.get("dataFim");
		return ZonedDateTime.parse(dataFim).toLocalDateTime();
	}
	
	public boolean isStartDateGreaterThanEndDate(Viagem viagem) {
		if (viagem.getDataFim() == null) return false;
		return viagem.getDataInicio().isAfter(viagem.getDataFim());
	}
	
	private void setViagemValores(JSONObject jsonViagem, Viagem viagem) {
		
		String numeroOrdem = (String) jsonViagem.get("numeroOrdem");
		String tipo = (String) jsonViagem.get("tipo");
		
		viagem.setNumeroOrdem(numeroOrdem != null ? numeroOrdem : viagem.getNumeroOrdem());
		viagem.setValor(jsonViagem.get("valor") != null ? parseValor(jsonViagem) : viagem.getValor());
		viagem.setDataInicio(jsonViagem.get("dataInicio") != null ? parseDataInicio(jsonViagem) : viagem.getDataInicio());
		viagem.setDataFim(jsonViagem.get("dataFim") != null ? parseDataFim(jsonViagem) : viagem.getDataFim());
		viagem.setTipo(tipo != null ? ViagemTipoEnum.getEnum(tipo) : viagem.getTipo());
	}

	public Viagem criar(JSONObject jsonViagem) {
		
		criarViagemFactory();
		
		Viagem viagem = factory.criarViagem((String) jsonViagem.get("tipo"));
		viagem.setId(parseId(jsonViagem));
		setViagemValores(jsonViagem, viagem);
		
		return viagem;
	}

	public Viagem atualizar(Viagem viagem, JSONObject jsonViagem) {
		
		setViagemValores(jsonViagem, viagem);
		return viagem;
	}
	
	public void adicionar(Viagem viagem) {
		criarViagemList();
		viagens.add(viagem);
	}
	public List<Viagem> localizar() {
		criarViagemList();
		return viagens;
	}
	public Viagem localizarPorId(long id) {
		System.out.println(viagens.size());
		return viagens.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
	}
	
	public void remover() {
		viagens.clear();
	}
	
	public void limparObjetos() {
		viagens = null;
		factory = null;
	}
	
}
