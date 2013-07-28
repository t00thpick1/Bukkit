package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class EntityLeashedEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Entity leashHolder;
    private Player leasher;
    private boolean cancelled = false;

    public EntityLeashedEvent(Entity what, Entity leashHolder, Player leasher) {
        super(what);
        this.leashHolder = leashHolder;
        this.leasher = leasher;
    }

    /**
     * Returns the entity that is holding the leash
     *
     * @return The leash holder
     */
    public Entity getLeashHolder() {
        return leashHolder;
    }

    /**
     * Returns the player who is leashing the entity, or null if done by a plugin
     *
     * @return The player
     */
    public Player getLeasher() {
        return leasher;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled  = cancel;
    }
}
