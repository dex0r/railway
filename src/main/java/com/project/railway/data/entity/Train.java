package com.project.railway.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Train")
public class Train {
    @Id
    @Column(name="TrainID")
    private Long id;
    @Column(name="DepartureTime")
    private Timestamp DepartureTime;
    @Column(name = "Seats")
    private Integer Seats;
    @Column(name="Type")
    private String Type;

    private String shortLatinName;

    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)

    public Long getId(){
        return this.id;
    }

    public Timestamp getDepartureTime(){
        return this.DepartureTime;
    }

    public Integer getSeats(){
        return this.Seats;
    }

    public String getType(){
        return this.Type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortLatinName() {
        return shortLatinName;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id:" + id +
                ", Type:'" + Type + '\'' +
                '}';
    }
}
