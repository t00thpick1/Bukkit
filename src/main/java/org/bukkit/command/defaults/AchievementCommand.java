package org.bukkit.command.defaults;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableList;

public class AchievementCommand extends VanillaCommand {
    public AchievementCommand() {
        super("achievement");
        this.description = "Gives the specified player an achievement. Use '*' to give all achievements.";
        this.usageMessage = "/achievement give <stat_name> [player]";
        this.setPermission("bukkit.command.achievement");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        if (args.length >= 2 && args[0].equalsIgnoreCase("give")) {
            String statisticString = args[1];
            Player player = null;

            if (args.length > 2) {
                player = Bukkit.getPlayer(args[1]);
            } else if (sender instanceof Player) {
                player = (Player) sender;
            }

            if (player == null) {
                sender.sendMessage("You must specify which player you wish to perform this action on.");
                return true;
            }

            if (statisticString.equals("*")) {
                for (Achievement achievement : Achievement.values()) {
                    player.awardAchievement(achievement);
                }
                Command.broadcastCommandMessage(sender, String.format("Successfully given all achievements to %s", player.getName()));
                return true;
            }

            Achievement achievement = null;
            Statistic statistic = null;

            if (achievement != null) {
                while (!player.hasAchievement(achievement)) {
                    Achievement achieve = achievement;
                    while (achieve.hasParent() && !player.hasAchievement(achieve.getParent())) {
                        achieve = achieve.getParent();
                    }
                    player.awardAchievement(achieve);
                }
                Command.broadcastCommandMessage(sender, String.format("Successfully given %s the stat %s", player.getName(), statisticString));
                return true;
            } else if (statistic != null) {
                if (statistic.isSubstatistic()) {
                    if (statistic.isEntity()) {
                        EntityType entityType = EntityType.fromName(statisticString.substring(statisticString.lastIndexOf(".") + 1));
                        if (entityType == null) {
                            sender.sendMessage(String.format("Unknown achievement or statistic '%s'", statisticString));
                            return true;
                        }
                        try {
                            player.incrementStatistic(statistic, entityType);
                        } catch (IllegalArgumentException e) {
                            sender.sendMessage(String.format("Unknown achievement or statistic '%s'", statisticString));
                            return true;
                        }
                    } else {
                        int id;
                        try {
                            id = getInteger(sender, statisticString.substring(statisticString.lastIndexOf(".") + 1), 0 , Integer.MAX_VALUE, true);
                        } catch (NumberFormatException e) {
                            sender.sendMessage(e.getMessage());
                            return true;
                        }
                        Material material = Material.getMaterial(id);
                        if (material == null) {
                            sender.sendMessage(String.format("Unknown achievement or statistic '%s'", statisticString));
                            return true;
                        }
                        try {
                            player.incrementStatistic(statistic, material);
                        } catch (IllegalArgumentException e) {
                            sender.sendMessage(String.format("Unknown achievement or statistic '%s'", statisticString));
                            return true;
                        }
                    }
                } else {
                    player.incrementStatistic(statistic);
                }
                Command.broadcastCommandMessage(sender, String.format("Successfully given %s the stat %s", player.getName(), statisticString));
                return true;
            }
            sender.sendMessage(String.format("Unknown achievement or statistic '%s'", statisticString));
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (args.length == 3) {
            return super.tabComplete(sender, alias, args);
        }
        return ImmutableList.of();
    }
}
