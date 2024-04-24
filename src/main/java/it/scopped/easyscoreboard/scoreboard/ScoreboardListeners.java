package it.scopped.easyscoreboard.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import it.scopped.easyscoreboard.EasyScoreboard;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class ScoreboardListeners implements Listener {
    private final EasyScoreboard main;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FastBoard board = main.getScoreboardManager().createBoard(player);

        main.getScoreboardManager().getBoards().put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        FastBoard board = main.getScoreboardManager().getBoards().get(player.getUniqueId());
        main.getScoreboardManager().getBoards().remove(player.getUniqueId());
        if (board != null)
            board.delete();
    }
}
