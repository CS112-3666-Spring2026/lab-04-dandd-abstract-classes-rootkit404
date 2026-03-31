/**
 * GameCharacter.java - Represents a game character with stats, alignment,
 * class type, and equipped weapons.
 * <p>
 * Class invariant: all int values must be >= 0, name exists (not null or length > 0),
 * class alignments have to be part of allowed values (limited to classes from Player's Handbook),
 * weapon 1 must exist, weapon 2 can be null.
 *
 * @author Nathan Tshishimbi <W7404862@student.miracosta.edu>
 * @version 1.0
 * @date 03/10/2026
 */
public abstract class GameCharacter
{
    //***** CONSTANTS *****//
    public static final String DEFAULT_NAME = "Frodo", DEFAULT_CLASS_TYPE = "Bard", DEFAULT_ALIGNMENT = "Neutral";
    public static final int DEFAULT_GOLD = 100, DEFAULT_EXP_POINTS = 1, DEFAULT_HIT_POINTS = 1, DEFAULT_ARMOR_CLASS = 1;
    public static final Weapon DEFAULT_WEAPON_1 = new Weapon(), DEFAULT_WEAPON_2 = null;

    public static final String[] VALID_CLASSES = { "Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin",
            "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"};
    public static final String[] VALID_ALIGNMENTS = {"Lawful Good", "Neutral Good", "Chaotic Good", "Lawful Neutral",
            "Neutral", "Chaotic Neutral", "Lawful Evil", "Neutral Evil", "Chaotic Evil", "Unaligned"};


    //***** INSTANCE VARIABLES *****//
    private String name, classType, alignment;
    private int gold, expPoints, hitPoints, armorClass;
    private Weapon weapon1, weapon2;


    //***** CONSTRUCTORS *****//
    /**
     * Full constructor
     *
     * @param name character's name
     * @param classType character's class type
     * @param alignment character's alignment
     * @param gold amount of gold
     * @param expPoints experience points
     * @param hitPoints hit points
     * @param armorClass armor class
     * @param weapon1 primary weapon (required)
     * @param weapon2 secondary weapon (optional, can be null)
     */
    public GameCharacter(String name, String classType, String alignment, int gold, int expPoints, int hitPoints,
                         int armorClass, Weapon weapon1, Weapon weapon2) {

        if(!this.setAll(name, classType, alignment, gold, expPoints, hitPoints, armorClass, weapon1, weapon2)) {
            System.out.println("ERROR: invalid data given to full GameCharacter constructor. Shutting down.");
            System.exit(0);
        }
    }

    /**
     * Default constructor
     */
    public GameCharacter() {
        this(DEFAULT_NAME, DEFAULT_CLASS_TYPE, DEFAULT_ALIGNMENT, DEFAULT_GOLD, DEFAULT_EXP_POINTS, DEFAULT_HIT_POINTS,
                DEFAULT_ARMOR_CLASS, DEFAULT_WEAPON_1, DEFAULT_WEAPON_2);
    }

    /**
     * Copy constructor
     *
     * @param original GameCharacter object to copy
     */
    public GameCharacter (GameCharacter original) {
        if(original == null) {
            System.out.println("ERROR: null data given to copy GameCharacter constructor. Shutting down.");
            System.exit(0);
        } else {
            this.setAll(original.name, original.classType, original.alignment, original.gold, original.expPoints,
                    original.hitPoints, original.armorClass, original.weapon1, original.weapon2);
        }
    }


    //***** SETTERS / MUTATORS *****//
    /**
     * Sets the name of the character
     *
     * @param name character's name
     * @return true if valid (not null or empty), false otherwise
     */
    public boolean setName(String name) {
        if (name != null && name.length() > 0) { //or can use !name.isEmpty()
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the class type of the character
     *
     * @param classType character's class type
     * @return true if classType is valid, false otherwise
     */
    public boolean setClassType(String classType) {
        if (GameCharacter.isInArray(VALID_CLASSES, classType)) {
            this.classType = classType;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the alignment of the character
     *
     * @param alignment character's alignment
     * @return true if alignment is valid, false otherwise
     */
    public boolean setAlignment(String alignment) {
        if (GameCharacter.isInArray(VALID_ALIGNMENTS, alignment)) {
            this.alignment = alignment;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets amount of gold
     *
     * @param gold amount of gold
     * @return true if valid (>= 0), false otherwise
     */
    public boolean setGold(int gold) {
        if (gold >= 0) {
            this.gold = gold;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets experience points
     *
     * @param expPoints experience points
     * @return true if valid (>= 0), false otherwise
     */
    public boolean setExpPoints(int expPoints) {
        if (expPoints >= 0) {
            this.expPoints = expPoints;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets hit points
     *
     * @param hitPoints hit points
     * @return true if valid (>= 0), false otherwise
     */
    public boolean setHitPoints(int hitPoints) {
        if (hitPoints >= 0) {
            this.hitPoints = hitPoints;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets armor class
     *
     * @param armorClass armor class
     * @return true if valid (>= 0), false otherwise
     */
    public boolean setArmorClass(int armorClass) {
        if (armorClass >= 0) {
            this.armorClass = armorClass;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the first weapon (required)
     *
     * @param weapon weapon object
     * @return true if weapon is not null, false otherwise
     */
    public boolean setWeapon1(Weapon weapon) {
        if (weapon != null) {
            this.weapon1 = new Weapon(weapon); // deep copy so there are no leaks, and nothing break encapsulation
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the second weapon (optional)
     *
     * @param weapon weapon object (can be null)
     * @return always true
     */
    public boolean setWeapon2(Weapon weapon) {
        if (weapon == null) {
            this.weapon2 = null;

        } else {
            this.weapon2 = new Weapon(weapon);
        }
        return true;
    }

    /**
     * Sets all instance variables at once
     *
     * @param name character's name
     * @param classType character's class type
     * @param alignment character's alignment
     * @param gold amount of gold
     * @param expPoints experience points
     * @param hitPoints hit points
     * @param armorClass armor class
     * @param weapon1 primary weapon
     * @param weapon2 secondary weapon
     * @return true if all setters succeed, false otherwise
     */
    public boolean setAll(String name, String classType, String alignment, int gold, int expPoints, int hitPoints,
                          int armorClass, Weapon weapon1, Weapon weapon2) {

        return this.setName(name) && this.setClassType(classType) && this.setAlignment(alignment) && this.setGold(gold)
                && this.setExpPoints(expPoints) && this.setHitPoints(hitPoints) && this.setArmorClass(armorClass)
                && this.setWeapon1(weapon1) && this.setWeapon2(weapon2);
    }



    // ***** GETTERS / ACCESSORS *****//
    /**
     * Gets the name of the character
     *
     * @return character's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the class type of the character
     *
     * @return character's class type
     */
    public String getClassType() {
        return this.classType;
    }

    /**
     * Gets the alignment of the character
     *
     * @return character's alignment
     */
    public String getAlignment() {
        return this.alignment;
    }

    /**
     * Gets amount of gold
     *
     * @return gold amount
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Gets experience points
     *
     * @return experience points
     */
    public int getExpPoints() {
        return this.expPoints;
    }

    /**
     * Gets hit points
     *
     * @return hit points
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Gets armor class
     *
     * @return armor class
     */
    public int getArmorClass() {
        return this.armorClass;
    }

    /**
     * Gets the first weapon (deep copy)
     *
     * @return copy of weapon1
     */
    public Weapon getWeapon1() {
        return new Weapon(this.weapon1);
    }

    /**
     * Gets the second weapon (deep copy)
     *
     * @return copy of weapon2 or null if not set
     */
    public Weapon getWeapon2() {
        if (this.weapon2 == null) {
            return null;
        } else {
            return new Weapon(this.weapon2);
        }
    }


    // ***** OTHER REQUIRED METHODS *****//
    /**
     * Returns a String representation of the GameCharacter
     *
     * @return formatted character data
     */
    @Override
    public String toString() {
        // Create weapons string
        String weapons = "Equipped = {" + this.weapon1 + "}";
        if (this.weapon2 != null) {
            weapons += " + {" + this.weapon2 + "}";
        }

        return String.format("GameCharacter: Name = %s, Class = %s, Alignment = %s, "
                        + "Gold = %d, XP = %d, HP = %d, Armor Class = %d,%n\t%s",
                this.name, this.classType, this.alignment,
                this.gold, this.expPoints, this.hitPoints, this.armorClass,
                weapons);
    }

    /**
     * Checks equality between this and another object
     *
     * @param other object to compare
     * @return true if all data matches, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof GameCharacter)) {
            return false;
        }
        GameCharacter otherCharacter = (GameCharacter) other;

        boolean secondWeaponsEqual =
                (this.weapon2 == null && otherCharacter.weapon2 == null) ||
                        this.weapon2.equals(otherCharacter.weapon2);

        return this.name.equals(otherCharacter.name)
                && this.classType.equals(otherCharacter.classType)
                && this.alignment.equals(otherCharacter.alignment)
                && this.gold == otherCharacter.gold
                && this.expPoints == otherCharacter.expPoints
                && this.hitPoints == otherCharacter.hitPoints
                && this.armorClass == otherCharacter.armorClass
                && this.weapon1.equals(otherCharacter.weapon1)
                && secondWeaponsEqual;
    }


    // ***** HELPER METHODS *****//
    /**
     * Checks if a value is in a given array of strings
     *
     * @param values array of valid strings
     * @param value string to test
     * @return true if value exists in array, false otherwise
     */
    private static boolean isInArray(String[] values, String value) {
        if (values == null || value == null) {
            return false;
        }
        /*
         for (String item : values) {
             if (item.equals(value)) {
                 return true;
             }
         }
        */

        int locationInArray = 0;
        boolean valueIsPresent = false;

        while (!valueIsPresent && locationInArray < values.length) {
            valueIsPresent = values[locationInArray].equalsIgnoreCase(value); //will return a boolean assigned to valueIsPresent
            locationInArray++;
        }

        return valueIsPresent;
    }


    public String toAscii() {
        final String BAR = "════════════════════════════════════", DIVIDER = "╠" + BAR + "╣";
        final int BAR_LENGTH = BAR.length();

        String identity = this.name + " (" + this.getClass().getCanonicalName() + " / " + this.classType + ")";
        String hp = "HP: " + this.hitPoints, xp = "XP: " + this.expPoints, armor = "AC: " + this.armorClass,
                gold = String.format("%,dG", this.gold);
        int identityCenterWidth = (BAR_LENGTH + identity.length()) / 2;
        int alignmentCenterWidth = (this.alignment.length() + BAR_LENGTH) / 2;
        int specWidth = BAR.length() / 4;

        //Top of box, contains: name, race, class, and alignment
        String ascii = "╔" + BAR + "╗\n";
        ascii += String.format(
                "║%" + identityCenterWidth + "s%" + (BAR_LENGTH - identityCenterWidth) + "s║%n", identity, ""); //center identity in ascii box
        ascii += String.format(
                "║%" + alignmentCenterWidth + "s%" + (BAR_LENGTH - alignmentCenterWidth) + "s║%n", this.alignment,
                ""); //center alignment in ascii box
        ascii += DIVIDER + "\n";

        // Add on each of the stats on same row (note -2-1 because of extra space added after ║)
        ascii += String.format("║ %-" + (specWidth - 2) + "s", hp);
        ascii += String.format("║ %-" + (specWidth - 2) + "s", xp);
        ascii += String.format("║ %-" + (specWidth - 2) + "s", armor);
        ascii += String.format("║ %" + (specWidth - 2) + "s ║\n", gold);

        //Bottom of box
        ascii += "╚" + BAR + "╝";

        return ascii;
    }


    // ***** ABSTRACT METHODS *****//
    /**
     * Abstract method to assist another character
     *
     * @param other GameCharacter to assist
     */
    public abstract void assist(GameCharacter other);

    /**
     * Abstract method to attack another character
     *
     * @param other GameCharacter to attack
     * @return true if attack was successful, false otherwise
     */
    public abstract boolean attack(GameCharacter other);
}