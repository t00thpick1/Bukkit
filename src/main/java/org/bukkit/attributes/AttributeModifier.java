package org.bukkit.attributes;

/**
 * Represents an attribute modifier.
 */
public class AttributeModifier {
    private AttributeIdentifier identifier;
    private double value;
    private AttributeModifierType modifierType;

    private AttributeModifier(AttributeIdentifier identifier, double value, AttributeModifierType type) {
        this.identifier = identifier;
        this.value = value;
        this.modifierType = type;
    }
    /**
     * Gets the attribute identifier used to determine which attribute to modify
     * 
     * @return an {@link AttributeIdentifier}
     */
    public AttributeIdentifier getIdentifier() {
        return identifier;
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
    public AttributeModifierType getModifierType() {
        return modifierType;
    }

    /**
     * Creates a new AttributeModifier with the given values
     * @param identifier
     * @param value
     * @param type
     * @return a new AttributeModifier
     */
    public static AttributeModifier createAttributeModifier(AttributeIdentifier identifier, double value, AttributeModifierType type) {
        return new AttributeModifier(identifier, value, type);
    }
}
