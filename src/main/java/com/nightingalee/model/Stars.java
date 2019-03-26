package com.nightingalee.model;


import javax.persistence.*;

@Entity(name = "Stars")
public class Stars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long starId;
    private String name;
    private double brightness;

    //@JsonManagedReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Constellations constellation;

    public Stars(String name, double brightness) {
        this.name = name;
        this.brightness = brightness;
    }

    public Stars() {
    }

    public void setStarId(Long starId) {
        this.starId = starId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }


    public void setConstellation(Constellations constellation) {
        this.constellation = constellation;
    }

    public Long getStarId() {
        return starId;
    }

    public String getName() {
        return name;
    }

    public double getBrightness() {
        return brightness;
    }


    public Constellations getConstellation() {
        return constellation;
    }


}
