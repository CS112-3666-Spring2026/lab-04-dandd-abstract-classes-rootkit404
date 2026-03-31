/**
 * Elf.java - A subclass of GameCharacter representing an Elf.
 * Elves assist other Elves by healing them, and assist others by giving gold.
 * In combat, they have different hit chances and damage based on opponent type.
 *
 * @author Nathan Tshishimbi <W7404862@student.miracosta.edu>
 * @version 1.0
 * @date 03/10/2026
 */
public class Elf extends GameCharacter
{
    // ***** CONSTANTS ***** //
    public static final String DEFAULT_NAME = "Legolas", DEFAULT_CLASS_TYPE = "Druid",
            DEFAULT_ALIGNMENT = "Lawful Neutral";
    public static final int DEFAULT_GOLD = 1000000, DEFAULT_EXP_POINTS = 1, DEFAULT_HIT_POINTS = 1, DEFAULT_ARMOR_CLASS = 1;
    public static final Weapon DEFAULT_WEAPON_1 = new Weapon("Crossbow", 6, 30, 3), DEFAULT_WEAPON_2 = new Weapon();


    // ***** CONSTRUCTORS ***** //
    /**
     * Full constructor for an Elf character.
     *
     * @param name character name
     * @param classType class type
     * @param alignment alignment
     * @param gold amount of gold
     * @param expPoints experience points
     * @param hitPoints hit points
     * @param armorClass armor class
     * @param weapon1 first weapon
     * @param weapon2 second weapon
     */
    public Elf(String name, String classType, String alignment, int gold, int expPoints, int hitPoints, int armorClass,
               Weapon weapon1, Weapon weapon2) {
        super(name, classType, alignment, gold, expPoints, hitPoints, armorClass, weapon1, weapon2);
    }

    /**
     * Default constructor that creates an Elf with preset attributes.
     */
    public Elf() {
        this(Elf.DEFAULT_NAME, Elf.DEFAULT_CLASS_TYPE, Elf.DEFAULT_ALIGNMENT, Elf.DEFAULT_GOLD, Elf.DEFAULT_EXP_POINTS,
                Elf.DEFAULT_HIT_POINTS, Elf.DEFAULT_ARMOR_CLASS, Elf.DEFAULT_WEAPON_1, Elf.DEFAULT_WEAPON_2);
    }

    /**
     * Copy constructor that creates a deep copy of a given Elf object.
     *
     * @param original the original Elf to copy
     */
    public Elf(Elf original) {
        super(original);
    }


    // ***** ABSTRACTION METHODS ***** //
    /* These are the abstract methods we essentially promised to fully build
    by putting their abstract method headers at the end of GameCharacter class
    */

    /**
     * Assists another GameCharacter.
     * <p>
     * If the other character is an Elf, heals them by 10 hit points.
     * Otherwise, gives them 5 gold.
     *
     * @param other GameCharacter to assist; modifies their hit points or gold
     */
    @Override
    public void assist(GameCharacter other) {
        if (other instanceof Elf) {
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
     * If the target is an Elf, this Elf is more likely to hit but deals less damage.
     * Otherwise, less likely to hit but deals more damage.
     *
     * @param other GameCharacter to attack; modifies their hit points if attack hits
     * @return true if the attack was successful, false otherwise
     */
    @Override
    public boolean attack(GameCharacter other) {
        int diceRoll = (int) (Math.random() * 20) + 1;

        // Calculate base damage
        int attackDamage = this.getExpPoints() / other.getArmorClass();
        int updatedHitPoints = other.getHitPoints();

        // Modify damage based on character type 
        if (other instanceof Elf) {
            attackDamage /= 2;
            diceRoll += 5;
        }

        // Clamp damage so HP doesn't go below zero
        if (attackDamage > updatedHitPoints) {
            attackDamage = updatedHitPoints;
        }

        // Determine if attack hits
        if (diceRoll > 10) {
            updatedHitPoints -= attackDamage;
            other.setHitPoints(updatedHitPoints);
            return true;
        } else {
            return false;
        }
    }
}