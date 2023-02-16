package br.com.quinino.controller;

import br.com.quinino.domain.requests.FareCalculationRequest;
import br.com.quinino.domain.responses.FareCalculationResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/fares")
public interface FaresController {

    @PostMapping(path = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FareCalculationResponse> calculateFare(@RequestBody FareCalculationRequest request);
}
