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
public class PlansController {

    @Autowired
    private PlansService plansService;

    @GetMapping()
    public ResponseEntity<List<Plan>> getPlans() {
        return ResponseEntity.ok(plansService.getPlans());
    }

    @GetMapping(path = "/{plan}")
    public ResponseEntity<Plan> getMinutesInPlan(@PathVariable String plan) {
        Plan response = plansService.getPlan(plan);
        if (response == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }
}
