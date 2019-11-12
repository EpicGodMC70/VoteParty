package me.epicgodmc.voteparty.Commands;

import me.epicgodmc.voteparty.Main;
import me.epicgodmc.voteparty.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCMD implements CommandExecutor {

    private Main plugin = Main.getPlugin(Main.class);
    private Util util = plugin.util;


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (args.length == 0)
            {
                int votesLeft = plugin.vPmanager.getVotesNeeded()-plugin.vPmanager.getCurrentVotes();
                player.sendMessage(util.applyCC(util.prefix+"Votes Left For VoteParty: &f&l"+votesLeft));
                return true;
            }
            if (args.length == 1)
            {
                if (args[0].equalsIgnoreCase("addvote"))
                {
                    plugin.vPmanager.setCurrentVotes(plugin.vPmanager.getCurrentVotes()+1);
                    System.out.print("added 1 vote");
                    if (plugin.vPmanager.getCurrentVotes() >= plugin.vPmanager.getVotesNeeded())
                    {
                        plugin.vPmanager.setCurrentVotes(0);
                        util.sendRewards();
                    }
                }
            }
            if (args.length == 2)
            {
                if (args[0].equalsIgnoreCase("setvotes"))
                {
                    int value;
                    try{

                        value = Integer.parseInt(args[1]);

                    }catch (NumberFormatException e)
                    {
                        e.printStackTrace();
                        return true;
                    }
                    System.out.print(value);
                    plugin.vPmanager.setCurrentVotes(value);
                    player.sendMessage(util.applyCC(util.prefix+"You have set the current votes to &f&l"+value));
                    return true;
                }
            }

        }else{
            if (args.length == 1)
            {
                if (args[0].equalsIgnoreCase("addvote"))
                {
                    plugin.vPmanager.setCurrentVotes(plugin.vPmanager.getCurrentVotes()+1);
                    System.out.print("added 1 vote");
                    if (plugin.vPmanager.getCurrentVotes() >= plugin.vPmanager.getVotesNeeded())
                    {
                        plugin.vPmanager.setCurrentVotes(0);
                        util.sendRewards();
                    }
                }
            }
        }


        return true;
    }
}
