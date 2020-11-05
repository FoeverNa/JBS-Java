package project.tic_tac_toe.third;

public class AiPlayer extends Player {

    @Override
    public void play() {
        int x,y;
        while(true){

        x = (int)(Math.random() * TicTacToe.BOARD_SIZE);
        y = (int)(Math.random() * TicTacToe.BOARD_SIZE);

        Point move = new Point(x,y);

        if(ticTacToe.observe().isPossible(move)){
            ticTacToe.play(move, this);
            break;
        }
        }
        System.out.printf("AI Player %s Moved to %d,%d\n",name,x+1,y+1);

    }
}

