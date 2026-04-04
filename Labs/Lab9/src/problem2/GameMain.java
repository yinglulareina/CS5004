package problem2;

import java.util.ArrayList;
import java.util.List;

// ─────────────────────────────────────────────────────────────────────────────
// Equipment interface (Decorator interface)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Equipment that can be attached to a character to modify its stats.
 * Used by the Decorator pattern.
 */
interface Equipment {
    int    getAttackBonus();
    int    getDefenseBonus();
    String getDescription();
}

// ─────────────────────────────────────────────────────────────────────────────
// Base abstract class — GameCharacter
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Base class for all characters. Implements Cloneable for the Prototype pattern.
 */
abstract class GameCharacter implements Cloneable {
    protected String name;
    protected int    health;
    protected int    attack;
    protected int    defense;
    protected List<Equipment> equipment = new ArrayList<>();

    public GameCharacter(String name, int health, int attack, int defense) {
        this.name    = name;
        this.health  = health;
        this.attack  = attack;
        this.defense = defense;
    }

    // Getters
    public String getName()    { return name; }
    public int getHealth()     { return health; }

    /** Total attack = base + all equipment bonuses. */
    public int getTotalAttack() {
        int bonus = 0;
        for (Equipment e : equipment) bonus += e.getAttackBonus();
        return attack + bonus;
    }

    /** Total defense = base + all equipment bonuses. */
    public int getTotalDefense() {
        int bonus = 0;
        for (Equipment e : equipment) bonus += e.getDefenseBonus();
        return defense + bonus;
    }

    public void addEquipment(Equipment e) { equipment.add(e); }

    /** PROTOTYPE pattern: every subclass must implement deep clone. */
    public abstract GameCharacter clone();

    public abstract String getDescription();

    @Override
    public String toString() {
        return getDescription()
                + " | HP:" + health
                + " ATK:" + getTotalAttack()
                + " DEF:" + getTotalDefense();
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Concrete character classes
// ─────────────────────────────────────────────────────────────────────────────

/** A high-defense melee fighter. */
class Warrior extends GameCharacter {
    public Warrior(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    @Override
    public GameCharacter clone() {
        Warrior copy = new Warrior(name, health, attack, defense);
        // deep copy: copy the equipment list so changes don't affect the original
        for (Equipment e : equipment) copy.addEquipment(e);
        return copy;
    }

    @Override
    public String getDescription() {
        return "[Warrior] " + name;
    }
}

/** A high-attack spell caster. */
class Mage extends GameCharacter {
    public Mage(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    @Override
    public GameCharacter clone() {
        Mage copy = new Mage(name, health, attack, defense);
        for (Equipment e : equipment) copy.addEquipment(e);
        return copy;
    }

    @Override
    public String getDescription() {
        return "[Mage] " + name;
    }
}

/** A ranged damage dealer. */
class Archer extends GameCharacter {
    public Archer(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    @Override
    public GameCharacter clone() {
        Archer copy = new Archer(name, health, attack, defense);
        for (Equipment e : equipment) copy.addEquipment(e);
        return copy;
    }

    @Override
    public String getDescription() {
        return "[Archer] " + name;
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PATTERN 1: Builder — CharacterBuilder
// ─────────────────────────────────────────────────────────────────────────────

/**
 * BUILDER pattern.
 *
 * Constructs a GameCharacter step by step. Instead of a constructor with
 * many parameters, the caller chains setName().setHealth().addEquipment().build().
 * This makes the creation readable and flexible — you only set what you need.
 */
class CharacterBuilder {
    private String name    = "Unknown";
    private int    health  = 100;
    private int    attack  = 10;
    private int    defense = 10;
    private String type    = "warrior";
    private final List<Equipment> equipment = new ArrayList<>();

    public CharacterBuilder setName(String name)       { this.name    = name;    return this; }
    public CharacterBuilder setHealth(int health)      { this.health  = health;  return this; }
    public CharacterBuilder setAttack(int attack)      { this.attack  = attack;  return this; }
    public CharacterBuilder setDefense(int defense)    { this.defense = defense; return this; }
    public CharacterBuilder setType(String type)       { this.type    = type;    return this; }
    public CharacterBuilder addEquipment(Equipment e)  { equipment.add(e);       return this; }

    /**
     * Builds and returns the configured GameCharacter.
     *
     * @return a new GameCharacter of the chosen type
     * @throws IllegalArgumentException if the type is unknown
     */
    public GameCharacter build() {
        GameCharacter character;
        switch (type.toLowerCase()) {
            case "warrior": character = new Warrior(name, health, attack, defense); break;
            case "mage":    character = new Mage(name, health, attack, defense);    break;
            case "archer":  character = new Archer(name, health, attack, defense);  break;
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }
        for (Equipment e : equipment) character.addEquipment(e);
        return character;
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PATTERN 2: Decorator — WeaponDecorator, ArmorDecorator
// ─────────────────────────────────────────────────────────────────────────────

/**
 * DECORATOR pattern — Weapon.
 *
 * Implements Equipment and wraps another Equipment (or stands alone),
 * adding an attack bonus on top of whatever the wrapped item provides.
 *
 * The key idea: decorators implement the same interface as what they wrap,
 * so they can be stacked (Sword → Enchanted Sword → Flaming Enchanted Sword).
 */
class WeaponDecorator implements Equipment {
    private final String name;
    private final int    attackBonus;

    public WeaponDecorator(String name, int attackBonus) {
        this.name        = name;
        this.attackBonus = attackBonus;
    }

    @Override public int    getAttackBonus()  { return attackBonus; }
    @Override public int    getDefenseBonus() { return 0; }
    @Override public String getDescription()  { return name + "(+ATK " + attackBonus + ")"; }
}

/**
 * DECORATOR pattern — Armor.
 * Adds a defense bonus.
 */
class ArmorDecorator implements Equipment {
    private final String name;
    private final int    defenseBonus;

    public ArmorDecorator(String name, int defenseBonus) {
        this.name         = name;
        this.defenseBonus = defenseBonus;
    }

    @Override public int    getAttackBonus()  { return 0; }
    @Override public int    getDefenseBonus() { return defenseBonus; }
    @Override public String getDescription()  { return name + "(+DEF " + defenseBonus + ")"; }
}

// ─────────────────────────────────────────────────────────────────────────────
// PATTERN 3: Factory Method — CharacterFactory
// ─────────────────────────────────────────────────────────────────────────────

/**
 * FACTORY METHOD pattern.
 *
 * Provides pre-configured character templates so callers don't have to
 * remember which stats go with which archetype. Pass "tank", "dps", or
 * "support" to get a ready-to-use character.
 */
class CharacterFactory {

    /**
     * Creates a pre-configured character template.
     *
     * @param archetype "tank", "dps", or "support"
     * @param name      the character's name
     * @return a fully configured GameCharacter
     */
    public static GameCharacter create(String archetype, String name) {
        switch (archetype.toLowerCase()) {
            case "tank":    // Warrior with high HP and defense
                return new CharacterBuilder()
                        .setName(name).setType("warrior")
                        .setHealth(200).setAttack(15).setDefense(40)
                        .addEquipment(new ArmorDecorator("Tower Shield", 20))
                        .build();

            case "dps":     // Mage with high attack
                return new CharacterBuilder()
                        .setName(name).setType("mage")
                        .setHealth(80).setAttack(60).setDefense(10)
                        .addEquipment(new WeaponDecorator("Staff of Flames", 25))
                        .build();

            case "support": // Archer balanced build
                return new CharacterBuilder()
                        .setName(name).setType("archer")
                        .setHealth(120).setAttack(30).setDefense(20)
                        .addEquipment(new WeaponDecorator("Longbow", 10))
                        .addEquipment(new ArmorDecorator("Leather Vest", 8))
                        .build();

            default: throw new IllegalArgumentException("Unknown archetype: " + archetype);
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// CharacterManager — demonstrates all patterns together
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Demonstrates creating, cloning, and decorating characters using all patterns.
 */
class CharacterManager {

    public void demo() {
        System.out.println("=== FACTORY METHOD: create archetypes ===");
        GameCharacter tank    = CharacterFactory.create("tank",    "Thor");
        GameCharacter dps     = CharacterFactory.create("dps",     "Merlin");
        GameCharacter support = CharacterFactory.create("support", "Legolas");
        System.out.println(tank);
        System.out.println(dps);
        System.out.println(support);

        System.out.println("\n=== BUILDER: custom character ===");
        GameCharacter custom = new CharacterBuilder()
                .setName("Zara").setType("warrior")
                .setHealth(150).setAttack(25).setDefense(25)
                .build();
        System.out.println(custom);

        System.out.println("\n=== PROTOTYPE: clone + modify ===");
        GameCharacter tankClone = tank.clone();
        tankClone.name = "Thor II";          // change the clone's name
        tankClone.addEquipment(new WeaponDecorator("Mjolnir", 30));
        System.out.println("Original : " + tank);
        System.out.println("Clone    : " + tankClone);

        System.out.println("\n=== DECORATOR: equip existing character ===");
        System.out.println("Before: " + custom);
        custom.addEquipment(new WeaponDecorator("Enchanted Sword", 20));
        custom.addEquipment(new ArmorDecorator("Dragon Scale Mail", 15));
        System.out.println("After : " + custom);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Main
// ─────────────────────────────────────────────────────────────────────────────

public class GameMain {
    public static void main(String[] args) {
        new CharacterManager().demo();
    }
}