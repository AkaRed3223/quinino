package br.com.quinino.repository;

import java.math.BigDecimal;

public interface FaresDAO {

    BigDecimal getMinuteRate(String origin, String destination);
}
