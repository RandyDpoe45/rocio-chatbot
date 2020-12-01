package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.ImageDesciptorViews;
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
    @JsonView({ImageDesciptorViews.BasicView.class})
    private Long id;

    @Column(length = 600)
    @JsonView({ImageDesciptorViews.BasicView.class})
    private String imageLink;

    @ManyToOne(targetEntity = Request.class)
    private Request request;
}
