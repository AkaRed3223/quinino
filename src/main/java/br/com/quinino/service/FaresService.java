package br.com.quinino.service;

import br.com.quinino.domain.Fare;
import br.com.quinino.repository.FaresDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaresService {

    private final FaresDAO faresDAO;

    public FaresService(FaresDAO faresDAO) {
        this.faresDAO = faresDAO;
    }

    public List<Fare> getFares() {
        return faresDAO.findAllFares();
    }

    public Fare getFare(String origin, String destination) {
        return faresDAO.findFareByOriginAndDestination(origin, destination);
    }
}
