package com.github.aaugustinho.viagensjavaapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.github.aaugustinho.viagensjavaapi.enumeration.ViagemTipoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viagem {

	private Long id;
	private String numeroOrdem;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private BigDecimal valor;
	
	private ViagemTipoEnum tipo;
	
	public Viagem(ViagemTipoEnum tipo){
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroOrdem() {
		return numeroOrdem;
	}

	public void setNumeroOrdem(String numeroOrdem) {
		this.numeroOrdem = numeroOrdem;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ViagemTipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(ViagemTipoEnum tipo) {
		this.tipo = tipo;
	}

}
