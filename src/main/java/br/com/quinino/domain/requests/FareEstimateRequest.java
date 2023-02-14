package br.com.quinino.domain.requests;

import br.com.quinino.domain.enums.Plans;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class FareEstimateRequest {

    private String origin;
    private String destination;
    private Integer duration;
    private Plans plan;

}
