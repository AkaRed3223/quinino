package br.com.quinino.service;

import br.com.quinino.domain.Plan;
import br.com.quinino.repository.PlansDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlansService {

    @Autowired
    private PlansDAO plansDAO;

    public List<Plan> getPlans() {
        return plansDAO.findAllPlans();
    }

    public Plan getPlan(String plan) {
        return plansDAO.findPlanByName(plan);
    }
}
