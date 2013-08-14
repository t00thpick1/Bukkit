package org.bukkit.entity;

/**
 * Represents a Zombie.
 */
public interface Zombie extends Monster {
    /**
     * Gets whether the zombie is a baby
     *
     * @return Whether the zombie is a baby
     */
    public boolean isBaby();

    /**
     * Sets whether the zombie is a baby
     *
     * @param flag Whether the zombie is a baby
     */
    public void setBaby(boolean flag);

    /**
     * Gets whether the zombie is a villager
     *
     * @return Whether the zombie is a villager
     */
    public boolean isVillager();

    /**
     * Sets whether the zombie is a villager
     *
     * @param flag Whether the zombie is a villager
     */
    public void setVillager(boolean flag);

    /**
     * Gets the Spawn Reinforcement Chance of this zombie after Item
     * Attributes and other effects are applied.
     * <p>
     * Note: Only applicable when world difficulty and spawn conditions allow
     * for it
     *
     * @return the spawn reinforcement chance
     */
    public double getSpawnReinforcementsChance();

    /**
     * Gets the base Spawn Reinforcement Chance of this zombie before Item
     * Attributes and other effects are applied.
     * <p>
     * Note: Only applicable when world difficulty and spawn conditions allow
     * for it
     *
     * @return the base spawn reinforcement chance
     */
    public double getBaseSpawnReinforcementsChance();

    /**
     * Sets the base Spawn Reinforcement Chance of this zombie before Item
     * Attributes and other effects are applied.
     * <P>
     * Range between 0 and 1.
     * <p>
     * Note: Only applicable when world difficulty and spawn conditions allow
     * for it
     *
     * @param chance the base spawn reinforcement chance
     */
    public void setBaseSpawnReinforcementsChance(double chance);
}
