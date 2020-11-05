package project.tic_tac_toe.third;

public class Board implements Initializable {

    enum winStatus{
        NOT_FINISHED, PLAYER_ONE, PLAYER_TWO, TIE;
    }

    final char[][] board;

    public final static char BLANK = '-';
    public final static char MARKER_ONE = 'o';
    public final static char MARKER_TWO = 'x';


    public Board(int size) {
        this.board = new char[size][size];
        initialize();
    }

    @Override
    public void initialize() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = BLANK;
            }
        }
    }

    public boolean isPossible(Point move) {
        if (move.getX() < 0 || move.getX() >= board.length) {
            return false;
        } else if (move.getY() < 0 || move.getY() >= board.length) {
            return false;
        } else  return board[move.getX()][move.getY()] == BLANK;
    }

    public void setMark(Point move, char mark) {
        if (isPossible(move)) {
            board[move.getX()][move.getY()] = mark;
        }
    }

    public void showBoard() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf("%c ", board[i][j]);
            }
            System.out.println("");
        }
    }

    public winStatus isFinished(){

        if(isMarkWin(Board.MARKER_ONE)){
            return winStatus.PLAYER_ONE;
        } else if(isMarkWin(Board.MARKER_TWO)){
            return winStatus.PLAYER_TWO;
        } else if(isBoardFull()){
            return winStatus.TIE;
        } else{
            return winStatus.NOT_FINISHED;
        }
    }

    private boolean isMarkWin(char mark){
        int sum =0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sum += board[i][j] == mark ? 1 : 0;
                }
                if (sum == board.length) {
                    return true;
                }
            sum =0;
        }

        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                sum += board[j][i] == mark ? 1 : 0;
                if (sum == board.length){
                    return true;
                }
            }
            sum =0;
        }

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < board.length; i++) {
            sum1 += board[i][i] == mark ? 1 : 0;
            sum2 += board[i][board.length-i-1] == mark ? 1 : 0;
        }
        return sum1 == board.length || sum2 == board.length;
    }

    private boolean isBoardFull() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
               if(board[i][j] == BLANK){
                   return false;
               }
               }
            }
        return true;
    }
}


