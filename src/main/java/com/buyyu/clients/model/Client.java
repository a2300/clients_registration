package com.buyyu.clients.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    @Getter
    private UUID clientId;

    @Getter
    private String name;
    @Getter
    private String address;

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
