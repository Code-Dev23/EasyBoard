package it.scopped.easyscoreboard.commands;

import it.scopped.easyscoreboard.EasyScoreboard;
import it.scopped.easyscoreboard.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScoreboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        EasyScoreboard.get().getScoreboardManager().reloadBoard();
        sender.sendMessage(Messages.color("&aScoreboard reloaded with successfully!"));
        return true;
    }
}
