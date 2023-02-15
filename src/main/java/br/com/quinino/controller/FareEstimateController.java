package br.com.quinino.controller;

import br.com.quinino.domain.Plan;
import br.com.quinino.domain.enums.Plans;
import br.com.quinino.domain.responses.FareEstimateResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/estimate")
public interface FareEstimateController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FareEstimateResponse> findEstimate(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam Integer duration);


    @GetMapping(path = "/plans", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Plan>> getPlans();
}
