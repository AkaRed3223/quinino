package br.com.quinino.repository;

import br.com.quinino.domain.Plan;

import java.util.List;

public interface PlansDAO {

    List<Plan> findAllPlans();

    Plan findPlanByName(String plan);
}
