package org.bukkit.command.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public class SummonCommand extends VanillaCommand {

    public SummonCommand() {
        super("summon");
        this.description = "Summons an entity with the specified data.";
        this.usageMessage = "/summon <EntityName> [x] [y] [z] [dataTag]";
        this.setPermission("bukkit.command.summon");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;
        if ((args.length == 0)) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }

        double x;
        double y;
        double z;

        Location loc;
        if (sender instanceof BlockCommandSender) {
            loc = ((BlockCommandSender) sender).getBlock().getLocation();
        } else if (sender instanceof Player) {
            loc = ((Player) sender).getLocation();
        } else {
            sender.sendMessage("Unable to summon object");
            return true;
        }

        World world = loc.getWorld();

        if (args.length >= 4) {
            try {
                String xArg = args[1];
                if (xArg.startsWith("~")) {
                    x = loc.getX();
                    if (xArg.length() != 1) {
                        x += getDouble(xArg.replaceAll("~", ""));
                    }
                } else {
                    x = getDouble(xArg);
                }
                String yArg = args[2];
                if (yArg.startsWith("~")) {
                    y = loc.getY();
                    if (yArg.length() != 1) {
                        y += getDouble(yArg.replaceAll("~", ""));
                    }
                } else {
                    y = getDouble(yArg);
                }
                String zArg = args[3];
                if (zArg.startsWith("~")) {
                    z = loc.getZ();
                    if (zArg.length() != 1) {
                        z += getDouble(zArg.replaceAll("~", ""));
                    }
                } else {
                    z = getDouble(zArg);
                }
            } catch (NumberFormatException e) {
                sender.sendMessage(e.getMessage());
                return true;
            }
        } else {
            x = loc.getX();
            y = loc.getY();
            z = loc.getZ();
        }

        if (!world.isChunkLoaded(((int) x) >> 4, ((int) z) >> 4)) {
            sender.sendMessage("Cannot summon the object out of the world");
            return true;
        }

        String data = null;
        
        if (args.length >= 5) {
            data = Joiner.on(' ').join(Arrays.asList(args).subList(4, args.length));
        }

        Entity entity = null;

        try {
            entity = Bukkit.getUnsafe().createEntity(args[0], world, x, y, z, data);
        } catch (Exception e) {
            sender.sendMessage(String.format("Data tag parsing failed: %s", e.getMessage()));
        }

        if (entity == null) {
            sender.sendMessage("Unable to summon object");
            return false;
        }

        Command.broadcastCommandMessage(sender, "Object successfully summoned");
        return true;
    }

    public double getDouble(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(String.format("%s is not a valid number", value));
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (args.length == 1) {
            return Bukkit.getUnsafe().tabCompleteEntityType(args[0], new ArrayList<String>());
        }
        return ImmutableList.of();
    }
}
