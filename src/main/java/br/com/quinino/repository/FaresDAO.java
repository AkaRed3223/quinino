package br.com.quinino.repository;

import br.com.quinino.domain.Fare;

import java.util.List;

public interface FaresDAO {
    List<Fare> findAllFares();

    Fare findFareByOriginAndDestination(String origin, String destination);
}
