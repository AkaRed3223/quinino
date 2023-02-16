package br.com.quinino.domain.requests;

public record FareCalculationRequest(
        String origin,
        String destination,
        int duration,
        String plan) {
}
