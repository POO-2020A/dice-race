
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chalosalvador
 */
public class DiceRace {
    public static void main(String[] args) {
        int player1 = 0;
        int player2 = 0;
        int playerTurn = 1;
        int rollResult = 0;
        Scanner scanner = new Scanner(System.in);
        printGameStatus(player1, player2);
        ArrayList<String> list = new ArrayList<>();
        do {
            System.out.println("Jugador " + playerTurn + " presione enter para lanzar los dados");
            scanner.nextLine();
            rollResult = monopolyRoll();

            if (playerTurn == 1) {
                // si obtuvo 3 dobles, vuelve al inicio
                if (rollResult == -1) {
                    player1 = 0;
                } else {
                    // No puede pasarse de 50 para llegar tiene que suma 50 exacto
                    if (player1 + rollResult <= 50) {
                        player1 += rollResult;

                        // si la nueva posición de player2 es igual a player1
                        if (player1 == player2) {
                            // si player2 no está en una base, vuelve al inicio
                            if (player2 % 10 != 0) {
                                player2 = 0;
                                System.out.println("Jugador 1 atrapó a Jugador 2, Jugador 2 volvió al inicio.");
                            } else {
                                // si player2 está en base entonces player1 no puede avanzar
                                player1 -= rollResult;
                                System.out.println("Jugador 2 está en base, Jugador 1 no puede avanzar.");
                            }
                        }
                    } else {
                        System.out.println("Jugador 1 no puede avanzar. Debe llegar máximo a 50");
                    }
                }

                playerTurn = 2;
            } else {
                if (rollResult == -1) {
                    player2 = 0;
                } else {
                    if (player2 + rollResult <= 50) {
                        player2 += rollResult;

                        if (player2 == player1) {
                            if (player1 % 10 != 0) {
                                player1 = 0;
                                System.out.println("Jugador 2 atrapó a Jugador 1, Jugador 1 volvió al inicio.");
                            } else {
                                player2 -= rollResult;
                                System.out.println("Jugador 1 está en base, Jugador 2 no puede avanzar.");
                            }
                        }
                    } else {
                        System.out.println("Jugador 2 no puede avanzar. Debe llegar máximo a 50");
                    }
                }
                playerTurn = 1;
            }

            printGameStatus(player1, player2);
        } while (player1 < 50 && player2 < 50);

        if (player1 > player2) {
            System.out.println("Ganó el jugador 1");
        } else {
            System.out.println("Ganó el jugador 2");
        }
    }

    public static int diceRoll(int sides) {
        double randomNumber = Math.random() * sides;
        randomNumber = randomNumber + 1;
        return (int) randomNumber;
    }

    public static int monopolyRoll() {
        int roll1 = diceRoll(6);
        int roll2 = diceRoll(6);
        System.out.println("Dado 1:" + roll1);
        System.out.println("Dado 2:" + roll2);
        int total = roll1 + roll2;
        //An extra variable is added to keep track of how many rolls //have been made.
        int rollsSoFar = 1;
        while (roll1 == roll2) {
            System.out.println("¡¡Dobles!! Lanza de nuevo... (presiona enter)");
            Scanner s = new Scanner(System.in);
            s.nextLine();
            //Here, we return -1 if doubles have been rolled too //many times in a row.
            if (rollsSoFar >= 3) {
                System.out.println("¡¡oh noo triple dobles!! regresas al inicio (presiona enter)");
                return -1;
            }
            roll1 = diceRoll(6);
            roll2 = diceRoll(6);
            System.out.println("Dado 1:" + roll1);
            System.out.println("Dado 2:" + roll2);
            total = total + roll1 + roll2;
            rollsSoFar = rollsSoFar + 1;
        }
        return total;
    }

    public static void printGameStatus(int player1, int player2) {
        System.out.println("Estado del juego:");
        for (int i = 0; i <= 50; i++) {
            System.out.print(i);
            if (player1 == i) {
                System.out.print(" o");
            }

            if (player2 == i) {
                System.out.print(" x");
            }
            System.out.println("");
        }
    }
}
