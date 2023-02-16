package br.com.quinino.repository;

import br.com.quinino.domain.Plan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlansDAOImpl implements PlansDAO {

    @Override
    public List<Plan> findAllPlans() {
        List<Plan> plans = new ArrayList<>();
        List.of(PlansEnum.values()).forEach(planEnum -> plans.add(new Plan(planEnum.name(), planEnum.getValue())));
        return plans;
    }

    public Plan findPlanByName(String planName) {
        try {
            PlansEnum plan = PlansEnum.valueOf(planName);
            return new Plan(plan.name(), plan.getValue());
        } catch (Exception e) {
            throw new EnumConstantNotPresentException(PlansEnum.class, planName);
        }
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
