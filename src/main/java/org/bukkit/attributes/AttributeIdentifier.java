package org.bukkit.attributes;

public enum AttributeIdentifier {

    MAX_HEALTH("generic.maxHealth"),
    FOLLOW_RANGE("generic.followRange"),
    KNOCKBACK_RESISTANCE("generic.knockbackResistance"),
    MOVEMENT_SPEED("generic.movementSpeed"),
    ATTACK_DAMAGE("generic.attackDamage"),
    JUMP_STRENGTH("horse.jumpStrength"),
    SPAWN_REINFORCEMENTS("zombie.spawnReinforcements");

    private final String name;

    private AttributeIdentifier(String identityString) {
        this.name = identityString;
    }

    public String getIdentityString() {
        return name;
    }

    public static AttributeIdentifier getByName(String identityString) {
        for (AttributeIdentifier identifier : AttributeIdentifier.values()) {
            if (identifier.getIdentityString().equals(identityString)) {
                return identifier;
            }
        }
        return null;
    }
}
