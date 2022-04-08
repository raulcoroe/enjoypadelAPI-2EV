package com.martin.enjoypadelapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "centers")
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String longitude;
    @Column
    private String latitude;


    @OneToMany(mappedBy = "center")
    private List<Match> matches;
}