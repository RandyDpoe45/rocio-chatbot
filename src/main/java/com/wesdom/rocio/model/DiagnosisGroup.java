/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.wesdom.rocio.views.DiagnosisGroupViews;
import com.wesdom.rocio.views.RequestViews;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author randy
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DiagnosisGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({RequestViews.CreateUpdateView.class,RequestViews.BasicView.class,
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class})
    private Long id;
    
    @JsonView({RequestViews.BasicView.class,
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class})
    private String name;
    
    @ManyToOne(targetEntity = Expert.class)
    @JsonView({
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class
    })
    private Expert expert;
    
    @ManyToMany(targetEntity = Apprentice.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonView({
        DiagnosisGroupViews.CreateUpdateView.class, DiagnosisGroupViews.BasicView.class
    })
    private List<Apprentice> apprentices;
    
    
}
