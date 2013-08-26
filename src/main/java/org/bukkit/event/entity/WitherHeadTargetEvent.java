package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither.WitherHead;
import org.bukkit.event.HandlerList;

/**
 * Called when a wither head targets or untargets an entity for its wither skull attack
 */
public class WitherHeadTargetEvent extends EntityTargetLivingEntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private WitherHead head;

    public WitherHeadTargetEvent(Entity entity, LivingEntity target, TargetReason reason, WitherHead head) {
        super(entity, target, reason);
        this.head = head;
    }

    /**
     * Gets which head of the wither is targeting an entity
     *
     * @return Which wither head is targeting an entity
     */
    public WitherHead getWitherHead() {
        return head;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
