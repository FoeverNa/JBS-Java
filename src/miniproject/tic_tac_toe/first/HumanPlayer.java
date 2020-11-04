package miniproject.tic_tac_toe.first;

public class HumanPlayer implements Player, Inputable {

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String im = "Human";



    @Override
    public void getKeyboardInput() {

    }
}
