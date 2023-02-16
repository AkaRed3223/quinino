package br.com.quinino.service;

import br.com.quinino.domain.Plan;
import br.com.quinino.domain.requests.FareCalculationRequest;
import br.com.quinino.domain.responses.FareCalculationResponse;
import br.com.quinino.repository.FaresDAO;
import br.com.quinino.repository.PlansDAO;
import br.com.quinino.repository.PlansDAOImpl;
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
class FareEstimateRequestCalculationServiceImplTest {

    @InjectMocks
    private FareCalculationServiceImpl fareEstimateService;

    @Mock
    private FaresDAO faresDAO;

    @Mock
    private PlansDAO plansDAO;

    @Test
    void test1() {

        String origin = "011";
        String destination = "016";
        int duration = 20;
        String plan = "FALEMAIS_30";

        when(faresDAO.getRateByOriginAndDestination(origin, destination)).thenReturn(new BigDecimal("1.90"));
        when(plansDAO.findPlanByName(plan)).thenReturn(new Plan(plan, 30));

        FareCalculationRequest request = new FareCalculationRequest(origin, destination, duration, plan);
        FareCalculationResponse response = fareEstimateService.calculateFare(request);

        assertNotNull(response);
        assertEquals(new BigDecimal("0.00"), response.comFaleMais());
        assertEquals(new BigDecimal("38.00"), response.semFaleMais());
    }

    @Test
    void test2() {

        String origin = "011";
        String destination = "017";
        int duration = 80;
        String plan = "FALEMAIS_60";

        when(faresDAO.getRateByOriginAndDestination(origin, destination)).thenReturn(new BigDecimal("1.70"));
        when(plansDAO.findPlanByName(plan)).thenReturn(new Plan(plan, 60));

        FareCalculationRequest request = new FareCalculationRequest(origin, destination, duration, plan);
        FareCalculationResponse response = fareEstimateService.calculateFare(request);

        assertNotNull(response);
        assertEquals(new BigDecimal("37.40"), response.comFaleMais());
        assertEquals(new BigDecimal("136.00"), response.semFaleMais());
    }

    @Test
    void test3() {

        String origin = "018";
        String destination = "011";
        int duration = 200;
        String plan = "FALEMAIS_120";

        when(faresDAO.getRateByOriginAndDestination(origin, destination)).thenReturn(new BigDecimal("1.90"));
        when(plansDAO.findPlanByName(plan)).thenReturn(new Plan(plan, 120));

        FareCalculationRequest request = new FareCalculationRequest(origin, destination, duration, plan);
        FareCalculationResponse response = fareEstimateService.calculateFare(request);

        assertNotNull(response);
        assertEquals(new BigDecimal("167.20"), response.comFaleMais());
        assertEquals(new BigDecimal("380.00"), response.semFaleMais());
    }
}