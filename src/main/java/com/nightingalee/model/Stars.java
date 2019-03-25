package com.nightingalee.model;


import javax.persistence.*;

@Entity(name = "Stars")
public class Stars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long starId;
    private String nazwa;
    private double brightness;

    //@JsonManagedReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Constellations constellation;

    public Stars(String nazwa, double brightness) {
        this.nazwa = nazwa;
        this.brightness = brightness;
    }

    public Stars() {
    }

    public void setStarId(Long starId) {
        this.starId = starId;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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

    public String getNazwa() {
        return nazwa;
    }

    public double getBrightness() {
        return brightness;
    }


    public Constellations getConstellation() {
        return constellation;
    }


}
