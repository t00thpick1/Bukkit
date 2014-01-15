package org.bukkit;

/**
 * Represents a countable statistic, which is tracked about the player
 */
public enum Statistic {
    DAMAGE_DEALT,
    DAMAGE_TAKEN,
    DEATHS,
    MOB_KILLS,
    PLAYER_KILLS,
    FISH_CAUGHT,
    ANIMALS_BRED,
    TREASURE_FISHED,
    JUNK_FISHED,
    LEAVE_GAME,
    JUMP,
    DROP,
    PLAY_ONE_TICK,
    WALK_ONE_CM,
    SWIM_ONE_CM,
    FALL_ONE_CM,
    CLIMB_ONE_CM,
    FLY_ONE_CM,
    DIVE_ONE_CM,
    MINECART_ONE_CM,
    BOAT_ONE_CM,
    PIG_ONE_CM,
    HORSE_ONE_CM,
    MINE_BLOCK(true, false, false),
    USE_ITEM(false, true, false),
    BREAK_ITEM(false, true, false),
    CRAFT_ITEM(false, true, false),
    KILL_ENTITY(false, false, true),
    ENTITY_KILLED_BY(false, false, true);

    private final boolean isSubstat;
    private final boolean isBlock;
    private final boolean isItem;
    private final boolean isEntity;

    private Statistic() {
        this(false, false, false, false);
    }

    private Statistic(boolean isBlock, boolean isItem, boolean isEntity) {
        this(true, isBlock, isItem, isEntity);
    }

    private Statistic(boolean isSubstat, boolean isBlock, boolean isItem, boolean isEntity) {
        this.isSubstat = isSubstat;
        this.isBlock = isBlock;
        this.isItem = isItem;
        this.isEntity = isEntity;
    }

    /**
     * Checks if this is a substatistic.
     * <p>
     * A substatistic exists in mass for each block, item, or entitytype, depending on
     * {@link #isBlock()}, {@link #isItem()}, and {@link #isEntity()} respectively
     *
     * @return true if this is a substatistic
     */
    public boolean isSubstatistic() {
        return isSubstat;
    }

    /**
     * Checks if this is a substatistic dealing with blocks
     *
     * @return true if this deals with blocks
     */
    public boolean isBlock() {
        return isSubstat && isBlock;
    }

    /**
     * Checks if this is a substatistic dealing with items
     *
     * @return true if this deals with items
     */
    public boolean isItem() {
        return isSubstat && isItem;
    }

    /**
     * Checks if this is a substatistic dealing with entities
     *
     * @return true if this deals with entities
     */
    public boolean isEntity() {
        return isSubstat && isEntity;
    }
}
