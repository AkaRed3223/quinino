package br.com.quinino.controller;

import br.com.quinino.domain.Plan;
import br.com.quinino.service.PlansService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlansControllerImpl implements PlansController {

    private final PlansService plansService;

    public PlansControllerImpl(PlansService plansService) {
        this.plansService = plansService;
    }

    @Override
    public ResponseEntity<List<Plan>> getPlans() {
        return ResponseEntity.ok(plansService.getPlans());
    }

    @Override
    public ResponseEntity<Plan> getMinutesInPlan(String plan) {
        return ResponseEntity.ok(plansService.getPlan(plan));
    }
}
