package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Location {

    @Id
    @SequenceGenerator(
            name = "location_sequence",
            sequenceName = "location_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "location_sequence"
    )

    private long id;
    private String name;
    @ElementCollection
    private List<String> exemption;
    private Double tax;

    public Location() {
    }

    public Location(String name, List<String> exemption, Double tax) {
        this.name = name;
        this.exemption = exemption;
        this.tax = tax;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getExemption() {
        return exemption;
    }

    public void setExemption(List<String> exemption) {
        this.exemption = exemption;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", exemption=" + exemption +
                ", tax=" + tax +
                '}';
    }
}
