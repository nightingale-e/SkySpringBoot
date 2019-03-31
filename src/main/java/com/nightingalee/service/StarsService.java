package com.nightingalee.service;

import com.nightingalee.exception.NewException;
import com.nightingalee.model.Constellations;
import com.nightingalee.model.Stars;
import com.nightingalee.repository.ConstellationsRepository;
import com.nightingalee.repository.StarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarsService {

    @Autowired
    private StarsRepository starsRepository;

    @Autowired
    private ConstellationsRepository constellationsRepository;


    public Stars addSta(Stars star) throws NewException {
        if (!starsRepository.findById(star.getStarId()).isPresent()) {
            return starsRepository.save(star);
        } else throw new NewException("Star already exists");
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
        Stars result = starsRepository.getOne(id);
        if (result.getStarId() == id) {
            result.setBrightness(brightness);
            starsRepository.save(result);
        } else {
            throw new NewException("Id number does not exist");
        }
        return result;
    }

    public List<String> constellationNameContainingX() {
       // List<String> s = new ArrayList();
        //  List<Constellations> list = constellationsRepository.findAll();

        List<String> list = constellationsRepository.findAll()
                .stream()
                .filter(c -> "x".contains(c.getName()))
                .map(Constellations::getName)
                .collect(Collectors.toList());

        return list;
    }

    public String changePolarisPosition(double dec) throws NewException {
        if (dec > 90 || dec < 0) {
            Stars star = starsRepository.findByNameContaining("Polaris");
            Optional<Constellations> con = constellationsRepository.findById(star.getConstellation().getName());
            con.get().setDeclination(dec);
        } else throw new NewException("Wrong dec value");
        return "Twoje położenie to " + dec + " " + "szerokości geograficznej";
    }

    public List<Stars> longestConstellationNameStarsList() {

        List<Stars> list = constellationsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Constellations::getName))
                .limit(1).flatMap(n -> n.getStars().stream()).collect(Collectors.toList());

        return list;
    }

}
