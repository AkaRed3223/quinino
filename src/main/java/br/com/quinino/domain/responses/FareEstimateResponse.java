package br.com.quinino.domain.responses;

import br.com.quinino.domain.requests.FareEstimateRequest;

import java.math.BigDecimal;

public record FareEstimateResponse(
        FareEstimateRequest request,
        BigDecimal comFaleMais,
        BigDecimal semFaleMais) {
}
