/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.xyinc.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Marcos
 */
@Entity(name="poiModel")
public class POIModel implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    private String poi;
    private int coordenada_x;
    private int coordenada_y;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoi() {
        return poi;
    }

    public void setPoi(String poi) {
        this.poi = poi;
    }

    public int getCoordenada_x() {
        return coordenada_x;
    }

    public void setCoordenada_x(int coordenada_x) {
        this.coordenada_x = coordenada_x;
    }

    public int getCoordenada_y() {
        return coordenada_y;
    }

    public void setCoordenada_y(int coordenada_y) {
        this.coordenada_y = coordenada_y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.poi);
        hash = 59 * hash + this.coordenada_x;
        hash = 59 * hash + this.coordenada_y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final POIModel other = (POIModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.coordenada_x != other.coordenada_x) {
            return false;
        }
        if (this.coordenada_y != other.coordenada_y) {
            return false;
        }
        if (!Objects.equals(this.poi, other.poi)) {
            return false;
        }
        return true;
    }            
}
