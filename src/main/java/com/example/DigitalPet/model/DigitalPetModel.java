package com.example.DigitalPet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DigitalPetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isDead;
    private Integer health;
    private Integer hunger;
    private Integer energy;
    private Integer hygiene;

    public DigitalPetModel(){}
    public DigitalPetModel(String name, Integer health, Integer hunger, Integer energy, Integer hygiene){
        this.name = name;
        this.health = health;
        this.hunger = hunger;
        this.energy = energy;
        this.hygiene = hygiene;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getHunger() {
        return hunger;
    }

    public void setHunger(Integer hunger) {
        this.hunger = hunger;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getHygiene() {
        return hygiene;
    }

    public void setHygiene(Integer hygiene) {
        this.hygiene = hygiene;
    }

    @Override
    public String toString() {
        return "DigitalPetModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDead=" + isDead +
                ", health=" + health +
                ", hunger=" + hunger +
                ", energy=" + energy +
                ", hygiene=" + hygiene +
                '}';
    }
}
