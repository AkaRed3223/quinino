package br.com.quinino.controller;

import br.com.quinino.domain.Fare;
import br.com.quinino.domain.requests.FareCalculationRequest;
import br.com.quinino.domain.responses.FareCalculationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/fares")
public interface FaresController {
    @GetMapping()
    ResponseEntity<List<Fare>> getAllFares();

    @GetMapping(path = "/{origin}/{destination}")
    ResponseEntity<Fare> getFare(@PathVariable String origin, @PathVariable String destination);

    @PostMapping(path = "/calculate")
    ResponseEntity<FareCalculationResponse> calculateFare(@RequestBody FareCalculationRequest request);
}
