package com.martin.enjoypadelapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private String round;
    private int duration;
    private String date;
    private String matchScore;
    private long player1;
    private long player2;
    private long player3;
    private long player4;
    private long center;
}
