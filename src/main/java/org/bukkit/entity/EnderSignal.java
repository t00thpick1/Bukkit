package org.bukkit.entity;

import org.bukkit.Location;

/**
 * Represents an Ender Signal, which is often created upon throwing an ender
 * eye
 */
public interface EnderSignal extends Projectile {

    /**
     * Sets the location to which this EnderSignal will point.
     * <p>
     * If the horizontal distance from this entity to the location is greater
     * than 12 the distance will be truncated
     * 
     * @param location the location to point towards
     */
    public void setSignalDestination(Location location);

    /**
     * Gets the location to which this signal will attempt to reach.
     * <p>
     * This will not always be equal to the location set in
     * {@link #setSignalDestination(Location)}
     * 
     * @return the location the signal is headed to
     */
    public Location getSignalDestination();
}
