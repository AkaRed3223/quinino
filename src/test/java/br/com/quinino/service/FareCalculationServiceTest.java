package br.com.quinino.service;

import br.com.quinino.domain.Fare;
import br.com.quinino.domain.Plan;
import br.com.quinino.domain.requests.FareCalculationRequest;
import br.com.quinino.repository.FaresDAO;
import br.com.quinino.repository.PlansDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FareCalculationServiceTest {

    @InjectMocks
    private FareCalculationService fareEstimateService;

    @Mock
    private FaresDAO faresDAO;

    @Mock
    private PlansDAO plansDAO;

    private static final String GRANDE_SP = "011";
    private static final String RIBEIRAO = "016";
    private static final String SAO_JOSE = "017";
    private static final String PRESIDENTE_PRUDENTE = "018";

    private static final String FALEMAIS_30 = "FALEMAIS_30";
    private static final String FALEMAIS_60 = "FALEMAIS_60";
    private static final String FALEMAIS_120 = "FALEMAIS_120";

    private static final BigDecimal ONE_NINETY = new BigDecimal("1.90");
    private static final BigDecimal ONE_SEVENTY = new BigDecimal("1.70");

    @ParameterizedTest(name = "Should ALWAYS return ZERO when minutes in plan are not exceeded ({0})")
    @DisplayName("Parameterized Tests")
    @MethodSource("parameters")
    void test0(int sameValue) {

        when(faresDAO.findFareByOriginAndDestination(GRANDE_SP, RIBEIRAO)).thenReturn(new Fare(GRANDE_SP, RIBEIRAO, ONE_SEVENTY));
        when(plansDAO.findPlanByName(FALEMAIS_30)).thenReturn(new Plan(FALEMAIS_30, sameValue));

        var response = fareEstimateService.calculateFare(
                new FareCalculationRequest(GRANDE_SP, RIBEIRAO, sameValue, FALEMAIS_30));

        assertNotNull(response);
        assertEquals(new BigDecimal("0.00"), response.comFaleMais());
    }
    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(30),
                Arguments.of(60),
                Arguments.of(120),
                Arguments.of(999)
        );
    }

    @Test
    @DisplayName("Should return 0.00/38.00 for Origin 011/Destination 016/Duration 20")
    void test1() {
        when(faresDAO.findFareByOriginAndDestination(GRANDE_SP, RIBEIRAO)).thenReturn(new Fare(GRANDE_SP, RIBEIRAO, ONE_NINETY));
        when(plansDAO.findPlanByName(FALEMAIS_30)).thenReturn(new Plan(FALEMAIS_30, 30));

        var response = fareEstimateService.calculateFare(
                new FareCalculationRequest(GRANDE_SP, RIBEIRAO, 20, FALEMAIS_30));

        assertNotNull(response);
        assertEquals(new BigDecimal("0.00"), response.comFaleMais());
        assertEquals(new BigDecimal("38.00"), response.semFaleMais());
    }

    @Test
    @DisplayName("Should return 37.40/136.00 for Origin 011/Destination 017/Duration 80")
    void test2() {
        when(faresDAO.findFareByOriginAndDestination(GRANDE_SP, SAO_JOSE)).thenReturn(new Fare(GRANDE_SP, SAO_JOSE, ONE_SEVENTY));
        when(plansDAO.findPlanByName(FALEMAIS_60)).thenReturn(new Plan(FALEMAIS_60, 60));

        var response = fareEstimateService.calculateFare(
                new FareCalculationRequest(GRANDE_SP, SAO_JOSE, 80, FALEMAIS_60));

        assertNotNull(response);
        assertEquals(new BigDecimal("37.40"), response.comFaleMais());
        assertEquals(new BigDecimal("136.00"), response.semFaleMais());
    }

    @Test
    @DisplayName("Should return 167.20/380.00 for Origin 018/Destination 011/Duration 200")
    void test3() {
        when(faresDAO.findFareByOriginAndDestination(PRESIDENTE_PRUDENTE, GRANDE_SP)).thenReturn(new Fare(PRESIDENTE_PRUDENTE, GRANDE_SP, ONE_NINETY));
        when(plansDAO.findPlanByName(FALEMAIS_120)).thenReturn(new Plan(FALEMAIS_120, 120));

        var response = fareEstimateService.calculateFare(
                new FareCalculationRequest(PRESIDENTE_PRUDENTE, GRANDE_SP, 200, FALEMAIS_120));

        assertNotNull(response);
        assertEquals(new BigDecimal("167.20"), response.comFaleMais());
        assertEquals(new BigDecimal("380.00"), response.semFaleMais());
    }

    @Test
    @DisplayName("Should return 0.00/57.00 for Origin 011/Destination 016/Duration 30")
    void test4() {
        when(faresDAO.findFareByOriginAndDestination(GRANDE_SP, RIBEIRAO)).thenReturn(new Fare(GRANDE_SP, RIBEIRAO, ONE_NINETY));
        when(plansDAO.findPlanByName(FALEMAIS_30)).thenReturn(new Plan(FALEMAIS_30, 30));

        var response = fareEstimateService.calculateFare(
                new FareCalculationRequest(GRANDE_SP, RIBEIRAO, 30, FALEMAIS_30));

        assertNotNull(response);
        assertEquals(new BigDecimal("0.00"), response.comFaleMais());
        assertEquals(new BigDecimal("57.00"), response.semFaleMais());
    }

    @Test
    @DisplayName("Should return 1837.11/1898.10 for Origin 011/Destination 016/Duration 999")
    void test5() {
        when(faresDAO.findFareByOriginAndDestination(GRANDE_SP, RIBEIRAO)).thenReturn(new Fare(GRANDE_SP, RIBEIRAO, ONE_NINETY));
        when(plansDAO.findPlanByName(FALEMAIS_120)).thenReturn(new Plan(FALEMAIS_120, 120));

        var response = fareEstimateService.calculateFare(
                new FareCalculationRequest(GRANDE_SP, RIBEIRAO, 999, FALEMAIS_120));

        assertNotNull(response);
        assertEquals(new BigDecimal("1837.11"), response.comFaleMais());
        assertEquals(new BigDecimal("1898.10"), response.semFaleMais());
    }

    @Test
    @DisplayName("Should return (duration * ratePerMinute) whenever (duration == minutesInPlan)")
    void test6() {

        int duration = 30;
        int minutesInPlan = 30;
        var ratePerMinute = new BigDecimal("1.90");

        when(faresDAO.findFareByOriginAndDestination(GRANDE_SP, RIBEIRAO)).thenReturn(new Fare(GRANDE_SP, RIBEIRAO, ratePerMinute));
        when(plansDAO.findPlanByName(FALEMAIS_30)).thenReturn(new Plan(FALEMAIS_30, minutesInPlan));

        var response = fareEstimateService.calculateFare(
                new FareCalculationRequest(GRANDE_SP, RIBEIRAO, minutesInPlan, FALEMAIS_30));

        assertNotNull(response);
        assertEquals(new BigDecimal("0.00"), response.comFaleMais());
        assertEquals(ratePerMinute.multiply(new BigDecimal(duration)), response.semFaleMais());
    }

    @Test
    @DisplayName("Should return no values when origin and destination are uncovered")
    void test7() {
        when(faresDAO.findFareByOriginAndDestination("098", "099")).thenReturn(null);
        when(plansDAO.findPlanByName(FALEMAIS_30)).thenReturn(new Plan(FALEMAIS_30, 30));

        var response = fareEstimateService.calculateFare(
                new FareCalculationRequest("098", "099", 30, FALEMAIS_30));

        assertNotNull(response);
        assertNull(response.comFaleMais());
        assertNull(response.semFaleMais());
    }
}