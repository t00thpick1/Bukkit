package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called when a player spawns into the world.
 */
public class PlayerSpawnEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    public PlayerSpawnEvent(final Player spawnPlayer) {
        super(spawnPlayer);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
