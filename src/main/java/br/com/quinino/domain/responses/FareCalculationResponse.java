package br.com.quinino.domain.responses;

import java.math.BigDecimal;

public record FareCalculationResponse(
        BigDecimal comFaleMais,
        BigDecimal semFaleMais) {
}
