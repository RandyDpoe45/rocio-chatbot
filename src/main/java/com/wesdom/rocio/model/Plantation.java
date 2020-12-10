package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.DiagnosisViews;
import com.wesdom.rocio.views.PlantationViews;
import com.wesdom.rocio.views.RequestViews;
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
public class Plantation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({PlantationViews.CreateUpdateView.class,PlantationViews.BasicView.class,
            RequestViews.CreateUpdateView.class, RequestViews.BasicView.class,
            DiagnosisViews.BasicView.class})
    private Long id;

    @JsonView({PlantationViews.CreateUpdateView.class,PlantationViews.BasicView.class,
            DiagnosisViews.BasicView.class})
    @ManyToOne(targetEntity = Manufacturer.class)
    private Manufacturer manufacturer;

    @JsonView({PlantationViews.CreateUpdateView.class,PlantationViews.BasicView.class,
            RequestViews.CreateUpdateView.class, RequestViews.BasicView.class})
    private String name;

    @JsonView({DiagnosisViews.BasicView.class,
            PlantationViews.CreateUpdateView.class,PlantationViews.BasicView.class})
    private String municipality;

    @JsonView({DiagnosisViews.BasicView.class,
            PlantationViews.CreateUpdateView.class,PlantationViews.BasicView.class})
    private String department;

    @JsonView({DiagnosisViews.BasicView.class,
            PlantationViews.CreateUpdateView.class,PlantationViews.BasicView.class})
    private String address;

}
