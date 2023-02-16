package br.com.quinino.controller;

import br.com.quinino.domain.Plan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/plans")
public interface PlansController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Plan>> getPlans();

    @GetMapping(path = "/{plan}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Plan> getMinutesInPlan(@PathVariable String plan);
}
