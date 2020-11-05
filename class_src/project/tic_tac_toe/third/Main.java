package project.tic_tac_toe.third;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Player player_One = new HumanPlayer();
        Player player_Two = new AiPlayer();

        player_One.setName("Jack");
        player_Two.setName("Jill");


        TicTacToe ticTacToe = new TicTacToe(player_One, player_Two);

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many wins to finish the game?:  ");
        int N = scanner.nextInt();

        while(player_One.getTotalWin() <N || player_Two.getTotalWin()<N){
            Player player = Math.random() > 0.5 ? player_One : player_Two;
            ticTacToe.initialize();

            System.out.println(" -- Current Score -- ");
            System.out.printf(" %s  %d : %d %s \n",player_One.name,player_One.getTotalWin()
                                                ,player_Two.getTotalWin(),player_Two.name);
            System.out.println("-----------------");

            while(ticTacToe.observe().isFinished()==Board.winStatus.NOT_FINISHED){
                System.out.println("");
                System.out.println(" -- Current Board-- ");
                ticTacToe.observe().showBoard();
                System.out.println("-----------------");
                player.play();
                player =  player == player_One ? player_Two : player_One;
            }
            System.out.println("");
            System.out.println(" -- Final Board-- ");
            ticTacToe.observe().showBoard();
            System.out.println("-----------------");

            switch(ticTacToe.observe().isFinished()){
                case PLAYER_ONE:
                    player_One.win();
                    System.out.printf("%s has won!\n",player_One.name);
                    break;
                case PLAYER_TWO:
                    player_Two.win();
                    System.out.printf("%s has won!\n",player_Two.name);
                    break;
                default:
                    System.out.println("The game ended tie!\n");
            }
            System.out.println("");
            System.out.println(" -- Final Score -- ");
            System.out.printf(" %s  %d : %d %s \n",player_One.name,player_One.getTotalWin()
                    ,player_Two.getTotalWin(),player_Two.name);
            System.out.println("-----------------");
        }

    }
}

