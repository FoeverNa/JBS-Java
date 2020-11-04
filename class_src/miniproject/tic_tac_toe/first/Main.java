package miniproject.tic_tac_toe.first;

public class Main {

    public static void main(String[] args) {

        Tic_Tac_Toe tic = new Tic_Tac_Toe();

        tic.initialize();

        Player player = tic.whosTurn();

        System.out.println();

        tic.Play(player);



    }
}
