package br.com.quinino.service;

import br.com.quinino.domain.requests.FareEstimateRequest;
import br.com.quinino.domain.responses.FareEstimateResponse;

public interface FareEstimateService {

    FareEstimateResponse getEstimate(FareEstimateRequest request);
}
