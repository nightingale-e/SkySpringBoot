package com.nightingalee.service;


import com.nightingalee.exception.NewException;
import com.nightingalee.model.Constellations;
import com.nightingalee.model.Stars;
import com.nightingalee.repository.ConstellationsRepository;
import com.nightingalee.repository.StarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class ConstellationService {

    @Autowired
    private ConstellationsRepository constellationsRepository;

    @Autowired
    private StarsRepository starsRepository;

    public Constellations addCon(Constellations con) {
        return constellationsRepository.save(con);
    }

    public void removeCon(String id) {
        constellationsRepository.deleteById(id);
    }

    public List<Constellations> findCon() {
        return constellationsRepository.findAll();
    }

    public Constellations updateCon(String id, double dec, double ra) throws NewException {
        Constellations result = null;
        if (constellationsRepository.findById(id).isPresent()) {
            constellationsRepository.findById(id).get().setDeclination(dec);
            constellationsRepository.findById(id).get().setRightAnscesion(ra);
            result = constellationsRepository.findById(id).get();
        } else {
            throw new NewException("Constellation does not exist");
        }
        return result;
    }

    public Constellations changeStarConstellation(Long starId, String name) throws NewException {
        Constellations result = null;
        Stars star;
        List<Stars> stars = new LinkedList<>();
        if (constellationsRepository.findById(name).isPresent()) {
            stars = constellationsRepository.findById(name).get().getStars();
            if (starsRepository.findById(starId).isPresent()) {
                starsRepository.findById(starId).get().setConstellation(constellationsRepository.findById(name).get());
                star = starsRepository.findById(starId).get();
                stars.add(star);
                result = constellationsRepository.findById(name).get();
            } else {
                throw new NewException("Star does not exist, add star");

            }
        } else {
            throw new NewException("Constellation does not exist, add constellation");

        }
        return result;
    }

    public String longestNameOfConstellation() {
        int i = 0;
        String result = new String();
        for (Constellations constellations : constellationsRepository.findAll()) {
            if (i < constellations.getName().length()) {
                result = constellations.getName();
            }
        }
        return result;
    }


    public String nameOfConstellationHasBrightestStar() {
        List<Stars> starsList = starsRepository.findAll();
        starsList.sort((o1, o2) -> o1.getBrightness() > o2.getBrightness() ? 1 : -1);
        Stars star = starsList.get(0);
        return star.getConstellation().getName();

//
//        List<Constellations> constellationsList = constellationsRepo.findAll();
//        constellationsList.sort(((o1, o2) ->
//                o1.getStars().sort(((o11, o21) -> o11.getBrightness() > o21.getBrightness()?1:-1)
//                >o2.getStars().sort((o11, o21) -> o11.getBrightness()>o21.getBrightness());));
////        for (Constellations constellations : constellationsRepo.findAll()) {
//            constellations.

        //      }
//        Stars current = new Stars();
//        String result = new String();
//        for (Constellations constellations : constellationsRepo.findAll()) {
//            List<Stars> stars = constellations.getStars();
//            stars.sort((o1, o2) -> o1.getBrightness()>o2.getBrightness()?1:-1);
//            Stars star = stars.get(0);
//            if(current.getBrightness()>star.getBrightness()){
//                current = star;
//                result = constellations.getName();
//            }
//
//        }
//        return result;
    }

    public String nameOfHighestConstellation() {
        List<Constellations> constellations = constellationsRepository.findAll();
        constellations.sort((o1, o2) -> o1.getDeclination() > o2.getDeclination() ? 1 : -1);
        Constellations c = constellations.get(0);
        return c.getName();
    }

}
