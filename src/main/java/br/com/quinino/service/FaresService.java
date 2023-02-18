package br.com.quinino.service;

import br.com.quinino.domain.Fare;

import java.util.List;

public interface FaresService {
    List<Fare> getFares();

    Fare getFare(String origin, String destination);
}
