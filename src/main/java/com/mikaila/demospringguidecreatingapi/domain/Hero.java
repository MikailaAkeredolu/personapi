package com.mikaila.demospringguidecreatingapi.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Hero {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @ElementCollection
    @CollectionTable(name = "HERO_SPECIAL_POWERS", joinColumns = @JoinColumn(name = "HERO_ID"))
    @Column(name="SPECIAL_POWERS")
    private Set<String> specialPowers = new HashSet<>();

    public Hero() {

    }

    public Hero(String name, Set<String> specialPowers) {
        this.name = name;
        this.specialPowers = specialPowers;
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

    public Set<String> getSpecialPowers() {
        return specialPowers;
    }

    public void setSpecialPowers(Set<String> specialPowers) {
        this.specialPowers = specialPowers;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialPowers=" + specialPowers +
                '}';
    }


}
