package com.test.assignment;

import java.util.Random;

public class ForestGame {

    public static void main(String[] args) {
        char[][] forest = generateForest(5, 5);
        System.out.println("-------- Display Forest ----------------\n");
        displayForest(forest);
        movePlayer(forest, 'D');
        System.out.println("-------- Display Forest Different ----------------\n");
        displayForest(forest);
    }

    public static char[][] generateForest(int rows, int cols) {
        char[][] forest = new char[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (random.nextDouble() < 0.7) {
                    forest[i][j] = '.';
                } else {
                    forest[i][j] = 'T';
                }
            }
        }

        int playerRow, playerCol;
        do {
            playerRow = random.nextInt(rows);
            playerCol = random.nextInt(cols);
        } while (forest[playerRow][playerCol] != '.');

        forest[playerRow][playerCol] = 'P';

        return forest;
    }

    public static void displayForest(char[][] forest) {
        for (char[] row : forest) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void movePlayer(char[][] forest, char direction) {
        int[] playerPos = findPlayer(forest);
        int playerRow = playerPos[0];
        int playerCol = playerPos[1];

        int newRow = playerRow;
        int newCol = playerCol;

        switch (direction) {
            case 'W':
                newRow--;
                break;
            case 'S':
                newRow++;
                break;
            case 'A':
                newCol--;
                break;
            case 'D':
                newCol++;
                break;
            default:
                System.out.println("Invalid direction! Use W, S, A, or D.");
                return;
        }

        if (isValidMove(forest, newRow, newCol)) {
            forest[playerRow][playerCol] = '.';
            forest[newRow][newCol] = 'P';
        } else {
            System.out.println("Invalid move! Can't move there.");
        }
    }

    private static int[] findPlayer(char[][] forest) {
        int[] pos = new int[2];
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                if (forest[i][j] == 'P') {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    private static boolean isValidMove(char[][] forest, int newRow, int newCol) {
        return newRow >= 0 && newRow < forest.length && newCol >= 0 && newCol < forest[0].length && forest[newRow][newCol] != 'T';
    }
}
