package me.epicgodmc.voteparty;

import org.bukkit.configuration.file.FileConfiguration;

public class VPmanager {


    private Main plugin = Main.getPlugin(Main.class);
    private FileConfiguration config = plugin.file.getDataFile();

    private static int VOTESNEEDED = 100;
    private static int CurrentVotes = 0;


    public VPmanager (int votesNeeded)
    {
        VOTESNEEDED = votesNeeded;

    }

    public void Save(){
        config.set("Data.CurrentVotes", CurrentVotes);
        plugin.file.saveDataFile();
        System.out.print("Saved VoteParty");

    }
    public void Load()
    {
        if (!config.isSet("Data.CurrentVotes")) return;
        CurrentVotes = config.getInt("Data.CurrentVotes");
        System.out.print("Loaded VoteParty");
    }


    public int getCurrentVotes() {
        return CurrentVotes;
    }

    public void setCurrentVotes(int value) {
        CurrentVotes = value;
    }
    public int getVotesNeeded()
    {
        return VOTESNEEDED;
    }

}
