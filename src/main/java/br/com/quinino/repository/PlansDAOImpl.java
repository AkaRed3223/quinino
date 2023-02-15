package br.com.quinino.repository;

import br.com.quinino.domain.Plan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlansDAOImpl implements PlansDAO {

    @Override
    public List<Plan> getPlans() {
        List<Plan> plans = new ArrayList<>();
        List.of(PlansEnum.values()).forEach(planEnum -> plans.add(new Plan(planEnum.name(), planEnum.getValue())));
        return plans;
    }

    public int getMinutesInPlan(String plan) {
        return PlansEnum.valueOf(plan).getValue();
    }

    public enum PlansEnum {
        FALEMAIS_30(30),
        FALEMAIS_60(60),
        FALEMAIS_120(120);

        private final int value;

        PlansEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
