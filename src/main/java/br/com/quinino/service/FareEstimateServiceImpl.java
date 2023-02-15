package br.com.quinino.service;

import br.com.quinino.domain.requests.FareEstimateRequest;
import br.com.quinino.domain.responses.FareEstimateResponse;
import br.com.quinino.repository.FareEstimateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FareEstimateServiceImpl implements FareEstimateService {

    @Autowired
    private FareEstimateDAO fareEstimateDAO;

    @Override
    public FareEstimateResponse getEstimate(FareEstimateRequest request) {

        BigDecimal ratePerMinute = fareEstimateDAO.getMinuteRate(request.origin(), request.destination());
        final int minutesInPlan = request.plan().getValue();
        final int duration = request.duration();

        final int DECIMAL_PLACES = 2;
        BigDecimal valueWithPlan = calculateFaleMais(minutesInPlan, duration, ratePerMinute).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
        BigDecimal valueWithoutPlan = calculateFaleMais(duration, ratePerMinute).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);

        return new FareEstimateResponse(request, valueWithPlan, valueWithoutPlan);
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
