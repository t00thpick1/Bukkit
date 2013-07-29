package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Called when an entity is no longer leashed due to a player's action
 */
public class EntityUnleashedByPlayerEvent extends EntityUnleashedEvent implements Cancellable {
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

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
