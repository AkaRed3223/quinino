package br.com.quinino.controller;

import br.com.quinino.domain.requests.FareCalculationRequest;
import br.com.quinino.domain.responses.FareCalculationResponse;
import br.com.quinino.service.FareCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaresControllerImpl implements FaresController {

    private final FareCalculationService fareCalculationService;

    public FaresControllerImpl(FareCalculationService fareCalculationService) {
        this.fareCalculationService = fareCalculationService;
    }

    @Override
    public ResponseEntity<FareCalculationResponse> calculateFare(FareCalculationRequest request) {
        return ResponseEntity.ok(fareCalculationService.calculateFare(request));
    }
}
