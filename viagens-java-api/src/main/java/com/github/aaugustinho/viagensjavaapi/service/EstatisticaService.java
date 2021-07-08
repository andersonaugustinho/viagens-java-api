package com.github.aaugustinho.viagensjavaapi.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.aaugustinho.viagensjavaapi.model.Estatistica;
import com.github.aaugustinho.viagensjavaapi.model.Viagem;

@Service
public class EstatisticaService {

	public Estatistica criar(List<Viagem> travels) {
		
		var estatistica = new Estatistica();
		estatistica.setContador(travels.stream().count());
		estatistica.setMedia(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getValor().doubleValue()).average().orElse(0.0)).setScale(2, RoundingMode.HALF_UP));
		estatistica.setMinimo(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getValor().doubleValue()).min().orElse(0.0)).setScale(2, RoundingMode.HALF_UP));
		estatistica.setMaximo(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getValor().doubleValue()).max().orElse(0.0)).setScale(2, RoundingMode.HALF_UP));
		estatistica.setSoma(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getValor().doubleValue()).sum()).setScale(2, RoundingMode.HALF_UP));
		
		return estatistica;
	}
	
}
