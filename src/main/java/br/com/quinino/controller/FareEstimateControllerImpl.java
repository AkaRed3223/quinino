package br.com.quinino.controller;

import br.com.quinino.domain.Plan;
import br.com.quinino.domain.enums.Plans;
import br.com.quinino.domain.requests.FareEstimateRequest;
import br.com.quinino.domain.responses.FareEstimateResponse;
import br.com.quinino.service.FareEstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FareEstimateControllerImpl implements FareEstimateController {

    @Autowired
    private FareEstimateService fareEstimateService;

    @Override
    public ResponseEntity<FareEstimateResponse> findEstimate(String origin, String destination, Integer duration) {

        FareEstimateRequest request = FareEstimateRequest.builder()
                .withOrigin(origin)
                .withDestination(destination)
                .withDuration(duration).build();

        return ResponseEntity.ok(fareEstimateService.getEstimate(request));
    }

    @Override
    public ResponseEntity<List<Plan>> getPlans() {


        return null;
    }
}
