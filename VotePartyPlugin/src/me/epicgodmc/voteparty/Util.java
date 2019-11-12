package me.epicgodmc.voteparty;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Util
{
    private Main plugin = Main.getPlugin(Main.class);

    public String prefix = plugin.getConfig().getString("PluginPrefix");

    public String applyCC(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text
        );
    }


    public void sendRewards()
    {
        Bukkit.broadcastMessage(applyCC(prefix+"VoteParty has started!"));
        for (Player player : Bukkit.getOnlinePlayers())
        {
            giveItems(player);
            executeCommands(player);
        }
    }


    private void executeCommands(Player player)
    {
        String name = player.getName();

        for (String cmd : plugin.getConfig().getStringList("Rewards.Commands"))
        {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%PLAYER%", name));
        }

    }
    private void giveItems(Player player)
    {
        for (String s : plugin.getConfig().getConfigurationSection("Rewards.Items").getKeys(false))
        {
            String path = "Rewards.Items."+s+".";
            String type = plugin.getConfig().getString(path+"type");

            ItemStack item = new ItemStack(Material.valueOf(type.toUpperCase()));
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(applyCC(plugin.getConfig().getString(path+"name")));

            List<String> lore = new ArrayList<>();
            for (String line : plugin.getConfig().getStringList(path+"lore"))
            {
                lore.add(applyCC(line));
            }
            meta.setLore(lore);

            if (plugin.getConfig().getBoolean(path+"glow"))
            {
                meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            }
            item.setItemMeta(meta);

            player.getInventory().addItem(item);



        }
    }



}
