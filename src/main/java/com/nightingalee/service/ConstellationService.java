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


@Service
public class ConstellationService {

    @Autowired
    private ConstellationsRepo constellationsRepo;

    @Autowired
    private StarsRepo starsRepo;

    public Constellations addCon(Constellations con) {
        return constellationsRepo.save(con);
    }

    public void removeCon(String id) {
        constellationsRepo.deleteById(id);
    }

    public List<Constellations> findCon() {
        return constellationsRepo.findAll();
    }

    public Constellations updateCon(String id, double dec, double ra) throws NewException {
        Constellations result;
        if (constellationsRepo.findById(id).isPresent()) {
            constellationsRepo.findById(id).get().setDeclination(dec);
            constellationsRepo.findById(id).get().setRightAnscesion(ra);
            result = constellationsRepo.findById(id).get();
        } else {
            throw new NewException("Constellation does not exist");
        }
        return result;
    }

    public Constellations changeStarConstellation(Long starId, String nazwa) throws NewException {
        Constellations result;
        Stars star;
        List<Stars> stars = new ArrayList<>();
        if (constellationsRepo.findById(nazwa).isPresent()) {
            result = constellationsRepo.findById(nazwa).get();
            stars = constellationsRepo.findById(nazwa).get().getStars();
            if (starsRepo.findById(starId).isPresent()) {
                starsRepo.findById(starId).get().setConstellation(result);
                star = starsRepo.findById(starId).get();
                stars.add(star);
                result = constellationsRepo.findById(nazwa).get();
            } else {
                throw new NewException("star does not exist, add star");

            }
        } else {
            throw new NewException("constellation does not exist, add constellation");

        }
        return result;
    }

    public String longestNazwaOfConstellation() {
        int i = 0;
        String result = new String();
        for (Constellations constellations : constellationsRepo.findAll()) {
            if (i < constellations.getName().length()) {
                result = constellations.getName();
            }
        }
        return result;
    }


    public Constellations nameOfConstelationHasBrihtestStar() {
        List<Stars> starsList = starsRepo.findAll();
        starsList.sort((o1, o2) -> o1.getBrightness() > o2.getBrightness() ? 1 : -1);
        Stars star = starsList.get(0);
        return star.getConstellation();

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
        List<Constellations> constellations = constellationsRepo.findAll();
        constellations.sort((o1, o2) -> o1.getDeclination() > o2.getDeclination() ? 1 : -1);
        Constellations c = constellations.get(0);
        return c.getName();
    }

}
