package br.com.quinino.service;

import br.com.quinino.domain.Plan;

import java.util.List;

public interface PlansService {
    List<Plan> getPlans();

    Plan getPlan(String plan);
}
