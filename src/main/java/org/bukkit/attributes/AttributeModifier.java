package org.bukkit.attributes;

/**
 * Represents an attribute modifier.
 */
public class AttributeModifier {

    private final AttributeIdentifier identifier;
    private final String name;
    private final double value;
    private final AttributeModifierType type;

    public AttributeModifier(AttributeIdentifier identifier, String name, double value, AttributeModifierType type) {
        this.identifier = identifier;
        this.name = name;
        this.value = value;
        this.type = type;
    }

    /**
     * Gets the attribute identifier used to determine which attribute to modify
     * 
     * @return an {@link AttributeIdentifier}
     */
    public AttributeIdentifier getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the value the attribute is modified with
     * 
     * @return the modifier value
     */
    public double getValue() {
        return value;
    }

    /**
     * Gets the type of modification made to the attribute
     * 
     * @return an {@link AttributeModifierType}
     */
    public AttributeModifierType getType() {
        return type;
    }

}
