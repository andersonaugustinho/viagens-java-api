package com.github.aaugustinho.viagensjavaapi.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estatistica {
	
	private long contador;
	private BigDecimal soma;
	private BigDecimal media;
	private BigDecimal maximo;
	private BigDecimal minimo;

	public BigDecimal getSoma() {
		return soma;
	}

	public void setSoma(BigDecimal soma) {
		this.soma = soma;
	}

	public BigDecimal getMedia() {
		return media;
	}

	public void setMedia(BigDecimal media) {
		this.media = media;
	}

	public BigDecimal getMaximo() {
		return maximo;
	}

	public void setMaximo(BigDecimal maximo) {
		this.maximo = maximo;
	}

	public BigDecimal getMinimo() {
		return minimo;
	}

	public void setMinimo(BigDecimal minimo) {
		this.minimo = minimo;
	}

	public long getContador() {
		return contador;
	}

	public void setContador(long contador) {
		this.contador = contador;
	}
	
}
