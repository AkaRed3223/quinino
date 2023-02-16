package br.com.quinino.service;

import br.com.quinino.domain.Fare;
import br.com.quinino.repository.FaresDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaresService {

    @Autowired
    private FaresDAO faresDAO;

    public List<Fare> getFares() {
        return faresDAO.findAllFares();
    }

    public Fare getFare(String origin, String destination) {
        return faresDAO.findFareByOriginAndDestination(origin, destination);
    }
}
