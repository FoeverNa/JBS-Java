package miniproject.tic_tac_toe.third;

public abstract class Player {

    private int totalWin;
    protected String name;
    protected TicTacToe ticTacToe;


    public TicTacToe getTicTacToe() {
        return ticTacToe;
    }

    public void setTicTacToe(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    public void win(){
        totalWin++;
    }

    public int getTotalWin() {
        return totalWin;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void play();
}

