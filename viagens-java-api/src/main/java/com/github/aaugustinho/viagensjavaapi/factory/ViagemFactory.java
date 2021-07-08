package com.github.aaugustinho.viagensjavaapi.factory;

import com.github.aaugustinho.viagensjavaapi.model.Viagem;

public interface ViagemFactory {

	Viagem criarViagem(String tipo);
}
