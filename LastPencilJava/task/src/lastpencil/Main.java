package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static final String Name1 = "John";
    static final String Name2 = "Jack";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfPencils = 0;

        System.out.println("How many pencils would you like to use:");
        while (true) {
            String input = scanner.nextLine();
            try {
                numOfPencils = Integer.parseInt(input);
                if (numOfPencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }

        System.out.println("Who will be the first (" + Name1 + ", " + Name2 + "):");
        String input;
        String player = Name1;
        String bot = Name2;
        while (true) {
            input = scanner.nextLine();
            if (input.equals(Name1) || input.equals(Name2)) {
                break;
            } else {
                System.out.println("Choose between '" + Name1 + "' and '" + Name2 + "'");
            }
        }

        boolean isPlayerTurn = true;
        if (input.equals(Name2)) {
            isPlayerTurn = !isPlayerTurn;
        }

        // Print initial pencils
        System.out.println("|".repeat(numOfPencils));

        while (numOfPencils > 0) {
            String currentPlayer = isPlayerTurn ? player : bot;
            System.out.println(currentPlayer + "'s turn!");

            int taken;
            if (isPlayerTurn) {
                while (true) {
                    input = scanner.nextLine();
                    try {
                        taken = Integer.parseInt(input);
                        if (taken < 1 || taken > 3) {
                            System.out.println("Possible values: '1', '2' or '3'");
                        } else if (taken > numOfPencils) {
                            System.out.println("Too many pencils were taken");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2' or '3'");
                    }
                }
            } else {
                Random random = new Random();
                taken = 0;
                if (numOfPencils >= 5 && (numOfPencils - 5) % 4 == 0) {
                    taken = random.nextInt(3) + 1;
                } else if (numOfPencils >= 4 && (numOfPencils - 4) % 4 == 0) {
                    taken = 3;
                } else if (numOfPencils >= 3 && (numOfPencils - 3) % 4 == 0) {
                    taken = 2;
                } else if (numOfPencils >= 2 && (numOfPencils - 2) % 4 == 0) {
                    taken = 1;
                } else if (numOfPencils == 1) {
                    taken = 1;
                }
                System.out.println(taken);
            }

            numOfPencils -= taken;
            System.out.println("|".repeat(numOfPencils));
            isPlayerTurn = !isPlayerTurn;
        }

        String winner = isPlayerTurn ? player : bot;
        System.out.println(winner + " won!");
        scanner.close();
    }
}