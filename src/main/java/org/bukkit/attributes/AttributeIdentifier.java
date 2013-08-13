package org.bukkit.attributes;

public enum AttributeIdentifier {

    /**
     * The maximum health of this mob; determines the highest Health they may
     * be healed to.
     */
    MAX_HEALTH,
    /**
     * The range in blocks within which a mob will
     * target an entity to track. 
     * Exiting this range will cause the mob to stop following the
     * entity.
     * Max value of 2048.
     */
    FOLLOW_RANGE,
    /**
     * Resistance to knockback from attacks, explosions, and projectiles.
     * Max value of 1.0.
     */
    KNOCKBACK_RESISTANCE,
    /**
     * The movement speed of the mob
     */
    MOVEMENT_SPEED,
    /**
     * Damage dealt by attacks, in half-hearts.
     */
    ATTACK_DAMAGE,
    /**
     * Horse jump strength 
     * Max value of 2.0.
     */
    JUMP_STRENGTH,
    /**
     * Chance that a zombie will spawn another zombie when attacked.
     * Max value of 1.0
     */
    SPAWN_REINFORCEMENTS;
}
