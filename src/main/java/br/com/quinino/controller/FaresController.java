package br.com.quinino.controller;

import br.com.quinino.domain.Fare;
import br.com.quinino.domain.requests.FareCalculationRequest;
import br.com.quinino.domain.responses.FareCalculationResponse;
import br.com.quinino.service.FareCalculationService;
import br.com.quinino.service.FaresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fares")
public class FaresController {

    @Autowired
    private FaresService faresService;

    @Autowired
    private FareCalculationService fareCalculationService;

    @GetMapping()
    public ResponseEntity<List<Fare>> getAllFares() {
        return ResponseEntity.ok(faresService.getFares());
    }

    @GetMapping(path = "/{origin}/{destination}")
    public ResponseEntity<Fare> getFare(@PathVariable String origin, @PathVariable String destination) {
        Fare response = faresService.getFare(origin, destination);
        if (response == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/calculate")
    public ResponseEntity<FareCalculationResponse> calculateFare(@RequestBody FareCalculationRequest request) {
        return ResponseEntity.ok(fareCalculationService.calculateFare(request));
    }
}
