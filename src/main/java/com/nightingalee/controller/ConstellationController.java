package com.nightingalee.controller;

import com.nightingalee.exception.NewException;
import com.nightingalee.model.Constellations;
import com.nightingalee.service.ConstellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/constellations")
public class ConstellationController {

    @Autowired
    private ConstellationService constellationService;


    @PostMapping("/add")
    public Constellations add(@RequestBody Constellations con) {
        return constellationService.addCon(con);
    }

    @DeleteMapping("/remove/{id]")
    public void remove(@PathVariable String id) {
        constellationService.removeCon(id);
    }

    @GetMapping("/find")
    public List<Constellations> find() {
        return constellationService.findCon();
    }

    @PostMapping("/update/{id}/{dec}/{ra}")
    public Constellations update(@PathVariable String id, @PathVariable double dec, @PathVariable double ra) throws NewException {
        return constellationService.updateCon(id, dec, ra);
    }

    @PostMapping("/addStar/{starId}/{nazwa}")
    public Constellations addStar(@PathVariable Long starId, @PathVariable String nazwa) throws NewException {
        return constellationService.changeStarConstellation(starId, nazwa);

    }

    @GetMapping("/longestNazwa")
    public String longest() {
        return constellationService.longestNazwaOfConstellation();
    }

    @GetMapping("/highestStar")
    public String highest() {
        return constellationService.nameOfHighestConstellation();
    }

    @GetMapping("/brighestStar")
    public String brighest() {
        return constellationService.nameOfConstelationHasBrihtestStar();
    }


}
