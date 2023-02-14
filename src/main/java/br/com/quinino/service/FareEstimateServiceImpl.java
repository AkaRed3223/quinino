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

        int minutesInPlan = request.getPlan().getValue();
        BigDecimal minuteRate = fareEstimateDAO.getMinuteRate(request.getOrigin(), request.getDestination());

        BigDecimal comFaleMaisValue = calculateComFaleMais(minutesInPlan, request.getDuration(), minuteRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal semFaleMaisValue = calculateSemFaleMais(request.getDuration(), minuteRate).setScale(2, RoundingMode.HALF_UP);

        return FareEstimateResponse.builder()
                .withRequest(request)
                .withComFaleMais(comFaleMaisValue)
                .withSemFaleMais(semFaleMaisValue)
                .build();
    }

    private BigDecimal calculateComFaleMais(int minutesInPlan, int duration, BigDecimal minuteRate) {
        if (duration < minutesInPlan) return BigDecimal.ZERO;

        var a = duration - minutesInPlan;
        var b = new BigDecimal(a).multiply(minuteRate);
        return b.multiply(new BigDecimal("1.1"));
        //TODO usar overload
    }

    private BigDecimal calculateSemFaleMais(int duration, BigDecimal minuteRate) {
        return new BigDecimal(duration).multiply(minuteRate);
        //TODO usar overload
    }
}
