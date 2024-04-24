package it.scopped.easyscoreboard.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import it.scopped.easyscoreboard.EasyScoreboard;
import it.scopped.easyscoreboard.utils.Messages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class ScoreboardManager {
    private final EasyScoreboard main;
    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private Scoreboard scoreboard;

    public void loadBoard() {
        String title = main.getConfig().getString("scoreboard.title");
        List<String> lines = main.getConfig().getStringList("scoreboard.lines");
        int interval = main.getConfig().getInt("scoreboard.interval");
        scoreboard = new Scoreboard(title, lines, interval);
    }

    public FastBoard createBoard(Player player) {
        FastBoard board = new FastBoard(player);
        board.updateTitle(Messages.color(scoreboard.getTitle()));
        updateBoard(board);
        return board;
    }

    public void startUpdateBoardsTask() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(main, () -> boards.values().forEach(this::updateBoard), scoreboard.getInterval() * 20L, 20);
    }

    public void reloadBoard() {
        main.reloadConfig();
        Bukkit.getScheduler().cancelTasks(main);
        boards.clear();
        Bukkit.getOnlinePlayers().forEach(player -> {
            FastBoard board = createBoard(player);
            boards.put(player.getUniqueId(), board);
        });
        startUpdateBoardsTask();
    }

    public void updateBoard(FastBoard board) {
        board.updateLines(main.getConfig().getStringList("scoreboard.lines").stream().map(line -> Messages.color(board.getPlayer(), line)).collect(Collectors.toList()));
    }
}
