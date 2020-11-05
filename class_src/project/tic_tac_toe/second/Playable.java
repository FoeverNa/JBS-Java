package project.tic_tac_toe.second;

public interface Playable<T> {
    boolean play(T move, Player player);
}
