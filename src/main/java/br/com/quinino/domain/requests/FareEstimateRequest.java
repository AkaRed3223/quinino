package br.com.quinino.domain.requests;

public record FareEstimateRequest(
        String origin,
        String destination,
        int duration,
        String plan) {
}
