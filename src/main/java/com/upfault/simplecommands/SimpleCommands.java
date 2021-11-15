package com.upfault.simplecommands;

import com.upfault.simplecommands.commands.*;
import com.upfault.simplecommands.events.JoinEvent;
import com.upfault.simplecommands.updatechecker.UpdateChecker;
import com.upfault.simplecommands.updatechecker.UserAgentBuilder;
import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class SimpleCommands extends JavaPlugin {

    private static SimpleCommands instance;
    private static final Map<String, String> phrases = new HashMap<>();
    public static String prefix = "&7[&bSCMD&7] &7";
    public static String console_prefix = "[SCMD]";
    private static final int SPIGOT_RESOURCE_ID = 12345;
    public HashMap<UUID, Boolean> vanish_list = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        loadConfiguration();
        loadLangFile();
        registerCommands();
        registerEvents();
        checkForUpdates();
    }

    private void checkForUpdates() {
        UpdateChecker.init(this, "https://raw.githubusercontent.com/UpFault/SimpleCommands/master/latest-version.txt")
                .setDownloadLink(SPIGOT_RESOURCE_ID)
                .setDonationLink("https://paypal.me/tvred")
                .setChangelogLink(SPIGOT_RESOURCE_ID)
                .setNotifyOpsOnJoin(true)
                .setNotifyByPermissionOnJoin("simplecommands.op")
                .setUserAgent(new UserAgentBuilder().addPluginNameAndVersion())
                .checkEveryXHours(1)
                .checkNow();
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("fly")).setExecutor(new fly());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new feed());
        Objects.requireNonNull(getCommand("heal")).setExecutor(new heal());
        Objects.requireNonNull(getCommand("scmd")).setExecutor(new reload());
        Objects.requireNonNull(getCommand("gms")).setExecutor(new gamemodeSwitcher());
        Objects.requireNonNull(getCommand("gmc")).setExecutor(new gamemodeSwitcher());
        Objects.requireNonNull(getCommand("gmsp")).setExecutor(new gamemodeSwitcher());
        Objects.requireNonNull(getCommand("gma")).setExecutor(new gamemodeSwitcher());
        Objects.requireNonNull(getCommand("gmr")).setExecutor(new gamemodeSwitcher());
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new vanish());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    }

    public void loadConfiguration() {

        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()){
            Utilities.loadResource(this, "config.yml");
        }
        FileConfiguration config = this.getConfig();


        prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("plugin-prefix")));

    //    customSetting = config.getBoolean("custom-setting");

        Bukkit.getLogger().info(console_prefix + " Settings reloaded from config");
    }

    public void loadLangFile() {

        File langFile = new File(getDataFolder(), "language.yml");
        FileConfiguration langFileConfig = new YamlConfiguration();
        if (!langFile.exists()){ Utilities.loadResource(this, "language.yml"); }

        try { langFileConfig.load(langFile); }
        catch (Exception e3) { e3.printStackTrace(); }

        for(String priceString : langFileConfig.getKeys(false)) {
            phrases.put(priceString, langFileConfig.getString(priceString));
        }
    }

    public static void reload(Player player) {
        getInstance().reloadConfig();
        getInstance().loadConfiguration();
        getInstance().loadLangFile();
        Utilities.informPlayer(player, "configuration, values, and language settings reloaded");
        Bukkit.getLogger().info("configuration, values, and language settings reloaded");
    }

    public static String getPhrase(String key) {
        return phrases.get(key);
    }

    public static SimpleCommands getInstance() { return instance; }

    @Override
    public void onDisable() {
    }

}
