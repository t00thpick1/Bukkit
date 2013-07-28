package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class EntityLeashedEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Entity leashHolder;
    private HumanEntity cause;
    private boolean cancelled = false;

    public EntityLeashedEvent(Entity what, Entity leashHolder, HumanEntity cause) {
        super(what);
        this.leashHolder = leashHolder;
        this.cause = cause;
    }

    public Entity getLeashHolder() {
        return leashHolder;
    }

    public HumanEntity getCause() {
        return cause;
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
