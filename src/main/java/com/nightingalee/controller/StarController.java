package com.nightingalee.controller;


import com.nightingalee.exception.NewException;
import com.nightingalee.model.Stars;
import com.nightingalee.service.StarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/stars")
public class StarController {

    @Autowired
    private StarsService starsService;

    @PostMapping("/add")
    public Stars add(@RequestBody Stars stars) {
        return starsService.addSta(stars);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable Long id) {
        starsService.removeSta(id);
    }

    @GetMapping("/find")
    public List<Stars> find(){
        return starsService.findSta();
    }

    @PostMapping("/update/{id}/{name}")
    public Stars update(@PathVariable Long id, @PathVariable String name) throws NewException {
        return starsService.updateSta(id,name);
    }

    @PostMapping("/changeBrightness/{id}/{bri}")
    public Stars changeBrightness (@PathVariable Long id, @PathVariable double bri) throws NewException {
        return starsService.changeBrightness(id,bri);
    }

    @GetMapping("/namesContaingX")
    public List<String> namesContain(){
        return starsService.nameContainingX();
    }


    @PostMapping("/currentPosition/{dec}")
    public String currentPosition (@PathVariable double dec) {
        return starsService.changePolarisPosition(dec);
    }

    @GetMapping("/starList")
    public List<Stars> longestNameStars(){
        return starsService.longestConstellationNameStars();
    }



}
