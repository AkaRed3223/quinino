package br.com.quinino.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FaresDAOImpl implements FaresDAO {

    @Override
    public BigDecimal getMinuteRate(String origin, String destination) {
        Map<String, BigDecimal> map = buildMap();
        String key = origin + "-" + destination;
        BigDecimal value = map.get(key);
        return value != null ? value : BigDecimal.ZERO;
    }

    private Map<String, BigDecimal> buildMap() {
        Map<String, BigDecimal> ratesMap = new HashMap<>();
        ratesMap.put("011-016", new BigDecimal("1.90"));
        ratesMap.put("016-011", new BigDecimal("2.90"));
        ratesMap.put("011-017", new BigDecimal("1.70"));
        ratesMap.put("017-011", new BigDecimal("2.70"));
        ratesMap.put("011-018", new BigDecimal("0.90"));
        ratesMap.put("018-011", new BigDecimal("1.90"));
        return ratesMap;
    }
}
