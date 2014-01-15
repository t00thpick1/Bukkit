package org.bukkit.event.player;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a player statistic is incremented.
 * <p>
 * This event is not called for {@link org.bukkit.Statistic#PLAY_ONE_MINUTE}
 *
 */
public class PlayerStatisticIncrementEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    protected final Statistic statistic;
    private int initialValue;
    private int newValue;
    private boolean isCancelled = false;

    public PlayerStatisticIncrementEvent(Player player, Statistic statistic, int initialValue, int newValue) {
        super (player);
        this.statistic = statistic;
        this.initialValue = initialValue;
        this.newValue = newValue;
    }

    /**
     * Gets the statistic that is being incremented
     * @return the incremented statistic
     */
    public Statistic getStatistic() {
        return statistic;
    }

    /**
     * Gets the previous value of the statistic
     * @return the previous value of the statistic
     */
    public int getPreviousValue() {
        return initialValue;
    }

    /**
     * Gets the new value of the statistic
     * @return the new value of the statistic
     */
    public int getNewValue() {
        return newValue;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
