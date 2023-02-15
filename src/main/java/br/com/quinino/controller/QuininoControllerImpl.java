package br.com.quinino.controller;

import br.com.quinino.domain.Plan;
import br.com.quinino.domain.requests.FareEstimateRequest;
import br.com.quinino.domain.responses.FareEstimateResponse;
import br.com.quinino.service.FareCalculationService;
import br.com.quinino.service.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuininoControllerImpl implements QuininoController {

    @Autowired
    private FareCalculationService fareCalculationService;

    @Autowired
    private PlansService plansService;

    @Override
    public ResponseEntity<FareEstimateResponse> findEstimate(FareEstimateRequest request) {
        return ResponseEntity.ok(fareCalculationService.getEstimate(request));
    }

    @Override
    public ResponseEntity<List<Plan>> getPlans() {
        return ResponseEntity.ok(plansService.getPlans());
    }

    public ResponseEntity<Integer> getMinutesInPlan(String plan) {
        return ResponseEntity.ok(plansService.getMinutesInPlan(plan));
    }
}
