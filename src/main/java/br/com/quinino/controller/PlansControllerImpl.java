package br.com.quinino.controller;

import br.com.quinino.domain.Plan;
import br.com.quinino.service.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlansControllerImpl implements PlansController {

    @Autowired
    private PlansService plansService;

    @Override
    @GetMapping()
    public ResponseEntity<List<Plan>> getPlans() {
        return ResponseEntity.ok(plansService.getPlans());
    }

    @Override
    @GetMapping(path = "/{plan}")
    public ResponseEntity<Plan> getMinutesInPlan(@PathVariable String plan) {
        return ResponseEntity.ok(plansService.getPlan(plan));
    }
}
