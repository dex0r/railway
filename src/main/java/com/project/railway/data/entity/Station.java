package com.project.railway.data.entity;

import javax.persistence.*;

@Entity
public class Station implements Comparable<Station> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StationID")
    private Long id;
    @Column(name = "StationName")
    private String stationName;
    @Column(name="LatinStationName")
    private String latinStationName;

    @OneToMany(mappedBy = "station", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)

    public String getStationName() {
        return stationName;
    }

    public String getLatinStationName(){return latinStationName;}

    public Long getId(){return id;}

    @Override
    public int compareTo(Station o) {
        return this.getStationName().compareTo(o.getStationName());
    }
}
