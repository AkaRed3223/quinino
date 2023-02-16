package br.com.quinino.service;

import br.com.quinino.domain.Plan;
import br.com.quinino.repository.PlansDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlansService {

    private final PlansDAO plansDAO;

    public PlansService(PlansDAO plansDAO) {
        this.plansDAO = plansDAO;
    }

    public List<Plan> getPlans() {
        return plansDAO.findAllPlans();
    }

    public Plan getPlan(String plan) {
        return plansDAO.findPlanByName(plan);
    }
}
