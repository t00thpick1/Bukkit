package org.bukkit.attributes;

public interface Attribute {

    public AttributeIdentifier getIdentifier();

    public double getBaseValue();

    public void setBaseValue(double value);

    public double getValue();
}
