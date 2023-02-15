package br.com.quinino.service;

import br.com.quinino.domain.enums.Plans;
import br.com.quinino.domain.requests.FareEstimateRequest;
import br.com.quinino.domain.responses.FareEstimateResponse;
import br.com.quinino.repository.FareEstimateDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FareEstimateRequestEstimateServiceImplTest {

    @InjectMocks
    private FareEstimateServiceImpl fareEstimateService;

    @Mock
    private FareEstimateDAO fareEstimateDAO;

    @Test
    void test1() {

        String origin = "011";
        String destination = "016";
        int duration = 20;
        Plans plan = Plans.FALEMAIS_30;

        when(fareEstimateDAO.getMinuteRate(origin, destination)).thenReturn(new BigDecimal("1.90"));

        FareEstimateRequest request = new FareEstimateRequest(origin, destination, duration, plan);
        FareEstimateResponse response = fareEstimateService.getEstimate(request);

        assertNotNull(response);
        assertEquals(new BigDecimal("0.00"), response.comFaleMais());
        assertEquals(new BigDecimal("38.00"), response.semFaleMais());
    }

    @Test
    void test2() {

        String origin = "011";
        String destination = "017";
        int duration = 80;
        Plans plan = Plans.FALEMAIS_60;

        when(fareEstimateDAO.getMinuteRate(origin, destination)).thenReturn(new BigDecimal("1.70"));

        FareEstimateRequest request = new FareEstimateRequest(origin, destination, duration, plan);
        FareEstimateResponse response = fareEstimateService.getEstimate(request);

        assertNotNull(response);
        assertEquals(new BigDecimal("37.40"), response.comFaleMais());
        assertEquals(new BigDecimal("136.00"), response.semFaleMais());
    }

    @Test
    void test3() {

        String origin = "018";
        String destination = "011";
        int duration = 200;
        Plans plan = Plans.FALEMAIS_120;

        when(fareEstimateDAO.getMinuteRate(origin, destination)).thenReturn(new BigDecimal("1.90"));

        FareEstimateRequest request = new FareEstimateRequest(origin, destination, duration, plan);
        FareEstimateResponse response = fareEstimateService.getEstimate(request);

        assertNotNull(response);
        assertEquals(new BigDecimal("167.20"), response.comFaleMais());
        assertEquals(new BigDecimal("380.00"), response.semFaleMais());
    }

    /*@Test
    void test4() {

        String origin = "018";
        String destination = "017";
        Integer duration = 100;
        Plans plan = Plans.FALEMAIS_30;

        var request = buildRequest(origin, destination, duration, plan.toString());
        var response = service.getEstimate(request);

        assertEquals(BigDecimal.ZERO, response.getComFaleMais());
        assertEquals(new BigDecimal("38"), response.getSemFaleMais());
    }*/
}