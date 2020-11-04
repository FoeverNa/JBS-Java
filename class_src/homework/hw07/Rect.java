package homework.hw07;

import java.util.Arrays;

/**
 * 아래 테스트 코드가 정상 동작하도록 클래스들을 완성하시오.
 *
 * getArea(): 사각형의 넓이를 반환한다.
 * getCenterOfMass(): 사각형의 질량중심을 반환한다.
 * GetAllPoints(): 사각형의 네 점을 배열로 반환한다.
 * rot90(): Pivot을 기준으로 사각형을 90도 회전시킨다.
 */


class Vector2D {
    public float x, y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {

        return this.x +","+this.y;
    }
}

class RectCore {
    protected Vector2D pos;
    protected float w, h;


    public RectCore(float x, float y, float w, float h) {
        this.pos = new Vector2D(x, y);
        this.w = w;
        this.h = h;
    }

    public String toString() {

        return "RECTCORE("+this.pos+","+w+","+h+")";
    }
}


public class Rect extends RectCore {
    String rotRec;

    public Rect(float x, float y, float w, float h) {
        super(x, y, w, h);
        rotRec = "";
    }
    // 위에 객체가져오기위해서 super()해야했음

    public float getArea() {

        return this.w * this.h;
    }

    public Vector2D getCenterOfMass() {

        return new Vector2D(pos.x + (w /2.0f), pos.y +(h /2.0f));
    }

    public Vector2D [] getAllPoints() {

        return new Vector2D[]{ pos,
                               new Vector2D(pos.x,(pos.y+h)),
                               new Vector2D((pos.x+w),pos.y),
                               new Vector2D((pos.x+w),(pos.y+h))};
    }


    public void rot90(Vector2D pivot) {
        Vector2D[] oldPoints = getAllPoints();
        Vector2D[] newPoints = new Vector2D[4];
        for (int i = 0; i < oldPoints.length; i++) {
            newPoints[i] = new Vector2D(
                    -(oldPoints[i].y - pivot.y) + pivot.x,
                    (oldPoints[i].x - pivot.x) + pivot.y);
        }

        float min_x = newPoints[0].x;
        float min_y = newPoints[0].y;
        float max_x = newPoints[0].x;
        float max_y = newPoints[0].y;

        for (Vector2D twoD : newPoints) {
            min_x = Math.min(min_x, twoD.x);
            min_y = Math.min(min_y, twoD.y);
            max_x = Math.max(max_x, twoD.x);
            max_y = Math.max(max_y, twoD.y);
        }
        pos = new Vector2D(min_x, min_y);
        w = max_x - min_x;
        h = max_y - min_y;
    }

        @Override
        public String toString () {
            String s = super.toString();
            s += "\n Area: "+getArea();
            s += "\n CoM: "+getCenterOfMass();
            s += "\n";
            return s;
        }


    }


class RectTest {
    public static void main(String[] args) {
        Rect rect = new Rect(0.5f, 0.7f, 1.5f, 2.3f);
        System.out.println("Area: " + rect.getArea());
        System.out.println("CoM: " + rect.getCenterOfMass());
        System.out.println("All Points: " + Arrays.toString(rect.getAllPoints()));

        rect.rot90(new Vector2D(0.4f, 0.2f));
        System.out.println("Rotated rect: " + rect);
        // toString 메서드자체가 오버라이드 되면 객체명을 썼을 때 그 출력문을 출력하게 되어있데

    }
}

//
// - 수학적 개념이 어려웠던 예제인데 실무에 가면 수학적 개념을 통한 문제해결 많이 하게 될테니 나올때마다 익숙해져야한다
// - 메서드에서 로컬변수 만들지 않고 return 값에 바로 출력될 수 있게 하는것 연습하자
// - 해당 상황에서만 할 수 있는 해답이 아닌 하나의 원리를 발견해서 여러상황에서 사용할 수 있는 코드를 만들자
// - 자바 키워드 암기 뿐 만아니라 이런 문제를 해결할 수 있는 능력을 키우는데 중점 두자


// 질량 중심  : 물체가 있을때 각각의 위치마다 질량의 덴서티를 곱해서, 위치마다
// 4점중에 평균지점을 찍으면됬데 공식은 맞은듯, // 적분을 해야하는 상황이 맞데..
// 특정상황에 맞춰서 어떤 공식을 사용해야하는지 알아야한다..그래야 실무함..ㅠㅠㅠ //
// 내가고민햇던거랑 마찬가지...그림그리는것조은데 그것을 가지고 구하루잇는 공식을 생각해봐봐... 수학자들이 다 해줄거야



//피벗을 중심으로 90도를 돌리라는말....
//90도를 돈다는것은... 점들이 모두 90도로 돌거야
// 일반적인 상황으로 적용하는건 이번에도 실패..
//원점을 기준으로 x > -y가 되고 y -> x가 되는거래
// 그럼 피봇을 기준으로 동작을 하려면 원점을 이동시키고 90도이동시키고 다시 피벗으로 이동시키면됨
// 피벗 x,y를 빼주면 원점으로 옮겨간데(요백터빼기 더백터는 요백터리...몬솔)
//  그리고 4점에 min max를 통해 위치를 유추하고
// 다시 민민을 모아서 포지션 잡아주고 맥스에서 빼서 윗스 하이트를 계산해줌
// 좌표계 로테이션 따로 공부하기 -> 나올대마다 배우는 습관이 좋은 습관 => 개발자는 모든걸 알아야한데 으슈바라ㅓㅏㅣㄹ머지;ㄷ겆닥;ㅣ젇ㄱ



// 코딩을 위한 유용한 클래스를 만들기위해선 실제 이런 문제들을 직면하게됨...으슈발
// 그래서 이런거랑 친해져야한대
// 자바키워드 암기도 중요하지만 실제 이런 문제를 해결할수있어야 실무에 투입가능하다.....맞말
// pos 는 정확히 정의되지 않았기 때문에 어디에 있는지는 우리가 선택할수 있었데
//
