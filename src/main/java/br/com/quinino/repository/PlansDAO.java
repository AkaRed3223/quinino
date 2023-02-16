package br.com.quinino.repository;

import br.com.quinino.domain.Plan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlansDAO {

    public List<Plan> findAllPlans() {
        return new ArrayList<>(buildMap().values());
    }

    public Plan findPlanByName(String planName) {
        return buildMap().get(planName);
    }

    private Map<String, Plan> buildMap() {
        Map<String, Plan> planMap = new HashMap<>();
        planMap.put("FALEMAIS_30", new Plan("FALEMAIS_30", 30));
        planMap.put("FALEMAIS_60", new Plan("FALEMAIS_60", 60));
        planMap.put("FALEMAIS_120", new Plan("FALEMAIS_120", 120));
        return planMap;
    }
}
