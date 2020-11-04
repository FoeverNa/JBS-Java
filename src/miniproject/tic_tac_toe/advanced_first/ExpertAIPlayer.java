package miniproject.tic_tac_toe.advanced_first;

public class ExpertAIPlayer extends Player {
    static final int SCORE_WIN = 1;
    static final int SCORE_LOSE = -1;
    static final int SCORE_TIE = 0;

    @Override
    void play() {
        Point move = findBestMove();
        ticTacToe.play(move, this);

        System.out.printf("ExpertAI Player %s's input is: (%d %d)\n", name, move.getX() + 1, move.getY() + 1);

    }

    Point findBestMove() {
        Board board = ticTacToe.observe();
        int maxScore = Integer.MIN_VALUE;
        Point bestMove = new Point(0, 0);
        char marker = findMaker(true);


        for (int x = 0; x < TicTacToe.BOARD_SIZE; x++) {
            for (int y = 0; y < TicTacToe.BOARD_SIZE; y++) {
                Point move = new Point(x, y);
                if (!board.isPossible(move)) {
                    continue;
                }
                board.setMark(move, marker);
                int score = miniMax(board,false);
                board.setMark(move, Board.BLANK);

                if (score > maxScore) {
                    maxScore = score;
                    bestMove = move;
                }
            }
        }

        return bestMove;

    }

    char findMaker(boolean isMyturn) {
        boolean isPlayerOne = ticTacToe.playerOne.equals(this);

        if ((isMyturn && isPlayerOne) || (!isMyturn && !isPlayerOne)) {
            return Board.MARKER_ONE;
        } else {
            return Board.MARKER_TWO;
        }


    }

    int miniMax(Board board, boolean isMyturn) {

        int bestScore = isMyturn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        char mark = findMaker(isMyturn);

        switch (board.getWinnerStatus()) {
            case PLAYER_ONE:
                return ticTacToe.playerOne.equals(this)? SCORE_WIN : SCORE_LOSE;
            case PLAYER_TWO:
                return ticTacToe.playerTwo.equals(this)? SCORE_WIN : SCORE_LOSE;
            case TIE:
                return SCORE_TIE;
        }

        for (int x = 0; x < TicTacToe.BOARD_SIZE; x++) {
            for (int y = 0; y < TicTacToe.BOARD_SIZE; y++) {
                Point move = new Point(x, y);
                if (!board.isPossible(move)) {
                    continue;
                }
                board.setMark(move, mark);
                int score = miniMax(board,!isMyturn);
                board.setMark(move, Board.BLANK);

                if (isMyturn) {
                    if (score > bestScore) {
                        bestScore = score;
                    }
                } else {
                    if (score < bestScore)
                        bestScore = score;
                }
            }
        }
        return bestScore;
    }
}
