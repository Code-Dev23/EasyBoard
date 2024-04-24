package it.scopped.easyscoreboard.scoreboard;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Scoreboard {
    private String title;
    private List<String> lines;
    private int interval;
}