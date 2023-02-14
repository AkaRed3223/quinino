package br.com.quinino.repository;

import java.math.BigDecimal;

public interface FareEstimateDAO {

    BigDecimal getMinuteRate(String origin, String destination);
}
