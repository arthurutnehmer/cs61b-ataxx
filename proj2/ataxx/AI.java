/* Skeleton code copyright (C) 2008, 2022 Paul N. Hilfinger and the
 * Regents of the University of California.  Do not distribute this or any
 * derivative work without permission. */

package ataxx;

import net.sf.saxon.expr.Component;
import net.sf.saxon.ma.arrays.ArrayFunctionSet;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static ataxx.PieceColor.*;

/** A Player that computes its own moves.
 *  @author Arthur Utnehmer on 4/1/2022
 */
class AI extends Player {

    /** Maximum minimax search depth before going to static evaluation. */
    private static final int MAX_DEPTH = 4;
    /** A position magnitude indicating a win (for red if positive, blue
     *  if negative). */
    private static final int WINNING_VALUE = Integer.MAX_VALUE - 20;
    /** A magnitude greater than a normal value. */
    private static final int INFTY = Integer.MAX_VALUE;

    /** A new AI for GAME that will play MYCOLOR. SEED is used to initialize
     *  a random-number generator for use in move computations.  Identical
     *  seeds produce identical behaviour. */
    AI(Game game, PieceColor myColor, long seed) {
        super(game, myColor);
        generatePossibleMoves();
        _random = new Random(seed);
    }

    @Override
    boolean isAuto() {
        return true;
    }

    @Override
    String getMove() {
        if (!getBoard().canMove(myColor())) {
            game().reportMove(Move.pass(), myColor());
            return "-";
        }
        Main.startTiming();
        Move move = findMove();
        Main.endTiming();
        game().reportMove(move, myColor());
        return move.toString();
    }

    /** Return a move for me from the current position, assuming there
     *  is a move. */
    private Move findMove() {
        Board b = new Board(getBoard());
        _lastFoundMove = null;
        if (myColor() == RED) {
            minMax(b, MAX_DEPTH, true, 1, -INFTY, INFTY);
        } else {
            minMax(b, MAX_DEPTH, true, -1, -INFTY, INFTY);
        }
        return _lastFoundMove;
    }

    /** The move found by the last call to the findMove method
     *  above. */
    private Move _lastFoundMove;

    /** Find a move from position BOARD and return its value, recording
     *  the move found in _foundMove iff SAVEMOVE. The move
     *  should have maximal value or have value > BETA if SENSE==1,
     *  and minimal value or value < ALPHA if SENSE==-1. Searches up to
     *  DEPTH levels.  Searching at level 0 simply returns a static estimate
     *  of the board value and does not set _foundMove. If the game is over
     *  on BOARD, does not set _foundMove. */
    private int minMax(Board board, int depth, boolean saveMove, int sense,
                       int alpha, int beta) {
        /* We use WINNING_VALUE + depth as the winning value so as to favor
         * wins that happen sooner rather than later (depth is larger the
         * fewer moves have been made. */
        if (depth == 0 || board.getWinner() != null) {
            return staticScore(board, WINNING_VALUE + depth);
        }
        Move best;
        best = null;
        int bestScore;
        bestScore = alpha;

        ArrayList<Board> currentBoards = new ArrayList<Board>();
        ArrayList<Board> hypotheticalBoards;
        Board tmpBoard = new Board(board);
        tmpBoard.clearMoveHistory();
        currentBoards.add(tmpBoard);
        HashMap<String, String> evaluatedPositions
                = new HashMap<String, String>();
        HashMap<String, ArrayList<Move>> listOfUnusedPossibleMoves =
                new HashMap<String, ArrayList<Move>>();
        for (String key: _listOfPossibleMoves.keySet()) {
            listOfUnusedPossibleMoves.put(key,
                    _listOfPossibleMoves.get(key));
        }
        for (int x = 0; x < 8; x++) {
            evaluatedPositions.clear();
            hypotheticalBoards = new ArrayList<Board>();
            for (int y = 0; y < currentBoards.size(); y++) {
                executeMoves(currentBoards.get(y),
                        hypotheticalBoards,
                        evaluatedPositions,
                        listOfUnusedPossibleMoves);
            }
            if (hypotheticalBoards.size() < currentBoards.size()) {
                break;
            }
            currentBoards = hypotheticalBoards;
            for (String key : evaluatedPositions.keySet()) {
                listOfUnusedPossibleMoves.remove(key);
            }
            if (listOfUnusedPossibleMoves.isEmpty()) {
                break;
            }
        }
        int score = bestScore;
        for (Board boards : currentBoards) {
            score = staticScore(boards, WINNING_VALUE);
            if (score > bestScore) {
                bestScore = score;
                best = boards.allMoves().get(0);
                saveMove = true;
            }
        }
        if (saveMove) {
            _lastFoundMove = best;
        }
        return bestScore;
    }

    /** Return a heuristic value for BOARD.  This value is +- WINNINGVALUE in
     *  won positions, and 0 for ties. */
    private int staticScore(Board board, int winningValue) {
        PieceColor winner = board.getWinner();
        if (winner != null) {
            return switch (winner) {
            case RED -> winningValue;
            case BLUE -> -winningValue;
            default -> 0;
            };
        }
        return board.numPieces(myColor())
                - board.numPieces(myColor().opposite());
    }


    /** This method will take a board, find out whose move it is, and
     * then execute all moves on a board for the pieces that can move.
     * @return A list of boards that have had the player who is supposed to
     * move.
     * @param boardToEvaluate Board to simulate moves on.
     * @param boardWithMoves Where to store the boards that moves are made on.
     * @param evaluatedPositions Where we store positions that are evaluated.
     * @param listOfUnusedPossibleMoves Unused positions.
     * */
    public ArrayList<Board> executeMoves(Board boardToEvaluate,
                                         ArrayList<Board> boardWithMoves,
                                         HashMap<String,
                                                 String> evaluatedPositions,
                                         HashMap<String, ArrayList<Move>>
                                                 listOfUnusedPossibleMoves) {
        for (int x = 'a'; x < 'h'; x++) {
            for (int y = '1'; y < '8'; y++) {
                if (boardToEvaluate.get((char) x, (char) y)
                        == boardToEvaluate.whoseMove()) {
                    String key = (char) x + "" + (char) y;
                    evaluatedPositions.put(key, key);
                    if (listOfUnusedPossibleMoves.get(key) != null) {
                        for (Move hypotheticalMove
                                : listOfUnusedPossibleMoves.get(key)) {
                            if (boardToEvaluate.legalMove(hypotheticalMove)) {
                                Board tmpBoard = new Board(boardToEvaluate);
                                tmpBoard.makeMove(hypotheticalMove);
                                boardWithMoves.add(tmpBoard);
                            }
                        }
                    }
                }
            }
        }
        return boardWithMoves;
    }

    /** Generate legal moves from a spot. */
    public void generatePossibleMoves() {
        _listOfPossibleMoves = new HashMap<String, ArrayList<Move>>();
        Move move;
        for (int x = 'a'; x < 'h'; x++) {
            for (int y = '1'; y < '8'; y++) {
                for (int z = 'a'; z < 'h'; z++) {
                    for (int l = '1'; l < '8'; l++) {
                        move = Move.move((char) x,
                                (char) y, (char) z, (char) l);
                        if (move != null) {
                            String key = (char) x + "" + (char) y;
                            ArrayList<Move> listOfMoves;
                            if (_listOfPossibleMoves.get(key) == null) {
                                listOfMoves = new ArrayList<Move>();
                                listOfMoves.add(move);
                                _listOfPossibleMoves.put(key, listOfMoves);
                            } else {
                                listOfMoves = _listOfPossibleMoves.get(key);
                                listOfMoves.add(move);
                            }
                        }
                    }
                }
            }
        }
    }

    /** Pseudo-random number generator for move computation. */
    private HashMap<String, ArrayList<Move>> _listOfPossibleMoves;

    /** Hash table containing legal moves from a spot. */
    private Random _random = new Random();
}
