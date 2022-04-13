package com.martin.enjoypadelapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @NotBlank
    private String name;
    @Column
    @NotNull
    private String longitude;
    @Column
    @NotNull
    private String latitude;


    @JsonBackReference
    @OneToMany(mappedBy = "center")
    private List<Match> matches;
}
