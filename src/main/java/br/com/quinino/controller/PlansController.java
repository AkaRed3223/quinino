package br.com.quinino.controller;

import br.com.quinino.domain.Plan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/plans")
public interface PlansController {
    @GetMapping()
    ResponseEntity<List<Plan>> getPlans();

    @GetMapping(path = "/{plan}")
    ResponseEntity<Plan> getMinutesInPlan(@PathVariable String plan);
}
