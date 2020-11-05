package project.tic_tac_toe.second;

public class Board implements Initializable {
    enum WinnerStatus {
        NOt_FINISHED, TIE, PLAYER_ONE, PLAYER_TWO;
    }



    static final char BLANK = '-';
    static final char MARKER_ONE = 'o';
    static final char MARKER_TWO = 'x';

    final char[][] board;

    public Board(int size) {
        this.board = new char[size][size];
        initialize();
    }

    @Override
    public void initialize() {

        for(int i =0 ; i<board.length; i++){
            for(int j =0; j<board[0].length; j++){
                board[i][j] = BLANK;
            }
        }

    }

    public boolean isPossible(Point move){
        if(move.getX() < 0 || move.getX() >= board.length ){
            return false;
        } else if(move.getY() < 0 || move.getY() >= board.length){
            return false;
        } else {
            return board[move.getY()][move.getX()] == BLANK;
        }
    }

    public boolean setMark(Point move, char mark){
        if (!isPossible(move)){
            return false;
        } else{
            board[move.getY()][move.getX()] = mark;
            return true;
        }
    }

    public void showBoard(){
        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf("%s ",chars[j]);
            }
            System.out.println("");
        }
    }


    private boolean isMarkWin(char mark) {

        for (char[] chars : board) {
            int sum = 0;
            for (int j = 0; j < board[0].length; j++) {
                sum += chars[j] == mark ? 1 : 0;
            }
            if (sum == board.length) {
                return true;
            }
        }

        for (int i =0; i < board.length; i++) {
            int sum = 0;
            for (int j = 0; j < board[0].length; j++) {
                sum += board[j][i] == mark ? 1 : 0;
            }
            if (sum == board.length) {
                return true;
            }
        }


        int sum1 =0;
        int sum2 =0;
        for (int i =0; i < board.length; i++) {
            sum1 += board[i][i] == mark ? 1 : 0;
            sum2 += board[i][board.length-i-1] == mark ? 1 : 0;
        }
        return sum1 == board.length || sum2 == board.length;

    }

    public WinnerStatus getWinnerStatus(){
        if (isMarkWin(MARKER_ONE)) {
            return WinnerStatus.PLAYER_ONE;
        } else if (isMarkWin(MARKER_TWO)){
            return WinnerStatus.PLAYER_TWO;
        } else if (isMarkFull()){
            return WinnerStatus.TIE;
        } else {
            return WinnerStatus.NOt_FINISHED;
        }

    }



    private boolean isMarkFull(){
        for ( int i = 0 ; i < board.length; i++){
            for(int j =0; j< board[0].length; j++){
                if(board[i][j]==BLANK){
                    return false;
                }
            }
        }
        return true;
    }

}
