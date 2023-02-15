package br.com.quinino.domain.responses;

import java.math.BigDecimal;

public record FareEstimateResponse(
        BigDecimal comFaleMais,
        BigDecimal semFaleMais) {
}
