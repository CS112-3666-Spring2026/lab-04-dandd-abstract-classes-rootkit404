/**
 * Dwarf.java - A subclass of GameCharacter representing a Dwarf.
 * Dwarves assist other Dwarves by healing them, and assist others by giving gold.
 * In combat, their damage calculation changes depending on the opponent.
 *
 * @author Nathan Tshishimbi <W7404862@student.miracosta.edu>
 * @version 1.0
 * @date 03/10/2026
 */
public class Dwarf extends GameCharacter
{
    // ***** CONSTANTS ***** //
    public static final String DEFAULT_NAME = "Gimli", DEFAULT_CLASS_TYPE = "Cleric",
            DEFAULT_ALIGNMENT = "True Neutral";
    public static final int DEFAULT_GOLD = 1000000, DEFAULT_EXP_POINTS = 1,
            DEFAULT_HIT_POINTS = 1, DEFAULT_ARMOR_CLASS = 1;
    public static final Weapon DEFAULT_WEAPON_1 = new Weapon("Hammer", 6, 1, 2),
            DEFAULT_WEAPON_2 = new Weapon();


    // ***** CONSTRUCTORS ***** //
    /**
     * Full constructor for a Dwarf character.
     *
     * @param name character name
     * @param classType class type
     * @param alignment alignment
     * @param gold amount of gold
     * @param expPoints experience points
     * @param hitPoints hit points
     * @param armorClass armor class
     * @param weapon1 first weapon (required)
     * @param weapon2 second weapon (optional)
     */
    public Dwarf(String name, String classType, String alignment, int gold, int expPoints, int hitPoints, int armorClass,
                 Weapon weapon1, Weapon weapon2) {
        super(name, classType, alignment, gold, expPoints, hitPoints, armorClass, weapon1, weapon2);
    }

    /**
     * Default constructor that creates a Dwarf with preset attributes.
     */
    public Dwarf() {
        this(Dwarf.DEFAULT_NAME, Dwarf.DEFAULT_CLASS_TYPE, Dwarf.DEFAULT_ALIGNMENT, Dwarf.DEFAULT_GOLD,
                Dwarf.DEFAULT_EXP_POINTS, Dwarf.DEFAULT_HIT_POINTS, Dwarf.DEFAULT_ARMOR_CLASS,
                Dwarf.DEFAULT_WEAPON_1, Dwarf.DEFAULT_WEAPON_2);
    }

    /**
     * Copy constructor that creates a deep copy of a given Dwarf object.
     *
     * @param original the original Dwarf to copy
     */
    public Dwarf(Dwarf original) {
        super(original);
    }


    // ***** ABSTRACTION METHODS ***** //
    /* These are the abstract methods we essentially promised to fully build
    by putting their abstract method headers at the end of GameCharacter class
    */

    /**
     * Assists another GameCharacter.
     * If the other character is a Dwarf, heals them by 10 hit points.
     * Otherwise, gives them 5 gold.
     *
     * @param other GameCharacter to assist; modifies their hit points or gold
     */
    @Override
    public void assist(GameCharacter other) {
        if (other instanceof Dwarf) {
            int healed = other.getHitPoints() + 10;
            other.setHitPoints(healed);
            System.out.println("Assisted by healing 10");
        } else {
            int goldUpdated = other.getGold() + 5;
            other.setGold(goldUpdated);
            System.out.println("Assisted by giving 5 gold");
        }
    }

    /**
     * Attacks another GameCharacter.
     * <p>
     * If the target is a Dwarf, damage is based solely on the other’s armor class.
     * Otherwise, damage is based on this Dwarf’s armor class multiplied by 
     *  experience points divided by the other’s armor class.
     * Attack success is determined by a dice roll.
     *
     * @param other GameCharacter to attack; modifies their hit points if attack hits
     * @return true if the attack was successful (hit), false otherwise
     */
    @Override
    public boolean attack(GameCharacter other) {
        int diceRoll = (int) (Math.random() * 20) + 1;

        int attackDamage;

        // Modify damage based on opponent type
        if (other instanceof Dwarf) {
            attackDamage = this.getExpPoints() / other.getArmorClass();
        } else {
            attackDamage = this.getExpPoints() * this.getArmorClass() / other.getArmorClass();
        }

        int updatedHitPoints = other.getHitPoints();

        // Clamp damage so HP doesn't go below zero
        if (attackDamage > updatedHitPoints) {
            attackDamage = updatedHitPoints;
        }

        // Determine if attack hits
        if (diceRoll > 10) {
            updatedHitPoints -= attackDamage;
            other.setHitPoints(updatedHitPoints);
            System.out.println("Attacked for " + attackDamage + " damage");
            return true;
        } else {
            return false;
        }
    }
}