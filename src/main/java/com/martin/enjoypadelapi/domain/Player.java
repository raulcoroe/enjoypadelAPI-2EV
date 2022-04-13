package com.martin.enjoypadelapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Import;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull
    @NotBlank
    private String name;
    @Column
    private String surname;
    @Column
    private String level;
    @Column
    private boolean availability;
    @Column
    @Lob
    private byte[] image;

    @JoinTable(
            name = "rel_players_matches",
            joinColumns = @JoinColumn(name = "FK_PLAYER", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_MATCH", nullable = false)
    )
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Match> matches;
}
