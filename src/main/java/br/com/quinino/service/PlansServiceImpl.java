package br.com.quinino.service;

import br.com.quinino.domain.Plan;
import br.com.quinino.repository.PlansDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlansServiceImpl implements PlansService {

    @Autowired
    private PlansDAO plansDAO;

    @Override
    public List<Plan> getPlans() {
        return plansDAO.getPlans();
    }

    @Override
    public int getMinutesInPlan(String plan) {
        return plansDAO.getMinutesInPlan(plan);
    }
}
