package com.wesdom.rocio.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ImageDescriptor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 600)
    private String imageLink;

    @ManyToOne(targetEntity = Request.class)
    private Request request;
}
