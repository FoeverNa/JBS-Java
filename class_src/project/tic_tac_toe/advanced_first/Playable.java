package project.tic_tac_toe.advanced_first;

public interface Playable<T> {
    boolean play(T move, Player player);
}
