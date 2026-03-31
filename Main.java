import java.util.Scanner;
/**
 * Main.java - Represents driver class to run game
 *
 * @author Nathan Tshishimbi <W7404862@student.miracosta.edu>
 * @version 1.0
 * @date 03/10/2026
 */

public class Main {

    public static void main(String[] args) {

        //***** DECLARATION / INITIALIZAITON *****//
        Scanner keyboard = new Scanner(System.in);


        /*
         ===== START OF TESTING (Weapon.java) ===== //
         // TESTING SETTERS
         Weapon hammer = new Weapon();
         System.out.println(hammer);
         // GOOD TEST
         hammer.setName("Mjolnir");
         hammer.setDamage(25);
         hammer.setRange(10);
         hammer.setAttackBonus(12);
         // BAD TEST (sets values to null or 0 respectively)
         hammer.setName("");
         hammer.setDamage(-300);
         hammer.setRange(-5);
         hammer.setAttackBonus(-123);
         // TESTING FULL CONSTRUCTOR
         Weapon goodTest = new Weapon("Mjolnir", 25, 10, 12);
         Weapon badTest = new Weapon("", -300, -5, -123);
         System.out.println(goodTest);
         System.out.println(badTest);
         ===== TESTING (GameCharacter.java) ===== //
         // TESTING SETTERS
         GameCharacter goku = new GameCharacter(); // testing toString method
         System.out.println(goku);
         GameCharacter elf = new GameCharacter("Varis Liadon", "Monk", "Lawful Neutral", 50000, 90, 100, 5,
                 new Weapon("Longbow", 8, 150, 2), null);
         System.out.println(elf);
         ===== END OF TESTING CODE ===== //
        */

        //Create objects for all 2 players of type dwarf
        GameCharacter player1 = new Dwarf("Durnan Goldhammer", "Fighter", "Chaotic Neutral", 100, 450, 200, 8,
                new Weapon("Warhammer", 8, 2, 4), null);
        GameCharacter player2 = new Dwarf("Balin Ironfoot", "Barbarian", "Chaotic Evil", 5000, 250, 220, 3,
                new Weapon("Battleaxe", 6, 2, 2), Dwarf.DEFAULT_WEAPON_1);


        boolean battleDone = false;
        GameCharacter winner = null, loser = null, offense, defense, temp;
        int menuChoice;

        System.out.println("\nFlipping coin...");
        int simulateFlip = (int) (Math.random() * 2); // gives a value of either 0 or 1

        if (simulateFlip == 0) { //heads
            System.out.println("... It's heads!");
            offense = player1;
            defense = player2;
        } else { //tails
            System.out.println("... It's tails!");
            offense = player2;
            defense = player1;
        }
        System.out.println(offense.getName() + " goes first!\n");


        //***** INPUT & PROCESSING SECTION *****//
        do {
            Main.printSideBySide(offense, defense);
            System.out.println();

            //Print menu + input choices
            System.out.println(offense.getName() + ", what would you like to do?\n");
            System.out.println("1] Attack");
            System.out.println("2] Assist (Attempt to befriend)");
            System.out.println("3] Run Away");
            System.out.println("Enter choice >> ");
            menuChoice = keyboard.nextInt();
            System.out.println();

            //Switch cases to handle user imput
            switch(menuChoice) {
                case 1:
                    battleDone = Main.attackChoice(offense, defense);
                    if (battleDone) {
                        winner = offense;
                        loser = defense;
                        System.out.println("\n" + winner.getName() + " has KO'd" + loser.getName() + "!");
                    }
                    break;
                case 2:
                    Main.assistChoice(offense, defense);
                    System.out.println(offense.getName() + " assisted " + defense.getName() + "!");
                    break;
                case 3:
                    System.out.println(offense.getName() + " ran away?! Why?? We may never know...");
                    battleDone = true;
                    winner = defense;
                    loser = offense;
                    break;
                default:
                    System.out.println("\nERROR: please try again and enter valid choice (1-3)");
                    break;
            }
            //Switch for the next round
            System.out.println();
            temp = offense;
            offense = defense;
            defense = temp;

        } while (!battleDone);

        keyboard.close();

        System.out.println("\n!!! " + winner.getName() + " beat " + loser.getName() + " !!!\n\n");

        System.out.println(
                """
                        ███████╗██╗███╗   ██╗ █████╗ ██╗         ███████╗████████╗ █████╗ ████████╗███████╗
                        ██╔════╝██║████╗  ██║██╔══██╗██║         ██╔════╝╚══██╔══╝██╔══██╗╚══██╔══╝██╔════╝
                        █████╗  ██║██╔██╗ ██║███████║██║         ███████╗   ██║   ███████║   ██║   ███████╗
                        ██╔══╝  ██║██║╚██╗██║██╔══██║██║         ╚════██║   ██║   ██╔══██║   ██║   ╚════██║
                        ██║     ██║██║ ╚████║██║  ██║███████╗    ███████║   ██║   ██║  ██║   ██║   ███████║
                        ╚═╝     ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝    ╚══════╝   ╚═╝   ╚═╝  ╚═╝   ╚═╝   ╚══════╝

                        """
        );
        System.out.println("Winner:\n" + winner.toAscii() + "\n");
        System.out.println("Loser:\n" + loser.toAscii() + "\n");

        System.out.println("Thanks for playing!");
    }


    //***** EXTRA CLASS METHODS ***** //
    public static void assistChoice(GameCharacter assister, GameCharacter receiver) {
        assister.assist(receiver);
    }

    // Method return ture if defender is defeated, false if still has HP left
    public static boolean attackChoice(GameCharacter attacker, GameCharacter defender) {
        boolean successfulAttack = attacker.attack(defender);

        if (successfulAttack) {
            System.out.println("It was a hit! " + defender.getName() + " has taken damage...");
        } else {
            System.out.println("It was a miss! " + defender.getName() + " has taken no damage...");
        }

        return defender.getHitPoints() == 0;
    }

    public static void printSideBySide(GameCharacter left, GameCharacter right) {
        String[] leftParts = left.toAscii().split("\n");
        String[] rightParts = right.toAscii().split("\n");

        for (int i = 0; i < leftParts.length; i++) {
            System.out.print(leftParts[i]);

            if (i == leftParts.length / 2) {
                System.out.print("-->");
            } else {
                System.out.print("   ");
            }

            System.out.println(rightParts[i]);
        }
    }
}