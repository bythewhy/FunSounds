package funpay.neverlos3.funSounds.command;

import funpay.neverlos3.funSounds.util.Color;
import funpay.neverlos3.funSounds.util.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.List;

public class MainCmd implements CommandExecutor, TabCompleter {
    private final Config config;

    public MainCmd(Config config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] argument) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Color.pars(config.getMessages().getString("messages.onlyplayer", "&cКоманда только для игроков!")));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("funsounds.adm")) {
            player.sendMessage(Color.pars(config.getMessages().getString("messages.onlyplayer", "&cКоманда только для игроков!")));
            return true;
        }

        if(argument.length == 0 || !argument[0].equalsIgnoreCase("toggle")) {
            player.sendMessage(Color.pars(config.getMessages().getString("messages.usg", "&cИспользование: /funsounds toggle")));
            return true;
        }

        boolean soundsEnabled = config.isSoundEnabledForPlayer(player);
        config.setSoundEnabledForPlayer(player, !soundsEnabled);
        String message = soundsEnabled ?
                config.getMessages().getString("messages.disable", "&cЗвуки отключены!") :
                config.getMessages().getString("messages.enable", "&aЗвуки включены!");
        player.sendMessage(Color.pars(message));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] argument) {
        if(argument.length == 1) {
            return Arrays.asList("toggle");
        }
        return null;
    }
}