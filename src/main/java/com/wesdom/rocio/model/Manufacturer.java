package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.ManufacturerViews;
import com.wesdom.rocio.views.RequestViews;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({
            ManufacturerViews.CreateUpdateView.class, ManufacturerViews.BasicView.class,
            RequestViews.BasicView.class})
    private Long id;

    @JsonView({ManufacturerViews.CreateUpdateView.class, ManufacturerViews.BasicView.class,
             RequestViews.BasicView.class})
    @Column(length = 300)
    private String names;

    @JsonView({ManufacturerViews.CreateUpdateView.class, ManufacturerViews.BasicView.class,
            RequestViews.BasicView.class})
    @Column(length = 500)
    private String lastNames;


    @JsonView({ManufacturerViews.CreateUpdateView.class, ManufacturerViews.BasicView.class,
            RequestViews.CreateUpdateView.class, RequestViews.BasicView.class})
    private String phone;

    @JsonView({ManufacturerViews.CreateUpdateView.class, ManufacturerViews.BasicView.class})
    private String role;

    @JsonView({ManufacturerViews.CreateUpdateView.class, ManufacturerViews.BasicView.class})
    private Double commitment;
}
