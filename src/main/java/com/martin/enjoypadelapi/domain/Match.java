package com.martin.enjoypadelapi.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull
    @NotBlank
    private String round;
    @Column
    @PositiveOrZero
    private int duration;
    @Column
    private String date;
    @Column
    private String matchScore;

    @ManyToMany(mappedBy = "matches")
    private List<Player> players;


    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;
}

