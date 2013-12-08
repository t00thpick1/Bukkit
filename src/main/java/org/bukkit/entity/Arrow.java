package org.bukkit.entity;

/**
 * Represents an arrow.
 */
public interface Arrow extends Projectile {

    /**
     * Get the knockbackStrength for an arrow. Which is the {@link org.bukkit.enchantments.Enchantment#KNOCKBACK KnockBack} level of
     * the bow that shot it.
     * 
     * @return the knockbackStrength value.
     */
    public int getKnockbackStrength();

    /**
     * Set the knockbackStrength for an arrow.
     * 
     * @param knockbackStrength the knockbackStrength value.
     */
    public void setKnockbackStrength(int knockbackStrength);
}
