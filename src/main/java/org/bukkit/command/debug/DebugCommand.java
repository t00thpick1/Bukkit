package org.bukkit.command.debug;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.common.collect.ImmutableList;

public class DebugCommand extends BukkitCommand {
    public DebugCommand(String name) {
        super(name);

        this.description = "Prints debug information for the purpose of bug reporting";
        this.usageMessage = "/debug";
        this.setPermission("bukkit.command.debug");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        if (args.length == 0) {
            debug(Bukkit.getServer(), sender);
        } else {
            StringBuilder name = new StringBuilder();

            for (String arg : args) {
                if (name.length() > 0) {
                    name.append(' ');
                }

                name.append(arg);
            }

            String pluginName = name.toString();
            Plugin exactPlugin = Bukkit.getPluginManager().getPlugin(pluginName);
            if (exactPlugin != null) {
                debug(exactPlugin, sender);
                return true;
            }

            boolean found = false;
            pluginName = pluginName.toLowerCase();
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if (plugin.getName().toLowerCase().contains(pluginName)) {
                    debug(plugin, sender);
                    found = true;
                }
            }

            if (!found) {
                sender.sendMessage("This server is not running any plugin by that name.");
                sender.sendMessage("Use /plugins to get a list of plugins.");
            }
        }
        return true;
    }

    private void debug(Debugable debugable, CommandSender sender) {
        String url = createGist(debugable);
        sender.sendMessage(ChatColor.GRAY + url);
    }

    private String createGist(Debugable debugable) {
        JSONObject json = new JSONObject();
        json.put("description", "Debug Information for: " + debugable.getName());
        json.put("public", true);
        JSONObject files = new JSONObject();
        JSONObject file = new JSONObject();
        file.put("content", debugable.debug());
        files.put(debugable.getName() + ".debug", file);
        json.put("files", files);
        return ((JSONObject) ((JSONObject) post(json).get("files")).get(debugable.getName() + ".debug")).get("raw_url").toString();
    }

    private JSONObject post(JSONObject post) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL("https://api.github.com/gists");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.getOutputStream().write(post.toJSONString().getBytes());
            connection.getOutputStream().flush();
            connection.getOutputStream().close();

            JSONObject response = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
            connection.getInputStream().close();

            return (JSONObject) response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (args.length == 1) {
            List<String> completions = new ArrayList<String>();
            String toComplete = args[0].toLowerCase();
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if (StringUtil.startsWithIgnoreCase(plugin.getName(), toComplete)) {
                    completions.add(plugin.getName());
                }
            }
            return completions;
        }
        return ImmutableList.of();
    }
}
