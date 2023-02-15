package br.com.quinino.service;

import br.com.quinino.domain.requests.FareEstimateRequest;
import br.com.quinino.domain.responses.FareEstimateResponse;
import br.com.quinino.repository.FaresDAO;
import br.com.quinino.repository.PlansDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FareCalculationServiceImpl implements FareCalculationService {

    @Autowired
    private FaresDAO faresDAO;

    @Autowired
    private PlansDAO plansDAO;

    @Override
    public FareEstimateResponse getEstimate(FareEstimateRequest request) {
        final int minutesInPlan = plansDAO.getMinutesInPlan(request.plan());
        final int duration = request.duration();

        BigDecimal ratePerMinute = faresDAO.getMinuteRate(request.origin(), request.destination());

        final int DECIMAL_PLACES = 2;
        return new FareEstimateResponse(
                calculateFaleMais(minutesInPlan, duration, ratePerMinute).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP),
                calculateFaleMais(duration, ratePerMinute).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP));
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
