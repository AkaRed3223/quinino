package br.com.quinino.domain.requests;

import br.com.quinino.domain.enums.Plans;

public record FareEstimateRequest(
        String origin,
        String destination,
        int duration,
        Plans plan) {
}
