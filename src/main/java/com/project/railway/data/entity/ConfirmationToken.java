package com.project.railway.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String confirmationToken;

    private LocalDate createdDate;

    @OneToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "clientid")
    private Client client;

    public ConfirmationToken(Client client){
        this.client = client;
        this.createdDate = LocalDate.now();
        this.confirmationToken = UUID.randomUUID().toString();
    }

    public Client getClient() {
        return client;
    }

    public Long getId() {
        return id;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }
}
