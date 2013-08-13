package org.bukkit.attributes;

/**
 * Represents an attribute on an entity
 */
public interface Attribute {

    /**
     * Gets the identifier of the attribute type
     * @return the attribute identifier
     */
    public AttributeIdentifier getIdentifier();

    /**
     * Gets the base value of the attribute before modifiers are applied
     * 
     * @return the base value of the attribute
     */
    public double getBaseValue();

    /**
     * Sets the base value of an Attribute
     * 
     * @param value the value to set the base to
     */
    public void setBaseValue(double value);

    /**
     * Gets the value of the attribute after modifiers are applied
     * 
     * @return the value
     */
    public double getValue();
}