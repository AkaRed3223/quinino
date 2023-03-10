package br.com.quinino.service;

import br.com.quinino.domain.requests.FareCalculationRequest;
import br.com.quinino.domain.responses.FareCalculationResponse;
import br.com.quinino.repository.FaresDAO;
import br.com.quinino.repository.PlansDAO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FareCalculationServiceImpl implements FareCalculationService {

    private final FaresDAO FaresDAO;
    private final PlansDAO PlansDAO;

    public FareCalculationServiceImpl(FaresDAO FaresDAO, PlansDAO PlansDAO) {
        this.FaresDAO = FaresDAO;
        this.PlansDAO = PlansDAO;
    }

    @Override
    public FareCalculationResponse calculateFare(FareCalculationRequest request) {
        var plan = PlansDAO.findPlanByName(request.plan());
        if (plan == null) return new FareCalculationResponse(null, null);

        var fare = FaresDAO.findFareByOriginAndDestination(request.origin(), request.destination());
        if (fare == null) return new FareCalculationResponse(null, null);

        int minutesInPlan = plan.minutes();
        int duration = request.duration();

        var ratePerMinute = fare.fare();

        final int DECIMAL_PLACES = 2;
        return new FareCalculationResponse(
                calculateFaleMais(minutesInPlan, duration, ratePerMinute).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP),
                calculateFaleMais(duration, ratePerMinute).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
    }

    private BigDecimal calculateFaleMais(Integer duration, BigDecimal minuteRate) {
        return calculateFaleMais(0, duration, minuteRate);
    }

    private BigDecimal calculateFaleMais(int minutesInPlan, int duration, BigDecimal minuteRate) {
        if (duration < minutesInPlan) return BigDecimal.ZERO;

        var calculatedValue = new BigDecimal(duration - minutesInPlan).multiply(minuteRate);
        if (minutesInPlan == 0) return calculatedValue;

        final var MULTIPLIER_TAX = "1.1";
        return calculatedValue.multiply(new BigDecimal(MULTIPLIER_TAX)); //calculated value + 10% tax
    }
}
