package br.com.quinino.service;

import br.com.quinino.domain.Plan;
import br.com.quinino.repository.PlansDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlansServiceImpl implements PlansService {

    private final PlansDAO plansDAO;

    public PlansServiceImpl(PlansDAO plansDAO) {
        this.plansDAO = plansDAO;
    }

    @Override
    public List<Plan> getPlans() {
        return plansDAO.findAllPlans();
    }

    @Override
    public Plan getPlan(String plan) {
        return plansDAO.findPlanByName(plan);
    }
}
