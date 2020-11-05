package project.tic_tac_toe.third;

public interface Playable<T> {
    boolean play(T move, Player player);
}
