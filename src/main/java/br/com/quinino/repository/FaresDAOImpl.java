package br.com.quinino.repository;

import br.com.quinino.domain.Fare;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FaresDAOImpl implements FaresDAO {

    @Override
    public List<Fare> findAllFares() {
        return new ArrayList<>(buildFareMap().values());
    }

    @Override
    public Fare findFareByOriginAndDestination(String origin, String destination) {
        return buildFareMap().get(buildMapKey(origin, destination));
    }

    private Map<String, Fare> buildFareMap() {
        Map<String, Fare> ratesMap = new HashMap<>();
        ratesMap.put("011-016", new Fare("011", "016", new BigDecimal("1.90")));
        ratesMap.put("016-011", new Fare("016", "011", new BigDecimal("2.90")));
        ratesMap.put("011-017", new Fare("011", "017", new BigDecimal("1.70")));
        ratesMap.put("017-011", new Fare("017", "011", new BigDecimal("2.70")));
        ratesMap.put("011-018", new Fare("011", "018", new BigDecimal("0.90")));
        ratesMap.put("018-011", new Fare("018", "011", new BigDecimal("1.90")));
        return ratesMap;
    }

    private String buildMapKey(String origin, String destination) {
        return origin + "-" + destination;
    }
}
