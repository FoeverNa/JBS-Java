package project.tic_tac_toe.third;

public class TicTacToe implements Initializable, Observable<Board>, Playable<Point> {

    public static final int BOARD_SIZE= 3;

    Board board = new Board(BOARD_SIZE);

    Player player_One, player_Two ;

    public TicTacToe(Player player_One, Player player_Two){
        setPlayer_One(player_One);
        setPlayerTwo(player_Two);

    }

    public void setPlayer_One(Player player_One) {
        this.player_One = player_One;
        player_One.setTicTacToe(this);
    }

    public void setPlayerTwo(Player playerTwo) {
        this.player_Two = playerTwo;
        playerTwo.setTicTacToe(this);
    }

    @Override
    public void initialize() {
        board.initialize();

    }

    @Override
    public Board observe() {
        return board;
    }

    @Override
    public boolean play(Point move, Player player) {
        if(!board.isPossible(move)){
            return false;
        } else{
            char mark = player == player_One ? Board.MARKER_ONE : Board.MARKER_TWO;
            board.setMark(move, mark );
            return true;
        }
    }
}

