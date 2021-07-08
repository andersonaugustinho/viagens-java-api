package com.github.aaugustinho.viagensjavaapi.factory.impl;

import com.github.aaugustinho.viagensjavaapi.enumeration.ViagemTipoEnum;
import com.github.aaugustinho.viagensjavaapi.factory.ViagemFactory;
import com.github.aaugustinho.viagensjavaapi.model.Viagem;

public class ViagemFactoryImpl implements ViagemFactory {

	@Override
	public Viagem criarViagem(String tipo) {
		
		if (ViagemTipoEnum.ONE_WAY.getValor().equals(tipo)) {
			return new Viagem(ViagemTipoEnum.ONE_WAY);
		} else if (ViagemTipoEnum.MULTI_CITY.getValor().equals(tipo)) {
			return new Viagem(ViagemTipoEnum.MULTI_CITY); 
		}
		
		return new Viagem(ViagemTipoEnum.RETURN);
	}

}
