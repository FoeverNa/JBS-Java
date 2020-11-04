package homework.pairprogramming.Gomoku;

import java.util.Scanner;

public class Player implements Inputtable {
    public Position pos;
    private String name;
    private int numWin;
    
    public void numCount(){
        numWin++;
    }

    public String getName() {
        return name;
    }

    public int getNumWin() {
        return numWin;
    }

    public Player(String name) {
        this.name = name;
    }

    @Override
    public void getKeyboardInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("좌표 입력");

        int int_y, int_x;
        String input_x = scanner.next();
        String input_y = scanner.next();
        if(input_x.equals("q") | input_x.equals("Q") | input_y.equals("q") | input_y.equals("Q")){
            pos = new Position(999, 999);
        } else{
             int_x = Integer.parseInt(input_x);
             int_y = Integer.parseInt(input_y);
            pos = new Position(int_x, int_y);
        }

    }
}
