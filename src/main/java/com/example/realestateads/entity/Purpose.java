package com.example.realestateads.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "purpose")
public class Purpose {
    @Id
    @Size(max = 50)
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    //TODO [JPA Buddy] generate columns from DB
}