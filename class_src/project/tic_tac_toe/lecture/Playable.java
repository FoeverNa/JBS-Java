package project.tic_tac_toe.lecture;

public interface Playable<T> {
    boolean play(T move, Player player);
}
