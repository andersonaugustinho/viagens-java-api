package com.github.aaugustinho.viagensjavaapi.enumeration;

public enum ViagemTipoEnum {
	
	RETURN("RETORNO"), ONE_WAY("SOMENTE-IDA"), MULTI_CITY("VARIAS-CIDADES");
	
	private String valor;
	
	private ViagemTipoEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static ViagemTipoEnum getEnum(String valor) {
		
		for(ViagemTipoEnum t : values()) {
			if(valor.equals(t.getValor())) {
				return t;
			}
		}
		
		throw new RuntimeException("Tipo não encontrado.");
	}

}
