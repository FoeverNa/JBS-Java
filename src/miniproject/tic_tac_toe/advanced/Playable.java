package miniproject.tic_tac_toe.advanced;

public interface Playable<T> {
    boolean play(T move, Player player);
}
