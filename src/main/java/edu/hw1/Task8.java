package edu.hw1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task8 {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final int BOARD_SIZE = 8;
    private static final int HORSE_TILE = 1;
    private static final int EMPTY_TILE = 0;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        Objects.requireNonNull(board);
        if (board.length != BOARD_SIZE) {
            return false;
        }
        for (int[] line : board) {
            if (line.length != BOARD_SIZE) {
                return false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == EMPTY_TILE) {
                    continue;
                }

                if (!isHorseSafeHorizontal(board, i, j)
                    || !isHorseSafeVertical(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isHorseSafeHorizontal(int[][] board, int i, int j) {
        return (j - 2 < 0 || i - 1 < 0 || board[i - 1][j - 2] != HORSE_TILE)
            && (j - 2 < 0 || i + 1 >= BOARD_SIZE || board[i + 1][j - 2] != HORSE_TILE)
            && (j + 2 >= BOARD_SIZE || i - 1 < 0 || board[i - 1][j + 2] != HORSE_TILE)
            && (j + 2 >= BOARD_SIZE || i + 1 >= BOARD_SIZE || board[i + 1][j + 2] != HORSE_TILE);
    }

    private static boolean isHorseSafeVertical(int[][] board, int i, int j) {
        return (i - 2 < 0 || j - 1 < 0 || board[i - 2][j - 1] != HORSE_TILE)
            && (i - 2 < 0 || j + 1 >= BOARD_SIZE || board[i - 2][j + 1] != HORSE_TILE)
            && (i + 2 >= BOARD_SIZE || j - 1 < 0 || board[i + 2][j - 1] != HORSE_TILE)
            && (i + 2 >= BOARD_SIZE || j + 1 >= BOARD_SIZE || board[i + 2][j + 1] != HORSE_TILE);
    }
}
