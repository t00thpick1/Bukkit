package org.bukkit.attributes;

/**
 * Represents an attribute modifier.
 */
public interface AttributeModifier {
    /**
     * Gets the attribute identifier used to determine which attribute to modify
     * 
     * @return an {@link AttributeIdentifier}
     */
    public AttributeIdentifier getIdentifier();

    public String getName();

    /**
     * Gets the value the attribute is modified with
     * 
     * @return the modifier value
     */
    public double getValue();

    /**
     * Gets the type of modification made to the attribute
     * 
     * @return an {@link AttributeModifierType}
     */
    public AttributeModifierType getType();
}
