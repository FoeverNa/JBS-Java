# 틱택도 게임 구현하기

틱택토 게임을 구현하는 예제를 라이브 코딩을 통해 보여주셨고 수업 후에 복습하며 코드에 의미들을 정리해본 내용입니다

## 예제

- 틱택토 게임 구현하기 

   틱택토 레퍼런스

  - 틱택토 게임을 UML로 설계하고, Java로 구현하시오.

    - UML로 클래스와 인터페이스 등을 표현하고, 상속/포함 관계를 나타내시오.
    - Java로 구현하면서 설계가 변경되는 경우, UML에 반영하시오.

  - 틱택토 게임의 룰은 기본 룰을 따르시오.

  - 프로그램 실행 후 아래 순서로 동작하도록 구현하시오.

    - 정수 N을 입력받으시오.
    - 각 플레이어가 사람인지 AI인지 입력받으시오.
    - 두 플레이어 중 한 플레이어가 N번 먼저 승리할 경우 게임을 종료하고, 그렇지 않으면 계속 반복하여 플레이하시오.
      - 사람인 경우, 사람의 차례에 좌표로 말을 둘 곳을 입력받으시오.
        - 이미 말이 있는 좌표를 입력받을 경우, 다시 입력 받으시오.
      - AI인 경우, 입력이 허용되는 좌표를 랜덤하게 선택하도록 하시오.

  - 다음 상속 관계를 이용하여 구현하시오.

    - Player 추상클래스를 구현하는 HumanPlayer와 AIPlayer 클래스를 각각 구현하여 사용하시오.

      

## # 예제 풀이

```java
public interface Playable<T> {  // 1번
    boolean play(T move, Player player);
}
```

```java
public interface Observable<T> { //2번
    T observe();
}
```

```java
public interface Initializable { // 3번
    void initialize();
}
```

 먼저 인터페이스를 만들어  기본적으로 어떤 기능 들이 구현되어야 할지 대략적으로 설정해 놓습니다.

1. plable은 play()메서드를 통해 게임을 play하는 클래스에서 구현됩니다, 제너릭 인터페이스로 선언해 move에 타입을 나중에 결정합니다.
2. Obsrvbale은 게임을 살펴보는 기능을 구현합니다. 어떤 것을 리턴할지 결정되지 않았기 때문에 제너릭 인터페이스로 선언합니다
3. Initailizable은 게임을 초기화 하는 기능을 구현합니다.

```java
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
```

틱택토 게임에서 입력을 받을 좌표역할을 할 Point 클래스를 만들고 생성자와 Getter Setter도 만들어 줍니다



```java
package lecture;

public class Board implements Initializable {
    enum WinnerStatus { //10번
        NOT_FINISHED, TIE, PLAYER_ONE, PLAYER_TWO;
    }

    static final char BLANK = '-'; // 2번
    static final char MARKER_ONE = 'o';
    static final char MARKER_TWO = 'x';

    final char[][] board; // 1번

    public Board(int size) {// 4번
        board = new char[size][size];
        initialize();
    }

    @Override 2번
    public void initialize() { // 3번
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = BLANK;
            }
        }
    }

    public boolean isPossible(Point move) { // 5번
        if (move.getX() < 0 || move.getX() >= board.length) {
            return false;
        } else if (move.getY() < 0 || move.getY() >= board.length) {
            return false;
        } else {
            return board[move.getY()][move.getX()] == BLANK;
        }
    }

    public boolean setMark(Point move, char mark) {//6번
        if (!isPossible(move)) {
            return false;
        } else {
            board[move.getY()][move.getX()] = mark;
            return true;
        }
    }

    public void showBoard() { //7번
        for (char[] chars : board) {
            for (char mark: chars) {
                System.out.printf("%s ", mark);
            }
            System.out.println();
        }
    }

    public WinnerStatus getWinnerStatus() { // 11번
        if (isMarkWin(MARKER_ONE)) {
            return WinnerStatus.PLAYER_ONE;
        } else if (isMarkWin(MARKER_TWO)) {
            return WinnerStatus.PLAYER_TWO;
        } else if (isMarkFull()) {
            return WinnerStatus.TIE;
        } else {
            return WinnerStatus.NOT_FINISHED;
        }
    }

    private boolean isMarkWin(char mark) { // 8번
        for (char[] chars : board) {
            int sum = 0;
            for (int j = 0; j < board[0].length; j++) {
                sum += chars[j] == mark ? 1 : 0;
            }
            if (sum == board.length) {
                return true;
            }
        }

        for (int j = 0; j < board[0].length; j++) {
            int sum = 0;
            for (char[] chars : board) {
                sum += chars[j] == mark ? 1 : 0;
            }
            if (sum == board.length) {
                return true;
            }
        }

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < board.length; i++) {
            sum1 += board[i][i] == mark ? 1 : 0;
            sum2 += board[i][board.length - 1 - i] == mark ? 1 : 0;
        }
        return sum1 == board.length || sum2 == board.length;
    }

    private boolean isMarkFull() { //9번
        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (chars[j] == BLANK) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

틱택토 게임판 역할을 해줄 Baord 클래스를 만들어 줍니다. 게임판을 초기화 해주기위해 Initalizable 인터페이스를 구현합니다

1. 게임판 역할을 하게 될 2차원 배열을 선언합니다
2. 게임판에 각 player에 돌역할을 할 상수들을 선언합니다. 빈자리는 '-'로 채워서 구분할 수 있게 합니다.
3. 게임판을 빈자리역할을 하는 상수로 채워 초기화를 해줍니다.
4. 생성자를 통해 2차원 배열의 생성과 판을 빈자리로 채우는 초기화가 동시에 일어나도록 합니다
5. 게임판(2차원 배열)에 Player돌을 입력하기 전에 빈자리가 맞는지 체크하는 메서드를 작성합니다.
6. 입력받은 move(좌표)가 비어있는지 확인한 후에 게임판에 Plyaer돌을 입력하는 메서드를 작성합니다.
7. 게임판을 출력하는 메서드를 작성합니다
8. 입력받은 mark(player돌)가 승리하였는지 확인해주는 메서드를 작성합니다. 가로 세로 대각선으로 같은 player mark가 입력되어 있으면 해당 player가 승리합니다
9. 게임판이 다찼는지 확인하는 메서드를 작성합니다. BALNK가 없으면 게임판이 다찬 것이고 게임은 비긴것으로 처리합니다
10. 게임의 상태를 나타내는 상수들을 모아놓은 enum 을 작성합니다
11. if ~ else if 구문을 통하여 아래서 작성한 isMarkwin 과 isMarkfull 메서드를 조건으로 활용하여  4가지 상태를  출력합니다



```java
public class TicTacToe implements Initializable, Observable<Board>, Playable<Point> {
    static final int BOARD_SIZE = 3;
    protected final Board board = new Board(BOARD_SIZE); //1번
    protected Player playerOne, playerTwo;

    public TicTacToe(Player playerOne, Player playerTwo) {
        setPlayerOne(playerOne);
        setPlayerTwo(playerTwo);
    }

    public void setPlayerOne(Player player) {
        playerOne = player;
        playerOne.setTicTacToe(this);
    }

    public void setPlayerTwo(Player player) { //2번
        playerTwo = player;
        playerTwo.setTicTacToe(this);
    }

    @Override
    public void initialize() {//3번
        board.initialize();
    }

    @Override
    public Board observe() {//4번
        return board;
    }

    @Override
    public boolean play(Point move, Player player) {//5번
        if (!board.isPossible(move)) {
            return false;
        } else {
            char mark = player == playerOne ? Board.MARKER_ONE : Board.MARKER_TWO;
            board.setMark(move, mark);
            return true;
        }
    }
}
```

TicTactoc클래스를 만들고 Initializable, Observable<Board>, Playable<Point>을 구현하도록 합니다

1. 상수 BOARD_SIZE를 선언하고 그것을 활용해 board 변수에 Board객체를 생성합니다.
2. plyaerOne,Two 변수를 선언하고 생성자와 setPlayer 객체를 통해서 player객체들을 TictacToe에서 참조하고 각각의 player 객체에서 Tictactoe객체를 참조할수 있게 합니다
   1. 개인적으로 이렇게 서로의 객체를 참조하는 구조는 처음봐서 신기하기도 하면서 머리속이 복잡해지는 구간이었습니다
   2. 이를 통해서 서로의 메서드에 접근가능하게되어 tictace게임과 play가 상호작용할수 있고 더나아가 tictactoe가 composition 한 board객체에 player객체가 접근할수 있게 해주는 중요한 부분이었습니다.
3. tiactatoe에서도 boad를 innitialize할 수있도록 메서드를 작성합니다
4. observe()메서드를 통하여 tictactoe를 통해 board 메서드에 접근할 수 있는 통로를 만듭니다
5. play()메서드를 작성하고 다시한번 boad.isPossible메서드를 통해 다시한번 좌표를 검사하고 입력받은 player에 mark가 입력되도록 하여 setMark메서드를 통해 돌을 놓도록 합니다.



```java
public abstract class Player {
    int totalWin = 0;
    String name = "default name";
    TicTacToe ticTacToe; //1번

    public void win() { 
        totalWin++;
    }

    public int getTotalWin() {
        return totalWin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TicTacToe getTicTacToe() {
        return ticTacToe;
    }

    public void setTicTacToe(TicTacToe ticTacToe) {//2번
        this.ticTacToe = ticTacToe;
    }

    abstract void play();//3번
}
```

조건에서 처럼 Player 추상클래스르 만들어 이를 상속받을 클래스들에서 공통적으로 가지거나 구현해야할 것들을 구현합니다

1. 멤버변수를 선언하여 자식클래스들이 totalwin, name, ticTacToe 변수를 공통으로 가질 수 있게 합니다
2. 멤버변수에 접근할 수 있도록 메서드들을 설정합니다. win()메서드는 승리후 각 객체의 totalwin을 증가시켜주는 메서드입니다.
3. 각각 player객체에서 play를 하도록 추상메서드를 선언합니다

```java
import java.util.Scanner;

public class HumanPlayer extends Player {
    @Override
    void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Please input %s's move (x y): ", name);

            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;

            Point move = new Point(x, y);
            if (ticTacToe.observe().isPossible(move)) {
                ticTacToe.play(move, this);
                break;
            }
        }
    }
}
```

HumanPlayer 클래스를 만들고 예제 조건에 맞게 move를 isPossible을 통과할때까지 반복적으로 받아서 ticTactoe.play에 입력하는 play()메서드를 작성합니다

```java
public class AIPlayer extends Player {
    @Override
    void play() {
        int x, y;
        while (true) {
            x = (int)(Math.random() * TicTacToe.BOARD_SIZE);//1번
            y = (int)(Math.random() * TicTacToe.BOARD_SIZE);

            Point move = new Point(x, y);
            if (ticTacToe.observe().isPossible(move)) {
                ticTacToe.play(move, this);
                break;
            }
        }
        System.out.printf("AI Player %s's input is: (%d %d)\n", name, x + 1, y + 1);
    }
}
```

AIPlayer 클래스를 만들고 예제 조건에 맞게 move를 isPosiible을 통과할때까지 랜덤하게 생성하여 ticTacToe.play()로넘겨주는 play메서드를 작성합니다. 

1. Math.random 메서드는 0부터 1미만의 수를 랜덤으로 생성하는 함수로 TicTacToe.BOARD_SIZE 를 곱해주면 0~2에 수를 랜덤으로 생성해주는 효과가 있습니다

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player playerOne = new AIPlayer();
        Player playerTwo = new HumanPlayer();

        playerOne.setName("Jack");
        playerTwo.setName("Jill"); // 1번

        TicTacToe ticTacToe = new TicTacToe(playerOne, playerTwo);//2번

        Scanner scanner = new Scanner(System.in);
        System.out.print("How many wins to finish the game?: ");
        int N = scanner.nextInt();//3번

        while (playerOne.getTotalWin() < N && playerTwo.getTotalWin() < N) {//4번
            Player player = Math.random() > 0.5 ? playerOne : playerTwo;
            ticTacToe.initialize();

            System.out.println("-- Current Score --");
            System.out.printf(" %s %d : %d %s\n", playerOne.getName(), playerOne.getTotalWin(),
                    playerTwo.getTotalWin(), playerTwo.getName());
            System.out.println("-----------------");//5번

            while (ticTacToe.observe().getWinnerStatus() == Board.WinnerStatus.NOT_FINISHED) {//6번
                System.out.println();
                System.out.println("-- Current Board Status --");
                ticTacToe.observe().showBoard();
                System.out.println("--------------------------");

                player.play();
                player = player == playerOne ? playerTwo : playerOne;//7번
            }

            System.out.println();
            System.out.println("-- Final Board Status --");
            ticTacToe.observe().showBoard();
            System.out.println("--------------------------");//8번

            switch (ticTacToe.observe().getWinnerStatus()) {//9번
                case PLAYER_ONE -> {
                    playerOne.win();
                    System.out.printf("%s has won!\n", playerOne.name);
                }
                case PLAYER_TWO -> {
                    playerTwo.win();
                    System.out.printf("%s has won!\n", playerTwo.name);
                }
                default -> System.out.println("The game ended tie.");
            }
            System.out.println();
        }

        System.out.println("-- Final Score --"); // 10번
        System.out.printf(" %s %d : %d %s\n", playerOne.getName(), playerOne.getTotalWin(),
                                            playerTwo.getTotalWin(), playerTwo.getName());
        System.out.println("-----------------");
        System.out.println();
    }
}
```

마지막으로 작성한 메서드들을 엮어서 실행하는 Main 메서드를 작성한다

1. PlayerOne,Two 겍체를 생성하고 각각의 객체의 name 변수를 설정한다
2. Player 객체들을 TicTactoe 객체 생성할때 입력파라미터에 넣어 서로의 객체를 참조할 수 있게 한다
3. Scanner를 통해 최종 승수를 입력 받는다
4. 첫번째 while문을 통해 player 한쪽이 최종승수를 넘으면 반복을 멈추도록 설정한다
5. 처음 플레이할 player 객체를 랜덤으로 결정한뒤 boad를 초기화하고 시작 스코어를 출력해준다
6. 2번째 while문을 통하여 getWinnerStatus가 NOT_FINISHED인 동안은 반복하도록 설정한다
7. 현재 게임판을 출력한 후 player객체가 play하도록 한뒤 player의 차례를 바꿔는 메소드를 사용한다
   1. 6~7번 과정을 통해 한쪽이 승리하거나 비길때까지 턴을 바꾸면서 player가 틱택톡을 플레이하도록한다
8. 한게임이 끝나고 6번 반복문을 빠져나오면 최종 게임판을 출력해준다
9. switch ~case 문을 통해 각각의 Winstatus에 따라 행동을 설정해준다
   1. 그후 다시 4번에 while문으로 돌아가 최종승수를 넘은쪽이 있는지 확인하고 없으면 있을 때까지 5~9번 행동을 반복한다
10. 최종 승수에 도달한 플레이어가 있을시 4번 반복문을 빠져나와 최종 스코어를 출력하고 게임을 마친다



### 느낀점

- 객체와 객체가 서로를 참조한다던지, 메서드안에 다른 메서드들이 사용된다던지 하는 여러 기법들이 등장해서 정신이 없었지만 이게 객체지향적인 프로그래밍인가 맛을 보게 하는 예제풀이였습니다
- 혼자풀이 할때 이와같이 풀이하겠다고 할수는 없겠지만 이런것들을 지향하면서 하면 좋겠다는 것들이 생기는 시간 이었고 앞으로도 다른 분들의 코드를 볼수 있는 시간을 많이 갖어야 겠다는 생각이 들었습니다.