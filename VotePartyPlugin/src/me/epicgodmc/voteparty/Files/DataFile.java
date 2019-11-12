package me.epicgodmc.voteparty.Files;

import me.epicgodmc.voteparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFile
{

    private Main plugin = Main.getPlugin(Main.class);

    //Files & FileConfigs

    public FileConfiguration data;
    public File datafile;
    //----------------------

    public void setup(){
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }



        datafile = new File(plugin.getDataFolder(),"Data.yml");
        if (!datafile.exists()){
            try {
                datafile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"FILE: Data.yml has been created correctly");

            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"Failed to create Data.yml FILE");
            }
        }

        data = YamlConfiguration.loadConfiguration(datafile);
    }
    public FileConfiguration getDataFile(){
        return data;
    }
    public void saveDataFile(){
        try {
            data.save(datafile);

        }catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"Failed to save Data.yml FILE");
        }
    }
    public void reloadDataFile(){
        data = YamlConfiguration.loadConfiguration(datafile);

    }
}
