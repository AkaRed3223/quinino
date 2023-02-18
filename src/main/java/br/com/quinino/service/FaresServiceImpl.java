package br.com.quinino.service;

import br.com.quinino.domain.Fare;
import br.com.quinino.repository.FaresDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaresServiceImpl implements FaresService {

    @Autowired
    private FaresDAO FaresDAO;

    @Override
    public List<Fare> getFares() {
        return FaresDAO.findAllFares();
    }

    @Override
    public Fare getFare(String origin, String destination) {
        return FaresDAO.findFareByOriginAndDestination(origin, destination);
    }
}
