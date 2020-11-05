package project.tic_tac_toe.first;

import java.util.Scanner;

public class Tic_Tac_Toe implements Simulatable,Winnable, Playable{

    private int mathNum;

    Player player_one;
    Player player_two;
    boolean onePlayed;


    @Override
    public void initialize() {

        onePlayed = false;

        Scanner sc = new Scanner(System.in);

        System.out.println("목표 승수를 입력하세요");
        mathNum = Integer.parseInt(sc.nextLine());


        System.out.println("player1이 Human인지 AI인지 입력하세요(Human/AI)");

        if(sc.nextLine().equals( "Human")){
            player_one = new HumanPlayer();
            player_two = new AIPlayer();
        } else{
            player_one = new HumanPlayer();
            player_two = new AIPlayer();
        }

    }

    public Player whosTurn(){
        if(onePlayed==false){
            return player_one;
        } else{
            return player_two;
        }
    }

    @Override
    public void Play(Player player) {

    }

    @Override
    public void isFinished() {

    }

    @Override
    public void reset() {

    }

    @Override
    public Player getWinner() {
        return null;
    }
}
