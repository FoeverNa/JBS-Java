# 틱택토 게임 ExpertAI 구현하기

 지난번에 구현한 틱택토 게임에서 더 나아가 무조건 게임에서 이기는 ExpertAI를 구현해 주신것에 대한 정리입니다



## 구현 내용 정리

 먼저 ai를 구현하기 사용한 miniMax의 개념을 간단하게 설명하면 내턴에서 내가 이득을 최대로 보는 것을 선택하는것 처럼 상대방도 상대방 턴에 이득을 최대한 보려고 한다는 것을 가정하고 내턴에서 한 수를 두었을 때 내턴에서 승리하는 것도 고려하지만 상대방 턴에서 상대방이 이기는 것도 고려하는 것을 말합니다. 즉 내턴과 상대방턴 한수 한수를 모두 살펴봐서 내가 승리하는 것보다 상대방이 승리하는 것이 먼저라면 해당수는 나에게 최선의 수가 되지 않는 것입니다. 이를 전제로 코드를 살펴보겠습니다



```java
package advanced;
//1번
public class ExpertAIPlayer extends Player {
    static final int SCORE_WIN = 1;
    static final int SCORE_LOSE = -1;
    static final int SCORE_TIE = 0;
	
    //2번
    @Override
    void play() {
        Point move = findBest();
        ticTacToe.play(move, this);
        System.out.printf("Expert AI Player %s's input is: (%d %d)\n", name, move.getX() + 1, move.getY() + 1);
    }

    //3번
    Point findBest() {
        Board board = ticTacToe.observe();
        int maxScore = Integer.MIN_VALUE;
        Point bestMove = new Point(0, 0);
        //4번
        char marker = findMarker(true);

        //5번
        for (int x = 0; x < TicTacToe.BOARD_SIZE; x++) {
            for (int y = 0; y < TicTacToe.BOARD_SIZE; y++) {
                Point move = new Point(x, y);
                if (!board.isPossible(move)) {
                    continue;
                }

                board.setMark(move, marker);
                
                //6번
                int score = miniMax(board, false);
                //7번
                board.setMark(move, Board.BLANK);
				
                if (score > maxScore) {
                    maxScore = score;
                    bestMove = move;
                }
            }
        }

        return bestMove;
    }
	
    //4번
    char findMarker(boolean isMyTurn) {
        boolean isPlayerOne = ticTacToe.getPlayerOne().equals(this);

        if ((isPlayerOne && isMyTurn) || (!isPlayerOne && !isMyTurn)) {
            return Board.MARKER_ONE;
        } else {
            return Board.MARKER_TWO;
        }
    }

    //6번
    int miniMax(Board board, boolean isMyTurn) {
        int bestScore = isMyTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        char marker = findMarker(isMyTurn);

        switch(board.getWinnerStatus()) {
            case PLAYER_ONE:
                return ticTacToe.getPlayerOne().equals(this) ? SCORE_WIN : SCORE_LOSE;
            case PLAYER_TWO:
                return ticTacToe.getPlayerTwo().equals(this) ? SCORE_WIN : SCORE_LOSE;
            case TIE:
                return SCORE_TIE;
        }

        for (int x = 0; x < TicTacToe.BOARD_SIZE; x++) {
            for (int y = 0; y < TicTacToe.BOARD_SIZE; y++) {
                Point move = new Point(x, y);
                if (!board.isPossible(move)) {
                    continue;
                }

                board.setMark(move, marker);
                
                int score = miniMax(board, !isMyTurn);
                board.setMark(move, Board.BLANK);

                if (isMyTurn) {
                    if (score > bestScore) {
                        bestScore = score;
                    }
                } else {
                    if (score < bestScore) {
                        bestScore = score;
                    }
                }
            }
        }
        return bestScore;
    }
}
```



- 1번 : Experert AI 클래스를 만들고 Player 추상클래스를 Extends합니다

- 2번 : Etends에서 상속받은 play()메서드를 작성합니다

  - move 변수에는 뒤에 작성할 findBest(); 메서드의 결과 값으로 나올 최선의 수를 담아줍니다
  - move 변수를 tictactoe.play메서드에 넣어 틱택토를 플레이하도록합니다

- 3번 : findBest()메서드를 작성하여 최선의 수를 찾습니다

  -  tictactoe 클래스에 board 클래스를 편하게 사용하기 위해  메서드 안에서 board 변수를 선언합니다
  - maxScore 변수를 선언하고 Integer.Minval 을 활용하여 가장 작은 수를 입력하여 비교가 용이하도록해줍니다
  - bestMove 변수를 선언하여 나중에 메서드의 결과 값이될 좌표를 담아주는 역할을 합니다
  - marker 변수를 선언하고 아래에서 작성할 findMarker()를 통해 현재 플레이어의 marker를 찾습니다

- 4번: 현재 Player의 marker를 찾아줄 findMarker 메서드를 작성합니다

  - 입력값으로 현재 자신의 턴인지 isMyturn 을 입력받습니다
  - 추가로 isPlayOne변수를 통하여 지금 객체가 PlayOne의 객체와 동일한지 결과를 담는 변수를 작성합니다
  - 조건문을 통해 자신의 객체가 playOne이면서 mytrun이면 playerOne의 marker를 반환하게 하고 아니라면 playerTwo의 marker를 반환하게 합니다

- 5번 : 다시 findBest()메서드로 돌아와서 3번에서 작성한 변수를 활용하여 최적의 수를 찾는 메서드를 작성합니다

  - boardsize크기까지 반복하는 2중 for문을 작성하여 새로운 좌표 move를 생성해줍니다
  - 작성한 move가 이미 boad에 존재한다면 다음 반복의 좌표를 생성할수있도록 continue합니다
  - 생성한 좌표가 boad에 없다면 setMark 를 통하여 해당 좌표에 mark를 입력하도록 합니다
  - 아래서 작성할 miniMax()를 통하여 현재 둔 수에 따른 모든 경우의 수를 평가하여 이기는 수면 1, 지는 수면 -1, 비기는 수면 0을 score변수에 담게 합니다

- 6번: 앞에서 둔수에 대하여 이어서 둘수 있는 모든 수를 두어서 해당 수가 어떤 결과를 내게 하는지 알려주는 miniMax 메서드를 작성합니다

  - bestScore변수를 선언하고 내턴인경우는 최소값이 상대방턴인경우는 최대값이 오게하여 비교에 용의하게 합니다
  - marker 변수를 선언해 현재 턴에 따른 marker를 담습니다
  - swich문을 통해 이번 수를 통해 게임이 끝났는지 확인하고 끝났다면 그에 따른 결과를 출력합니다
  - 게임이 끝나지 않았다면 다음수를 고려해야 되기 때문에 다시 2중 for문을 통해 move을 생성하고 이번엔 상대편 marker로 수를 둡니다
  - 해당수를 평가하기 위해 다시한번 miniMax를 재귀함수로 사용해서 턴을 바꿔가면서 게임이 끝날대까지 반복합니다
  - 반복되서 나온 결과를 score에 답고 내턴이라면 최소값과 비교하고 상대방 턴이면 최대값과 비교해서 그결과를 bestScore에 담아 출력합니다

- 7번 : 최선의 수를 알기 위해  두었던 수를 다시 빈칸으로 돌리기 위해 해당 move에 Blank를 입력합니다

  - miniMax의 결과로 담긴 해당 수에대한 결과를 담은 socre을 bestScore와 비교해서 bestScore보다 크면(승리하면) besScore에 담고 해당 좌표(move)를 bestMove에 넣습니다.

  - 위의 과정을 반복해서 모든 수의 결과 값을 비교하여 besScore가 높은(승리한경우)의 bestMove를 return합니다

    

  

## 느낀점

- 굉장히 어렵게 느껴진 예제인데 이전에 했던 메서드를 조금더 활용하고 재귀함수를 이용하니 풀리는 것을 보고 신기함을 느꼇다
- 강사님이 쉬운 코드를 사용해주신것도 있지만 그래도 포기하지 않고 코드를 읽고 실습까지 해볼 수 있어서 좋았다
  - 어려워 보이는 거솓 차근차근 도전해보는 습관 해야겠다