package br.com.quinino.domain.responses;

import br.com.quinino.domain.requests.FareEstimateRequest;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(setterPrefix = "with")
public class FareEstimateResponse {

    private FareEstimateRequest request;
    private BigDecimal comFaleMais;
    private BigDecimal semFaleMais;
}
