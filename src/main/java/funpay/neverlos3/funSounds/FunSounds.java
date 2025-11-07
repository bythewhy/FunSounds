package funpay.neverlos3.funSounds;

import funpay.neverlos3.funSounds.command.MainCmd;
import funpay.neverlos3.funSounds.listener.FunpayNeverlos3;
import funpay.neverlos3.funSounds.util.Color;
import funpay.neverlos3.funSounds.util.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class FunSounds extends JavaPlugin {
    @Override
    public void onEnable() {
        Config config = new Config(this);
        getCommand("funsounds").setExecutor(new MainCmd(config));
        getCommand("funsounds").setTabCompleter(new MainCmd(config));
        new FunpayNeverlos3(this, config);
        getLogger().info(Color.pars("§x§0§0§F§F§0§0| "));
        getLogger().info(Color.pars("§x§0§0§F§F§0§0| FunSounds - Звуки при критах, попаданий с луков с проекта FunTime"));
        getLogger().info(Color.pars("§x§0§0§F§F§0§0| Успешное включение плагина"));
        getLogger().info(Color.pars("§x§0§0§F§F§0§0| Сделано by neverlos3"));
        getLogger().info(Color.pars("§x§0§0§F§F§0§0| §6Покупка других плагинов/услуг - https://funpay.com/users/8628865/"));
        getLogger().info(Color.pars("§x§0§0§F§F§0§0| §6Вступай в телеграмм канал разработчика - &6t.me/warenetwork"));
        getLogger().info(Color.pars("§x§0§0§F§F§0§0| "));
    }

    @Override
    public void onDisable() {
        getLogger().info(Color.pars("§x§f§f§0§0§0§0| "));
        getLogger().info(Color.pars("§x§f§f§0§0§0§0| FunSounds - Звуки при критах, попаданий с луков с проекта FunTime"));
        getLogger().info(Color.pars("§x§f§f§0§0§0§0| Успешное выключение плагина"));
        getLogger().info(Color.pars("§x§f§f§0§0§0§0| Сделано by neverlos3"));
        getLogger().info(Color.pars("§x§f§f§0§0§0§0| §6Покупка других плагинов/услуг - https://funpay.com/users/8628865/"));
        getLogger().info(Color.pars("§x§f§f§0§0§0§0| §6ступай в телеграмм канал разработчика - &6t.me/warenetwork"));
        getLogger().info(Color.pars("§x§f§f§0§0§0§0| "));
    }
}