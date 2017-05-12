package ir.aut.ceit.test;

import ir.aut.ceit.*;
import ir.aut.ceit.cards.Card;
import ir.aut.ceit.cards.MonsterCard;
import ir.aut.ceit.PowerCard;
import ir.aut.ceit.decks.CardDeck;
import ir.aut.ceit.decks.SpecialDeck;

import java.util.Scanner;

/**
 * Created by Admin on 4/29/2017.
 */
public class DuelMonsters {
    private static Player player;
    private static Player cpu;
    private static Field playerField = new Field();
    private static Field cpuField = new Field();
    private static boolean playing;
    private static Scanner input;
    private static String status = "Duel Monsters Abridged";

    private static String[] monsterCardStrings = {
            "+-----+",
            "| O O |",
            "|VvvvV|",
            "|     |",
            "|^^^^^|",
            "+-----+"
    };
    private static String[] spellCardStrings = {
            "+-----+",
            "|@#@#@|",
            "|#@#@#|",
            "|@#@#@|",
            "|#@#@#|",
            "+-----+"
    };
    private static String[] emptySlotStrings = {
            "       ",
            "       ",
            "       ",
            "       ",
            "       ",
            "       "
    };

    /**
     * Main entry point for the program.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        setupPlayers();
        playing = true;
        input = new Scanner(System.in);
        while (playing) {
            playerMove();
            playing = playing && !player.isDefeated();
            cpuMove();
            playing = playing && !cpu.isDefeated();
        }
        if (player.isDefeated()) {
            System.out.println("You lost!");
        } else {
            System.out.println("You won!");
        }
        input.close();
    }

    /**
     * Prompts the player for a move choice, and executes that move.
     */
    private static void playerMove() {
        boolean turnEnded = false;
        while (!turnEnded) {
            printGameState();
            System.out.println();
            System.out.println("What would you like to do?\n"
                    + "1) Play card\n"
                    + "2) Attack\n"
                    + "3) End turn");
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    playerPlayCard();
                    break;
                case 2:
                    playerAttackCard();
                    break;
                case 3:
                    turnEnded = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        player.nextTurnPrep();
        playerField.cardTurnEffects(cpuField);
    }

    /**
     * Prompts the player for which card they want to play, and attempts
     * to play that card.
     */
    private static void playerPlayCard() {
        System.out.println("Type in the number of the card you want to "
                + "play");
        int selection = input.nextInt() - 1;
        boolean success = false;
        if ((selection >= 0) && (selection < player.getHand().length) && (player.getHand()[selection] != null)) {
            success = player.playCardFromHand(selection, playerField);
        } else if (selection == player.getHand().length
                && player.getNextSpecial() != null) {
            Special s = player.getNextSpecial();
            success = player.playSpecial(playerField);
            if (success) {
                s.instantEffect(playerField, cpuField);
            }
        }
        status = (success
                ? "Card played!"
                : "Play failed!");

    }

    /**
     * Prompts the player for which card they want to attack with, and which
     * monster they want to attack, and attempts to execute the attack.
     */
    private static void playerAttackCard() {
        System.out.println("Type in the number of the card you want to "
                + "attack with");
        int myMonster = input.nextInt() - 1;
        System.out.println("Type in the number of the enemy card you want to "
                + "attack");
        int enemyMonster = input.nextInt() - 1;
        boolean success = false;
        if (myMonster >= 0 && enemyMonster >= 0
                && myMonster < playerField.getMonsterCards().length
                && enemyMonster < cpuField.getMonsterCards().length
                && playerField.getMonsterCards()[myMonster] != null
                && playerField.getMonsterCards()[myMonster].getCanAttack()
                && cpuField.getMonsterCards()[enemyMonster] != null) {
            success = playerField.getMonsterCards()[myMonster].getPower()
                    >= cpuField.getMonsterCards()[enemyMonster].getPower();
            if (success) {
                cpu.changeLifePoints(
                        cpuField.getMonsterCards()[enemyMonster].getPower()
                                - playerField.getMonsterCards()[myMonster].getPower());
                cpuField.getMonsterCards()[enemyMonster] = null;
                playerField.getMonsterCards()[myMonster].setCanAttack(false);
            }
        }
        status = (success
                ? "Attack successful!"
                : "Attack failed!");
    }

    /**
     * Makes the cpu put down as many of the cards from their hand as they
     * can.
     */
    private static void cpuMove() {
        for (int i = 0; i < cpu.getHand().length; i++) {
            cpu.playCardFromHand(i, cpuField);
        }
        cpu.nextTurnPrep();
        cpuField.cardTurnEffects(playerField);
    }

    /**
     * Prints out information about the game state.
     */
    private static void printGameState() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
        System.out.println(status);
        status = "Duel Monsters Abridged";
        System.out.println("Your life points: " + player.getLifePoints());
        System.out.println("Enemy life points: " + cpu.getLifePoints());
        System.out.println("Enemy Cards");
        System.out.println();
        printCards(spellCardStrings, cpuField.getSpellCards());
        printCards(monsterCardStrings, cpuField.getMonsterCards());
        System.out.println();
        System.out.println("Enemy Monsters");
        System.out.println();
        printNumberedArray(cpuField.getMonsterCards());
        System.out.println("Enemy Spells");
        printNumberedArray(cpuField.getSpellCards());
        System.out.println();
        System.out.println("Your Cards");
        printCards(monsterCardStrings, playerField.getMonsterCards());
        printCards(spellCardStrings, playerField.getSpellCards());
        System.out.println();
        System.out.println("Player Monsters");
        printNumberedArray(playerField.getMonsterCards());
        System.out.println();
        System.out.println("Player Spells");
        printNumberedArray(playerField.getSpellCards());
        System.out.println();
        System.out.println("Your Hand");
        printNumberedArray(player.getHand());
        System.out.println();
        System.out.println("Your Special Card");
        if (player.getNextSpecial() != null) {
            System.out.println(playerField.getMonsterCards().length + 1 + ": "
                    + player.getNextSpecial());
        }
    }

    /**
     * Prints out an array, with each element numbered.
     *
     * @param things the array to print out.
     */
    private static void printNumberedArray(Card[] things) {
        int i = 1;
        for (Object c : things) {
            if (c != null) {
                System.out.println(i + ": " + c);
            }
            i++;
        }
    }

    /**
     * Prints out the visual representations of the cards.
     *  @param cardStrings the array of strings to represent the cards with.
     * @param cards       the cards to actually print out.
     */
    private static void printCards(String[] cardStrings, Card[] cards) {
        for (int i = 0; i < cardStrings.length; i++) {
            for (Card card : cards) {
                if (card != null) {
                    System.out.print(cardStrings[i]);
                } else {
                    System.out.print(emptySlotStrings[i]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Sets up the players decks and hands and such.
     */
    private static void setupPlayers() {
        CardDeck playerDeck = new CardDeck(
                new MonsterCard("Perfectly Ultimate Great Moth",
                        "One of the most powerful cards in all of Duel Monsters",
                        2600),
                new MonsterCard("Swordsman of Landstar",
                        "One of the most powerful cards in all of Duel Monsters",
                        500),
                new MonsterCard("Jeff Goldblum",
                        "One of the most powerful cards in all of Duel Monsters",
                        3000),
                new MonsterCard("Insect Queen",
                        "One of the most powerful cards in all of Duel Monsters",
                        2200),
                new PowerCard(),
                new MonsterCard("Disgruntled Celtic Guard",
                        "Very disgruntled",
                        500),
                new PowerCard(),
                new MonsterCard("Kuriboh",
                        "Just Kuriboh",
                        500),
                new PowerCard(),
                new MonsterCard("Baby Dragon",
                        "rar",
                        500),
                new PowerCard(),
                new MonsterCard("Red Eyes Black Dragon",
                        "Nifty card",
                        500)
        );
        CardDeck cpuDeck = new CardDeck(
                new MonsterCard("Perfectly Ultimate Great Moth",
                        "One of the most powerful cards in all of Duel Monsters",
                        2600),
                new MonsterCard("Swordsman of Landstar",
                        "One of the most powerful cards in all of Duel Monsters",
                        500),
                new MonsterCard("Jeff Goldblum",
                        "One of the most powerful cards in all of Duel Monsters",
                        3000),
                new MonsterCard("Insect Queen",
                        "One of the most powerful cards in all of Duel Monsters",
                        2200),
                new PowerCard(),
                new MonsterCard("Disgruntled Celtic Guard",
                        "Very disgruntled",
                        500),
                new PowerCard(),
                new MonsterCard("Kuriboh",
                        "Just Kuriboh",
                        500),
                new PowerCard(),
                new MonsterCard("Baby Dragon",
                        "rar",
                        500),
                new PowerCard(),
                new MonsterCard("Red Eyes Black Dragon",
                        "Nifty card",
                        500)
        );
        SpecialDeck playerSpecials = new SpecialDeck(new DestroySpell(), new BlueEyesWhiteDragon());
        SpecialDeck cpuSpecials = new SpecialDeck(new DestroySpell(), new BlueEyesWhiteDragon());

        player = new Player(playerDeck, playerSpecials);
        cpu = new Player(cpuDeck, cpuSpecials);
        player.draw(5);
        player.drawSpecialCard();
        cpu.draw(5);
        cpu.drawSpecialCard();
    }

}
