package br.com.quinino.domain;

import java.math.BigDecimal;

public record Fare(String origin, String destination, BigDecimal fare) {
}
