/* Skeleton code copyright (C) 2008, 2022 Paul N. Hilfinger and the
 * Regents of the University of California.  Do not distribute this or any
 * derivative work without permission. */

package ataxx;

import org.junit.Test;

import static ataxx.PieceColor.*;
import static org.junit.Assert.*;

/** Tests of the Board class.
 *  @author P. N. Hilfinger
 */
public class AiTest {

    private static void makeMoves(Board b, String[] moves) {
        for (String s : moves) {
            b.makeMove(s.charAt(0), s.charAt(1),
                    s.charAt(3), s.charAt(4));
        }
    }

    private static final char[] COLS = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
    private static final char[] ROWS = {'1', '2', '3', '4', '5', '6', '7'};

    private static void checkBoard(Board b, PieceColor[][] expectedColors) {
        assertEquals(Board.SIDE, expectedColors.length);
        assertEquals(Board.SIDE, expectedColors[0].length);
        for (int r = 0; r < expectedColors.length; r++) {
            for (int c = 0; c < expectedColors[0].length; c++) {
                assertEquals("incorrect color at "
                                + COLS[c] + ROWS[ROWS.length - 1 - r],
                        expectedColors[r][c],
                        b.get(COLS[c], ROWS[ROWS.length - 1 - r]));
            }
        }
    }

    @Test
    public void testBasic() {
        GUI display = new GUI("Ataxx!");
        Game gameTest = new Game(display, display, display, false);
        System.out.println(gameTest.getBoard());


        int x = 0;
        while (gameTest.gameInProgress()) {
            System.out.println("-----------------------");
            System.out.println(GAME1[x]);
            gameTest.makeMove(GAME1[x]);
            System.out.println(gameTest.getBoard());

            System.out.println(gameTest.getBoard().getWinner());
            System.out.println(gameTest.getBoard().redPieces());
            System.out.println(gameTest.getBoard().bluePieces());
            System.out.println(gameTest.getBoard().canMove(BLUE));
            System.out.println(gameTest.getBoard().canMove(RED));
            System.out.println("-----------------------");
            x = x + 1;
        }

    }

    @Test
    public void testBasic2() {
        GUI display = new GUI("Ataxx!");
        Game gameTest = new Game(display, display, display, false);
        System.out.println(gameTest.getBoard());


        int x = 0;
        while (gameTest.gameInProgress()) {
            System.out.println("-----------------------");
            System.out.println(GAME2[x]);
            gameTest.makeMove(GAME2[x]);
            System.out.println(gameTest.getBoard());

            System.out.println(gameTest.getBoard().redPieces());
            System.out.println(gameTest.getBoard().bluePieces());
            System.out.println("-----------------------");
            System.out.println(gameTest.getBoard().numJumps());
            x = x + 1;
        }

        System.out.println(gameTest.getBoard().getWinner());

    }

    @Test
    public void testAivsAiNew() {
        GUI display = new GUI("Ataxx!");
        Game gameTest = new Game(display, display, display, false);
        Board edit = gameTest.getBoard();
        edit.setBlock("b2");
        edit.setBlock("c3");
        edit.setBlock("c4");
        System.out.println(gameTest.getBoard());

        AI blue = new AI(gameTest, BLUE, 0);
        AI red = new AI(gameTest, RED, 0);


        while (gameTest.gameInProgress()) {
            System.out.println("-----------------------");
            String test1 = red.getMove();
            System.out.println(test1);
            gameTest.makeMove(test1);
            System.out.println(gameTest.getBoard());
            System.out.println("-----------------------");
            String test = blue.getMove();
            System.out.println(test);
            gameTest.makeMove(test);
            System.out.println(gameTest.getBoard());
            System.out.println("------------------------");


        }
        System.out.println(gameTest.getBoard().numMoves());
        System.out.println(gameTest.getBoard().getWinner());
    }



    @Test
    public void testAivsAi() {
        GUI display = new GUI("Ataxx!");
        Game gameTest = new Game(display, display, display, false);
        System.out.println(gameTest.getBoard());
        AI blue = new AI(gameTest, BLUE, 0);
        AI red = new AI(gameTest, RED, 0);


        while (gameTest.gameInProgress()) {
            System.out.println("-----------------------");
            String test1 = red.getMove();
            System.out.println(test1);
            gameTest.makeMove(test1);
            System.out.println(gameTest.getBoard());
            System.out.println("-----------------------");
            String test = blue.getMove();
            System.out.println(test);
            gameTest.makeMove(test);
            System.out.println(gameTest.getBoard());

            System.out.println("------------------------");

        }
        System.out.println(gameTest.getBoard().numMoves());
        System.out.println(gameTest.getBoard().getWinner());
    }

    @Test
    public void testAivsAiBlock() {
        GUI display = new GUI("Ataxx!");
        Game gameTest = new Game(display, display, display, false);
        System.out.println(gameTest.getBoard());
        AI blue = new AI(gameTest, BLUE, 0);
        AI red = new AI(gameTest, RED, 0);

        gameTest.block("a2");
        gameTest.block("a3");
        gameTest.block("a4");
        gameTest.block("b1");
        gameTest.block("b2");
        gameTest.block("b3");
        gameTest.block("b4");
        gameTest.block("c1");
        gameTest.block("c2");
        gameTest.block("c3");
        gameTest.block("c4");

        System.out.println(gameTest.getBoard());


        while (gameTest.gameInProgress()) {
            System.out.println("-----------------------");
            String test1 = red.getMove();
            System.out.println(test1);
            gameTest.makeMove(test1);
            System.out.println(gameTest.getBoard());
            System.out.println("-----------------------");
            String test = blue.getMove();
            System.out.println(test);
            gameTest.makeMove(test);
            System.out.println(gameTest.getBoard());

            System.out.println("------------------------");

        }
        System.out.println(gameTest.getBoard().numMoves());
        System.out.println(gameTest.getBoard().getWinner());
    }


    @Test
    public void testAivsAiBlock2() {
        GUI display = new GUI("Ataxx!");
        Game gameTest = new Game(display, display, display, false);
        AI red = new AI(gameTest, BLUE, 0);

        gameTest.block("c1");
        gameTest.block("d1");
        gameTest.block("a2");
        gameTest.block("b2");
        gameTest.block("c2");
        gameTest.block("d2");
        gameTest.block("a3");
        gameTest.block("b3");
        gameTest.block("c3");
        gameTest.block("d3");

        System.out.println(gameTest.getBoard());


        while (gameTest.gameInProgress()) {
            System.out.println(gameTest.getBoard().totalOpen());
            gameTest.reportMove(Move.move('a', '7', 'b', '7'), RED);
            System.out.println("------------------------");
            String test1 = red.getMove();
            System.out.println(test1);
            gameTest.makeMove(test1);
            System.out.println(gameTest.getBoard());
            System.out.println("-----------------------");
            gameTest.reportMove(Move.move('g', '1', 'f', '1'), RED);
            System.out.println("------------------------");
        }
        System.out.println(gameTest.getBoard().numMoves());
        System.out.println(gameTest.getBoard().getWinner());
    }

    private static final String[] GAME1 = {
        "a7-a6", "a1-b1", "a6-b7", "b1-c1", "a6-a5", "a1-a2",
        "a5-c7", "c1-d1", "c7-d6", "d1-e1", "d6-e7", "e1-f1",
        "e7-f7", "c1-d2", "c7-d7", "a1-b2", "a6-a5", "d1-e2",
        "a5-a4", "d1-c2", "a4-c3", "e2-d3", "a5-b3", "a1-a3",
        "c3-a1", "d3-c3", "a6-a4", "c3-b4", "b7-a5", "d3-b5",
        "d6-c4", "d2-d3", "b5-d4", "d2-e3", "c3-b5", "c2-c3",
        "a4-c5", "e3-d5", "a5-a4", "c5-b6", "e7-c6", "d4-f6",
        "b6-d4", "a5-b6", "d3-f2", "c2-d3", "d5-b7", "a5-a6",
        "f2-e3", "f6-e4", "e2-f3", "d4-d5", "c7-e5", "f7-d6",
        "e4-e6", "d3-e4", "d7-c7", "g7-e7", "f1-g2", "e5-g3",
        "d2-f4", "c1-d2", "e4-e5", "d7-f5", "c7-d7", "f4-g4",
        "e7-f6", "f4-g5", "e7-g6", "c5-e7", "b7-c5", "a6-b7",
        "d5-f7"
    };

    private static final String[] GAME2 = {
        "a7-a6", "a1-b1", "a6-b6", "a1-a2", "a6-c7", "b1-c1",
        "c7-d6", "c1-d1", "c7-b7", "d1-f1", "d6-f6", "c1-d1",
        "c7-d7", "d1-e1", "f6-e7", "e1-e2", "a7-a6", "a2-a3",
        "f6-e6", "a3-a4", "b6-c6", "a1-b2", "c6-b5", "a3-a5",
        "c6-b4", "b6-c6", "b4-c2", "d7-f7", "d1-f2", "a2-b3",
        "a5-a3", "c2-e3", "b3-a5", "e3-c2", "e1-e3", "b2-b3",
        "a5-b4", "c1-e1", "e3-c1", "c6-c5", "g1-g2", "e2-f3",
        "g1-e3", "g2-g1", "f3-g3", "b4-d2", "b3-d1", "a2-b4",
        "b1-b3", "e3-d3", "b2-b1", "c5-c3", "a1-a2", "e3-f4",
        "g2-e4", "f1-g2", "e4-g6", "c2-e4", "b2-c2", "f4-g5",
        "d3-f5", "b4-d3", "a3-b4", "f3-g4", "c3-e5", "c2-c3",
        "f4-d6", "b6-d7", "e4-f4", "b7-b6", "e3-c5", "d2-e3",
        "b5-c4", "e6-d4", "b6-d5", "c7-e6", "d4-b6", "b2-d4",
        "d1-b2", "d2-d1"
    };


    private static final String[] UNDO2MOVES = {
        "a7-a6", "a1-b1",
        "g1-f1", "g7-f6",
        "a6-a5", "a1-a2",
        "g1-g2", "b1-b2",
        "a5-a3", "a1-b3",
        "a6-a4", "b2-b4",
        "a7-a5", "b3-b5",
        "g1-f2", "b5-c4",
        "f2-d3", "b4-c3",
        "f1-e1"
    };

    private static final PieceColor[][] GAME1RESULT = {
            {RED, RED, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };

    private static final PieceColor[][] INITIAL = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };

    private static final PieceColor[][] REDEXTEND1 = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };

    private static final PieceColor[][] BLUEEXTEND1 = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };

    private static final PieceColor[][] REDJUMP1 = {
            {EMPTY, EMPTY, RED, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };

    private static final PieceColor[][] BLUEJUMP1 = {
            {EMPTY, EMPTY, RED, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, BLUE, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };

    private static final PieceColor[][] BLOCKS1 = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {BLOCKED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLOCKED},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, BLOCKED, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLOCKED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLOCKED},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };

    private static final PieceColor[][] GAMEEXTENDS = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, RED, RED}
    };

    private static final PieceColor[][] GAMEJUMP1 = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {RED, RED, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, RED, RED}
    };

    private static final PieceColor[][] GAMEJUMP2 = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {EMPTY, BLUE, EMPTY, EMPTY, EMPTY, RED, RED}
    };

    private static final PieceColor[][] GAMEJUMP3 = {
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, BLUE, BLUE, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {EMPTY, BLUE, EMPTY, EMPTY, RED, RED, RED}
    };

    private static final PieceColor[][] GAMEJUMP3UNDO = {
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, BLUE, BLUE, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {EMPTY, BLUE, EMPTY, EMPTY, EMPTY, RED, RED}
    };

    private static final PieceColor[][] GAMEJUMP3ALT = {
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, BLUE, BLUE, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, RED, RED},
            {EMPTY, BLUE, EMPTY, EMPTY, EMPTY, RED, RED}
    };

    private static final PieceColor[][] GAMEJUMP3ALT2 = {
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, BLUE, BLUE, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, BLUE, EMPTY, EMPTY, RED, RED, RED}
    };

    private static final PieceColor[][] GAMEJUMP4 = {
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, BLUE, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, BLUE},
            {EMPTY, BLUE, EMPTY, EMPTY, BLUE, BLUE, BLUE}
    };

    private static final PieceColor[][] GAMEJUMP4ALT = {
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, BLUE, BLUE, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, BLUE, EMPTY, EMPTY, EMPTY, EMPTY},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, EMPTY},
            {EMPTY, BLUE, EMPTY, EMPTY, BLUE, BLUE, BLUE}
    };

    private static final PieceColor[][] JUMPSINITIAL = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };

    private static final PieceColor[][] JUMPS1 = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };

    private static final PieceColor[][] JUMPS2 = {
            {RED, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, BLUE},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED},
            {BLUE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, RED}
    };
}
