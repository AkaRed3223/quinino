package br.com.quinino.controller;

import br.com.quinino.domain.enums.Plans;
import br.com.quinino.domain.requests.FareEstimateRequest;
import br.com.quinino.domain.responses.FareEstimateResponse;
import br.com.quinino.service.FareEstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FareEstimateControllerImpl implements FareEstimateController {

    @Autowired
    private FareEstimateService fareEstimateService;

    @Override
    public ResponseEntity<FareEstimateResponse> findEstimate(String origin, String destination, Integer duration, Plans plan) {

        FareEstimateRequest request = new FareEstimateRequest(origin, destination, duration, plan);
        FareEstimateResponse response = fareEstimateService.getEstimate(request);

        return ResponseEntity.ok(response);
    }
}
