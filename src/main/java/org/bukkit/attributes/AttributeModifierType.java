package org.bukkit.attributes;

/**
 * An enumeration representation of the modification type for an attribute
 * modifier
 */
public enum AttributeModifierType {
    /**
     * First stage of modification, adds to base value
     * 
     * Follows mathematical equation  Y = X + modifier
     */
    ADDITIVE,
    /**
     * Second stage of modification, adds to the calculated value a
     * percentage of the value
     * 
     * Follows mathematical equation  Y = Y + (Y * modifier)
     */
    ADD_PERCENTAGE,
    /**
     * Third stage of modification, multiplies the calculated value by a
     * percentage of the value
     * 
     * Follows mathematical equation  Y = Y * (1 + modifier)
     * Mathematically equivalent to the second stage, but sequentially
     * after
     */
    MULTIPLY_PERCENTAGE;
}
