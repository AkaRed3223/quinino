package br.com.quinino.service;

import br.com.quinino.domain.requests.FareCalculationRequest;
import br.com.quinino.domain.responses.FareCalculationResponse;

public interface FareCalculationService {
    FareCalculationResponse calculateFare(FareCalculationRequest request);
}
