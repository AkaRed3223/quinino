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

    private final FaresDAO faresDAO;
    private final PlansDAO plansDAO;

    public FareCalculationServiceImpl(FaresDAO faresDAO, PlansDAO plansDAO) {
        this.faresDAO = faresDAO;
        this.plansDAO = plansDAO;
    }

    @Override
    public FareCalculationResponse calculateFare(FareCalculationRequest request) {
        int minutesInPlan = getMinutesInPlan(request.plan());
        int duration = request.duration();

        BigDecimal ratePerMinute = faresDAO.getRateByOriginAndDestination(request.origin(), request.destination());

        final int DECIMAL_PLACES = 2;
        return new FareCalculationResponse(
                calculateFaleMais(minutesInPlan, duration, ratePerMinute).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP),
                calculateFaleMais(duration, ratePerMinute).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
    }

    private int getMinutesInPlan(String planName) {
        return plansDAO.findPlanByName(planName).minutes();
    }

    private BigDecimal calculateFaleMais(Integer duration, BigDecimal minuteRate) {
        return calculateFaleMais(0, duration, minuteRate);
    }

    private BigDecimal calculateFaleMais(int minutesInPlan, int duration, BigDecimal minuteRate) {
        if (duration < minutesInPlan) return BigDecimal.ZERO;

        BigDecimal calculatedValue = new BigDecimal(duration - minutesInPlan).multiply(minuteRate);

        if (minutesInPlan == 0) return calculatedValue;

        final String MULTIPLIER_TAX = "1.1";
        return calculatedValue.multiply(new BigDecimal(MULTIPLIER_TAX)); //calculated value + 10% tax
    }
}
