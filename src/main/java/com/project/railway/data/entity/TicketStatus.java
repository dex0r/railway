package com.project.railway.data.entity;

import javax.persistence.*;

@Entity
public class TicketStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @OneToMany(mappedBy = "ticketstatus", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }
}
