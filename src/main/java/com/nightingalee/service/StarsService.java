package com.nightingalee.service;

import com.nightingalee.exception.NewException;
import com.nightingalee.model.Constellations;
import com.nightingalee.model.Stars;
import com.nightingalee.repository.ConstellationsRepo;
import com.nightingalee.repository.StarsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StarsService {

    @Autowired
    private StarsRepo starsRepo;

    @Autowired
    private ConstellationsRepo constellationsRepo;


    public Stars addSta(Stars star) {
        return starsRepo.save(star);
    }

    public void removeSta(Long id) {
        starsRepo.deleteById(id);
    }

    public List<Stars> findSta() {
        return starsRepo.findAll();
    }

    public Stars updateSta(Long id, String name) throws NewException {
        Stars result;
        if (starsRepo.findById(id).isPresent()) {
            result = starsRepo.findById(id).get();
        } else {
            throw new NewException("Id number does not exist");

        }
        return result;
    }

    public Stars changeBrightness(Long id, double brightness) throws NewException {
        Stars result = new Stars();
        if (starsRepo.findById(id).isPresent()) {
            starsRepo.findById(id).get().setBrightness(brightness);
            result = starsRepo.findById(id).get();
        } else {
            throw new NewException("Id number does not exist");
        }
        return result;
    }

    public List<String> nameContainingX() {
        List<String> s = new ArrayList();
        for (Stars stars : starsRepo.findAll()) {
            if (stars.getNazwa().contains("x")) {
                s.add(stars.getNazwa());
            }
        }
        return s;
    }

    public String changePolarisPosition(double dec) {
        if (dec > 90 || dec < 0) {
            Stars star = starsRepo.findByNazwaContaining("Polaris");
            Optional<Constellations> con = constellationsRepo.findById(star.getConstellation().getName());
            con.get().setDeclination(dec);
        }
        return "Twoje położenie to " + dec + " " + "szeroości geograficznej";
    }

    public List<Stars> longestConstellationNameStars() {
        int i = 0;
        List<Stars> result = new ArrayList<>();
        for (Constellations constellations : constellationsRepo.findAll()) {
            if (i < constellations.getName().length()) {
                result = constellations.getStars();
            }
        }
        return result;
    }

}
