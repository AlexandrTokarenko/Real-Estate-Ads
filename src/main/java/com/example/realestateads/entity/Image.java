package com.example.realestateads.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "realty_id", nullable = false)
    private Realty realty;

    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "image_order")
    private Integer imageOrder;

    public Image(Integer id, String title, Integer imageOrder) {
        this.id = id;
        this.title = title;
        this.imageOrder = imageOrder;
    }

    public Image(Realty realty, String title, Integer imageOrder) {
        this.realty = realty;
        this.title = title;
        this.imageOrder = imageOrder;
    }

    public Image(String title, Integer imageOrder) {
        this.title = title;
        this.imageOrder = imageOrder;
    }
}