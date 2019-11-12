package me.epicgodmc.voteparty;

import me.epicgodmc.voteparty.Commands.AdminCMD;
import me.epicgodmc.voteparty.Files.DataFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public int votesNeeded = getConfig().getInt("VotesNeeded");

    public Util util;
    public VPmanager vPmanager;
    public DataFile file;
    @Override
    public void onEnable() {
        registerInstances();
        saveDefaultConfig();
        file.setup();
        vPmanager = new VPmanager(votesNeeded);


        getCommand("voteparty").setExecutor(new AdminCMD());

        vPmanager.Load();
    }

    @Override
    public void onDisable() {
        vPmanager.Save();
    }

    private void registerInstances()
    {
        util = new Util();
        file = new DataFile();
    }

}
