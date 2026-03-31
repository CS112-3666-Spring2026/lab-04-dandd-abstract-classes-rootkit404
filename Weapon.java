/**
 * Weapon.java - Represents a weapon, tracking name, damage,
 * range, and any attack bonus it provides.
 * <p>
 * A weapon may inflict damage, has a maximum range, and may grant
 * bonus attack points. Values are validated on construction or mutation.
 *
 * @author Nathan Tshishimbi <W7404862@student.miracosta.edu>
 * @version 1.0
 * @date 03/10/2026
 */
public class Weapon
{
    // ***** CONSTANTS ***** //
    public static final String DEFAULT_NAME = "Club";
    public static final int DEFAULT_DAMAGE = 1;
    public static final int DEFAULT_RANGE = 1;
    public static final int DEFAULT_ATTACK_BONUS = 0;


    // ***** INSTANCE VARIABLES ***** //
    private String name;
    private int damage, range, attackBonus;


    // ***** CONSTRUCTORS ***** //
    /**
     * Full Constructor with error handling (program shuts down on invalid data)
     *
     * @param name name of the weapon
     * @param damage how much damage the weapon inflicts
     * @param range how far the weapon can reach
     * @param attackBonus number of bonus points the weapon gives per attack
     */
    public Weapon(String name, int damage, int range, int attackBonus) {
        if (!this.setAll(name, damage, range, attackBonus)) {
            System.out.println("ERROR: Bad data given to full (Weapon) constructor.");
            System.exit(0);
        }
    }

    /**
     * Default Constructor using class constant values
     */
    public Weapon() {
        this(DEFAULT_NAME, DEFAULT_DAMAGE, DEFAULT_RANGE, DEFAULT_ATTACK_BONUS);
    }

    /**
     * Copy constructor creates a deep copy of all instance variables
     * with error handling (program shuts down on invalid data)
     *
     * @param original Weapon object to copy
     */
    public Weapon(Weapon original) {
        if (original == null) {
            System.out.println("ERROR: null data given to copy constructor. Program shutting down...");
            System.exit(0);
        } else {
            this.setAll(original.name, original.damage, original.range, original.attackBonus);
        }
    }


    // ***** SETTERS / MUTATORS ***** //
    /**
     * Sets the name of the weapon (with error checking)
     *
     * @param name name of the weapon
     * @return true if valid (non-null and non-empty), false otherwise
     */
    public boolean setName(String name) {
        if (name != null && name.length() > 0) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets how much damage the weapon inflicts (with error checking)
     *
     * @param damage amount of damage
     * @return true if valid (>= 0), false otherwise
     */
    public boolean setDamage(int damage) {
        if (damage >= 0) {
            this.damage = damage;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets how far the weapon can reach (with error checking)
     *
     * @param range weapon range
     * @return true if valid (>= 0), false otherwise
     */
    public boolean setRange(int range) {
        if (range >= 0) {
            this.range = range;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the number of bonus points the weapon gives per attack (with error checking)
     *
     * @param attackBonus attack bonus
     * @return true if valid (>= 0), false otherwise
     */
    public boolean setAttackBonus(int attackBonus) {
        if (attackBonus >= 0) {
            this.attackBonus = attackBonus;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets all instance variables at once (with validation)
     *
     * @param name weapon name
     * @param damage amount of damage
     * @param range weapon range
     * @param attackBonus attack bonus
     * @return true if all parameters are valid, false otherwise
     */
    public boolean setAll(String name, int damage, int range, int attackBonus) {
        return this.setName(name) && this.setDamage(damage) &&
                this.setRange(range) && this.setAttackBonus(attackBonus);
    }


    // ***** GETTERS / ACCESSORS ***** //
    /**
     * Gets the name of the weapon
     *
     * @return weapon name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the damage value of the weapon
     *
     * @return amount of damage
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Gets the range of the weapon
     *
     * @return weapon range
     */
    public int getRange() {
        return this.range;
    }

    /**
     * Gets the attack bonus provided by the weapon
     *
     * @return attack bonus value
     */
    public int getAttackBonus() {
        return this.attackBonus;
    }


    // ***** OTHER REQUIRED METHODS *****//
    /**
     * Returns a String representation of the Weapon object
     *
     * @return formatted string of weapon attributes
     */
    @Override
    public String toString() {
        return String.format("Weapon: %s, Damage: %d, Range: %d, Attack Bonus: %d",
                this.name, this.damage, this.range, this.attackBonus);
    }

    /**
     * Checks if another object is equal to this Weapon
     *
     * @param other object to compare
     * @return true if other is a Weapon and has identical data, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        Weapon otherWeapon = (Weapon) other;
        return this.name.equals(otherWeapon.name) &&
                this.damage == otherWeapon.damage &&
                this.range == otherWeapon.range &&
                this.attackBonus == otherWeapon.attackBonus;
    }
}