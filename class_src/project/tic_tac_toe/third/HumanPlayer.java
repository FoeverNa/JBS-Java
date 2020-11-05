package project.tic_tac_toe.third;

import java.util.Scanner;

public class HumanPlayer extends Player {


    @Override
     public void play() {
        Scanner scanner = new Scanner(System.in);
        int x,y;
        while(true){
            System.out.printf("Please input %s's move (x,y)",name);

            x = scanner.nextInt()-1;
            y = scanner.nextInt()-1;

            Point move = new Point(x,y);
            if(ticTacToe.observe().isPossible(move)){
                ticTacToe.play(move, this);
                break;
        }
        }
    }

}
