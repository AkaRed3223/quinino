package br.com.quinino.repository;

import java.math.BigDecimal;

public interface FaresDAO {

    BigDecimal getRateByOriginAndDestination(String origin, String destination);
}
