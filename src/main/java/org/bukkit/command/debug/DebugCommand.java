package org.bukkit.command.debug;

import java.io.IOException;
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
import org.json.simple.parser.ParseException;

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
            generateDebugReport(Bukkit.getServer(), sender);
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
                generateDebugReport(exactPlugin, sender);
                return true;
            }

            boolean found = false;
            pluginName = pluginName.toLowerCase();
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if (plugin.getName().toLowerCase().contains(pluginName)) {
                    generateDebugReport(plugin, sender);
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

    static void generateDebugReport(final Debuggable debuggable, final CommandSender sender) {
        final String fileName = debuggable.getName() + ".dbg";

        JSONObject file = new JSONObject();
        file.put("content", debuggable.debug());

        JSONObject files = new JSONObject();
        files.put(fileName, file);

        final JSONObject json = new JSONObject();
        json.put("files", files);
        json.put("description", "Debug Report for: " + debuggable.getName());

        sender.sendMessage(ChatColor.GRAY + "Generating debug report.");

        new Thread(new Runnable() {
            public void run() {
                try {
                    JSONObject response = post(json);

                    String url = ((JSONObject) ((JSONObject) response.get("files")).get(fileName)).get("raw_url").toString();

                    sender.sendMessage(ChatColor.GRAY + url);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                sender.sendMessage(ChatColor.GOLD + "A problem has occurred while generating debug report");
            }
        }).start();
    }

    static JSONObject post(JSONObject post) throws IOException, ParseException {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL("https://api.github.com/gists");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.getOutputStream().write(post.toJSONString().getBytes());
            connection.getOutputStream().close();

            JSONObject response = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
            connection.getInputStream().close();

            return (JSONObject) response;
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
