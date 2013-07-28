package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class EntityUnleashedEvent extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private UnleashReason reason;

    public EntityUnleashedEvent(Entity entity, UnleashReason reason) {
        super(entity);
        this.reason = reason;
    }

    public UnleashReason getUnleashReason() {
        return reason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public enum UnleashReason {
        HOLDER_DIED,
        HOLDER_NOT_FOUND,
        PLAYER_UNLEASH,
        DISTANCE,
        PLUGIN;
    }
}
