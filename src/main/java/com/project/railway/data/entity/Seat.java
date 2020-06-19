package com.project.railway.data.entity;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatID")
    private Long id;

    private String seatName;

    private String compartmentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TrainID", nullable = false)
    private Train train;

    @OneToMany(mappedBy = "space", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)

    public Train getTrain() {
        return train;
    }

    public Long getId(){
        return id;
    }

    public void setTrain(Train train){
        this.train = train;
    }

    public String getCompartmentType() {
        return compartmentType;
    }

    public void setCompartmentType(String compartmentType) {
        this.compartmentType = compartmentType;
    }

    public Seat(){
    }

    public Seat(Long id, String compartmentType, Train train){
        this.id = id;
        this.compartmentType = compartmentType;
        this.train = train;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    @Override
    public String toString() {
        return "{" +
                "\"seatName\":\"" + seatName + '\"' +
                ", \"compartmentType\":\"" + compartmentType + '\"' +
                ", \"train\":\"" + train.getId() + "\"" +
                '}';
    }

    public String getJSON(){
        return "{" +
                "\"seatid\":\"" + seatName + '\"' +
                ", \"compartmentType\":\"" + compartmentType + '\"' +
                ", \"train\":\"" + train.getId() + "\"" +
                '}';
    }
}
