package it.scopped.easyscoreboard;

import fr.mrmicky.fastboard.FastBoardBase;
import it.scopped.easyscoreboard.commands.ScoreboardCommand;
import it.scopped.easyscoreboard.scoreboard.Scoreboard;
import it.scopped.easyscoreboard.scoreboard.ScoreboardListeners;
import it.scopped.easyscoreboard.scoreboard.ScoreboardManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class EasyScoreboard extends JavaPlugin {

    private static EasyScoreboard instance;
    private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        scoreboardManager = new ScoreboardManager(this);
        scoreboardManager.loadBoard();
        scoreboardManager.startUpdateBoardsTask();

        getServer().getPluginManager().registerEvents(new ScoreboardListeners(this), this);
        getCommand("easyboardreload").setExecutor(new ScoreboardCommand());
    }

    @Override
    public void onDisable() {
        scoreboardManager.getBoards().values().forEach(FastBoardBase::delete);
        scoreboardManager.getBoards().clear();
    }

    public static EasyScoreboard get() {
        return instance;
    }
}
