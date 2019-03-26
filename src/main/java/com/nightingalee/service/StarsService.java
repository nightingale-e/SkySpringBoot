package com.nightingalee.service;

import com.nightingalee.exception.NewException;
import com.nightingalee.model.Constellations;
import com.nightingalee.model.Stars;
import com.nightingalee.repository.ConstellationsRepository;
import com.nightingalee.repository.StarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StarsService {

    @Autowired
    private StarsRepository starsRepository;

    @Autowired
    private ConstellationsRepository constellationsRepository;


    public Stars addSta(Stars star) {
        return starsRepository.save(star);
    }

    public void removeSta(Long id) {
        starsRepository.deleteById(id);
    }

    public List<Stars> findSta() {
        return starsRepository.findAll();
    }

    public Stars updateStarName(Long id, String name) throws NewException {
        Stars result;
        if (starsRepository.findById(id).isPresent()) {
            starsRepository.findById(id).get().setName(name);
            result = starsRepository.findById(id).get();
        } else {
            throw new NewException("Id number does not exist");

        }
        return result;
    }

    public Stars changeBrightness(Long id, double brightness) throws NewException {
        Stars result = new Stars();
        if (starsRepository.findById(id).isPresent()) {
            starsRepository.findById(id).get().setBrightness(brightness);
            result = starsRepository.findById(id).get();
        } else {
            throw new NewException("Id number does not exist");
        }
        return result;
    }

    public List<String> constellationNameContainingX() {
        List<String> s = new ArrayList();
        for (Stars stars : starsRepository.findAll()) {
            if (stars.getName().contains("x")) {
                s.add(stars.getName());
            }
        }
        return s;
    }

    public String changePolarisPosition(double dec) {
        if (dec > 90 || dec < 0) {
            Stars star = starsRepository.findByNameContaining("Polaris");
            Optional<Constellations> con = constellationsRepository.findById(star.getConstellation().getName());
            con.get().setDeclination(dec);
        }
        return "Twoje położenie to " + dec + " " + "szeroości geograficznej";
    }

    public List<Stars> longestConstellationNameStarsList() {
        int i = 0;
        List<Stars> result = new ArrayList<>();
        for (Constellations constellations : constellationsRepository.findAll()) {
            if (i < constellations.getName().length()) {
                result = constellations.getStars();
            }
        }
        return result;
    }

}
