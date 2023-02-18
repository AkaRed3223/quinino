package br.com.quinino.service;

import br.com.quinino.domain.Plan;
import br.com.quinino.repository.PlansDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlansServiceImpl implements PlansService {

    @Autowired
    private PlansDAO PlansDAO;

    @Override
    public List<Plan> getPlans() {
        return PlansDAO.findAllPlans();
    }

    @Override
    public Plan getPlan(String plan) {
        return PlansDAO.findPlanByName(plan);
    }
}
