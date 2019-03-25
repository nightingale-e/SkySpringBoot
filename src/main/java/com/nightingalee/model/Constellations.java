package com.nightingalee.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "Constellations")
public class Constellations {

    @Id
    @NotEmpty
    @NotNull
    private String nazwa;

    private double rightAnscesion; //RIGHT_ANSCESION
    private double declination;

    @JsonBackReference
    @OneToMany(mappedBy = "constellation", cascade = CascadeType.REMOVE)
    private List<Stars> stars;

    public Constellations(@NotEmpty @NotNull String nazwa) {
        this.nazwa = nazwa;
    }

    public Constellations() {
    }

    public Constellations(@NotEmpty @NotNull String nazwa, double rightAnscesion, double declination) {
        this.nazwa = nazwa;
        this.rightAnscesion = rightAnscesion;
        this.declination = declination;
    }

    public String getName() {
        return nazwa;
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

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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
        if (this == o) return true;
        if (!(o instanceof Constellations)) return false;

        Constellations that = (Constellations) o;

        if (Double.compare(that.rightAnscesion, rightAnscesion) != 0) return false;
        if (Double.compare(that.declination, declination) != 0) return false;
        return nazwa != null ? nazwa.equals(that.nazwa) : that.nazwa == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = nazwa != null ? nazwa.hashCode() : 0;
        temp = Double.doubleToLongBits(rightAnscesion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(declination);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
