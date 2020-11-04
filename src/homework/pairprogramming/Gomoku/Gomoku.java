package homework.pairprogramming.Gomoku;

import java.util.Scanner;

public class Gomoku implements Simulatable, Winnable, Playable, Printable {
    int count = 0;
    Position [] posA = new Position[150];
    Position [] posB = new Position[150];
    int [][] gomokuTable = new int[15][15];
    int nA, nB;
    boolean isExist = false;
    Player playerA;
    Player playerB;
    boolean finished = false;
    boolean isQuited = false;


    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player1 이름 :");
        playerA = new Player(scanner.nextLine());
        System.out.println("Player2 이름 :");
        playerB = new Player(scanner.nextLine());

        while(true){
        initialize();
            while (true) {
                inputStone(whosTurn());
                isFinished();
             if(finished|isQuited){
                 break;
               }
               }
        if(isQuited){
            break;
        } else{
            reset();
        }
        }
        System.out.println("승자 : " + getWinner().getName());
        getScore();
        }


    public Player whosTurn(){

        if (count % 2 == 0) {
            return playerA;
        } else {
            return playerB;
        }

    }

    public void inputStone(Player player) {
                 while (true) {
                    player.getKeyboardInput(); // pos instance
                    quitGame(player.pos);
                    if (isQuited) {
                        break;
                    }
                    isExist = false;
                    checkExist(player.pos);
                    if (isExist) {
                        System.out.println("이미 돌이 있습니다.");
                    } else {
                        System.out.println("" +
                                "을 두었습니다.");
                        break;
                    }
                }
                 if(!isQuited) {
                     play(player, player.pos); // player pos
                     printStatus();
                 }
            }



    public void quitGame(Position pos){
        if(pos.x == 999 || pos.y == 999){
            isQuited = true;
        }
    }


    public void checkExist(Position pos){

                for (int i = 0; i < posA.length; i++) {
                    if (posA[i].x == pos.x && posA[i].y == pos.y) {
                        isExist = true;
                    } else {
                        continue;
                    }
                }
                for (int i = 0; i < posB.length; i++) {
                    if (posB[i].x == pos.x && posB[i].y == pos.y) {
                        isExist = true;
                    } else {
                        continue;
                    }
                }

        }


    private void check3_3(Position pos) {
//        if(pos==playerA.pos)

    }

    @Override
    public void play(Player player, Position pos) {
        if(player == playerA) {
            posA[nA] = pos;
            gomokuTable[pos.x][pos.y] = 1;
            nA++;
        } else {
            posB[nB] = pos;
            gomokuTable[pos.x][pos.y] = 10;
            nB++;
        }
        count++;
        }


    @Override
    public void printStatus() {

        for(int i = 0 ; i < 15; i++){

            for (int j = 0 ; j < 15; j++){

                if(gomokuTable[i][j]==0){
                    System.out.print("-   ");
                } else if(gomokuTable[i][j]==1){
                    System.out.print("x   ");
                } else if(gomokuTable[i][j]==10){
                    System.out.print("o   ");
                }

            }
            System.out.println("");
        }


    }

    @Override
    public void initialize() {


        for(int i = 0 ; i < posA.length; i++){
           posA[i] = new Position(0,0);
           posB[i] = new Position(0,0);
        }

        for(int i=0; i < 15; i++){
            for(int j=0; j<15; j++){
                gomokuTable[i][j] =0;
            }
        }

        finished = false;
        nA =0;
        nB =0;

    }

    @Override
    public void isFinished() {
        int score = 0;

        //가로 5개
        for(int i=0 ; i < 15; i++){
            for(int j=0; j<11; j++){
                for (int k=0; k<5; k++){
                    score += gomokuTable[i][j+k];
                }
                if(score==5){
                    System.out.println("흑이 이겼습니다.");
                    playerA.numCount();
                    finished = true;
                } else if(score==50){
                    System.out.println("백이 이겼습니다.");
                    playerB.numCount();
                    finished = true;
                }
                score=0;
            }
        }

//        세로 5개
        for(int i=0 ; i < 11; i++){
            for(int j=0; j<15; j++){
                for (int k=0; k<5; k++){
                    score += gomokuTable[i+k][j];
                }
                if(score==5){
                    System.out.println("흑이 이겼습니다.");
                    playerA.numCount();
                    finished = true;
                } else if(score==50){
                    System.out.println("백이 이겼습니다.");
                    playerB.numCount();
                    finished = true;
                }
                score=0;
            }
        }
//        왼대각
        for(int i=0 ; i < 11; i++){
            for(int j=0; j <11; j++){
                for (int k=0; k<5; k++){
                    score += gomokuTable[i+k][j+k];
                }
                if(score==5){
                    System.out.println("흑이 이겼습니다.");
                    playerA.numCount();
                    finished = true;
                } else if(score==50){
                    System.out.println("백이 이겼습니다.");
                    playerB.numCount();
                    finished = true;
                }
                score=0;
            }
        }


              //  오른대각
        for(int i=4 ; i < 15; i++){
            for(int j=0; j <11; j++){
                for (int k=0; k<5; k++){
                    score += gomokuTable[i-k][j+k];
                }
                if(score==5){
                    System.out.println("흑이 이겼습니다.");
                    playerA.numCount();
                    finished = true;
                } else if(score==50){
                    System.out.println("백이 이겼습니다.");
                    playerB.numCount();
                    finished = true;
                }
                score=0;
            }
        }
    }

    @Override
    public void reset() {
        getScore();
        System.out.println("다음 게임을 시작합니다");
    }

    public void getScore(){
        System.out.println("현재스코어" + playerA.getName() + playerA.getNumWin() + " : " + playerB.getName() + playerB.getNumWin());
    }



    @Override
    public Player getWinner() {
        if (playerA.getNumWin() > playerB.getNumWin()){
            return playerA;
        } else {
            return playerB;
        }
    }


}
