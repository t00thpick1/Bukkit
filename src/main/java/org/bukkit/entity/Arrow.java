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

    /**
     * Determines if this projectile is critical.
     *
     * @return true if it is critical.
     */
    public boolean isCritical();

    /**
     * Set whether or not this arrow should be critical.
     *
     * @param critical whether or not it should be critical.
     */
    public void setCritical(boolean critical);
}
