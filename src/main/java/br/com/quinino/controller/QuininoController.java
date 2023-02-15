package br.com.quinino.controller;

import br.com.quinino.domain.Plan;
import br.com.quinino.domain.requests.FareEstimateRequest;
import br.com.quinino.domain.responses.FareEstimateResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/estimate")
public interface QuininoController {

    @GetMapping(path = "/plans", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Plan>> getPlans();

    @GetMapping(path = "/plans/{plan}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> getMinutesInPlan(@PathVariable String plan);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FareEstimateResponse> findEstimate(@RequestBody FareEstimateRequest request);
}
