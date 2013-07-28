package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class EntityUnleashedByPlayerEvent extends EntityUnleashedEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private boolean cancelled = false;

    public EntityUnleashedByPlayerEvent(Entity entity, Player player) {
        super(entity, UnleashReason.PLAYER_UNLEASH);
        this.player = player;
    }

    /**
     * Returns the player who is unleashing the entity
     *
     * @return The player
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
