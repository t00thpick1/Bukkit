package org.bukkit.entity;

/**
 * Represents a Creature. Creatures are non-intelligent monsters or animals which
 * have very simple abilities.
 */
public interface Creature extends LivingEntity {

    /**
     * Instructs this Creature to set the specified LivingEntity as its target.
     * Hostile creatures may attack their target, and friendly creatures may
     * follow their target.
     *
     * @param target New LivingEntity to target, or null to clear the target
     */
    public void setTarget(LivingEntity target);

    /**
     * Gets the current target of this Creature
     *
     * @return Current target of this creature, or null if none exists
     */
    public LivingEntity getTarget();

    /**
     * Returns whether the creature is currently leashed
     *
     * @return whether the creature is leashed
     */
    public boolean isLeashed();

    /**
     * Gets the entity that is currently leading this entity
     *
     * @return the entity holding the leash, or null if not leashed
     */
    public Entity getLeashHolder();

    /**
     * Unleashes this entity
     *
     */
    public void unleash();

    /**
     * Leashes this entity
     *
     * @param holder the entity to leash this entity to, must be a Player or Leash
     */
    public void leash(LivingEntity holder);
}
