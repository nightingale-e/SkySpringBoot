package com.nightingalee.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "Constellations")
public class Constellations {

    @Id
    @NotEmpty
    @NotNull
    private String name;

    @Column(name = "RIGHT_ANSCESION")
    private double rightAnscesion; //RIGHT_ANSCESION
    private double declination;

    @JsonBackReference
    @OneToMany(mappedBy = "constellation", cascade = CascadeType.REMOVE)
    private List<Stars> stars;

    public Constellations(@NotEmpty @NotNull String name) {
        this.name = name;
    }

    public Constellations() {
    }

    public Constellations(@NotEmpty @NotNull String name, double rightAnscesion, double declination) {
        this.name = name;
        this.rightAnscesion = rightAnscesion;
        this.declination = declination;
    }

    public String getName() {
        return name;
    }

    public double getRightAnscesion() {
        return rightAnscesion;
    }

    public double getDeclination() {
        return declination;
    }

    public List<Stars> getStars() {
        return stars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRightAnscesion(double rightAnscesion) {
        this.rightAnscesion = rightAnscesion;
    }

    public void setDeclination(double declination) {
        this.declination = declination;
    }

    public void setStars(List<Stars> stars) {
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Constellations)) return false;
//
//        Constellations that = (Constellations) o;
//
//        if (Double.compare(that.rightAnscesion, rightAnscesion) != 0) return false;
//        if (Double.compare(that.declination, declination) != 0) return false;
//        return name != null ? name.equals(that.name) : that.name == null;
        boolean result = false;
        Constellations xxx = (Constellations) o;
        if (xxx.getDeclination() == declination && xxx.getRightAnscesion() == rightAnscesion) {
            result = true;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(rightAnscesion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(declination);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
