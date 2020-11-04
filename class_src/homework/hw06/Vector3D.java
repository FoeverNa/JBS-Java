package homework.hw06;

/**
 * 3차원 벡터 클래스 구현하기
 *
 * 3차원 벡터를 클래스로 구현하시오.
 *
 * 속성
 * float타입의 x, y, z
 *
 * 생성자
 * Vector3D(float x, float y, float z)
 *
 * 메소드
 * - x, y, z에 대한 getter 및 setter
 * - add(): 벡터끼리의 덧셈, 실수와의 덧셈을 반환
 * - sub(): 벡터끼리의 뺄셈, 실수와의 뺄셈을 반환
 * - inner(): 두 벡터의 내적을 반환
 * - mul(): 실수와의 곱을 반환
 * - mag(): 벡터의 크기를 반환
 * - print(): 벡터의 내용을 "(%.3f, %.3f, %.3f)\n" 형식으로 콘솔에 출력
 */

public class Vector3D {

    float x;
    float y;
    float z;



    public Vector3D (float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void print(){

        System.out.printf("(%.3f, %.3f, %.3f)\n",x,y,z);

    }

    public Vector3D add(Vector3D v){

         return  new Vector3D(this.x + v.x,
                              this.y + v.y,
                              this.z + v.z);
    }

    public Vector3D add(float f){
        return  new Vector3D(this.x + f,
                             this.y + f,
                             this.z + f);
    }

    public Vector3D sub(Vector3D v){

        return  new Vector3D(this.x - v.x,
                             this.y - v.y,
                             this.z - v.z);
    }

    public Vector3D sub(float f){

        return  new Vector3D(this.x - f,
                             this.y - f,
                             this.z - f);
    }

    public float inner(Vector3D v){

        return (this.x * v.x) + (this.y * v.y) + (this.z * v.z);
    }


    public Vector3D mult(float f){
        return  new Vector3D(this.x * f,
                             this.y * f,
                             this.z * f);
    }
    public float mag(){
        return (float)Math.sqrt((Math.pow(this.x,2) + Math.pow(this.y,2)
                          + Math.pow(this.z,2)));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}

class Vector3DTest {
    public static void main(String[] args) {
        Vector3D v1 = new Vector3D(0.5f, 0.2f, 0.9f);
        Vector3D v2 = new Vector3D(0.8f, 0.1f, 1.3f);

        v1.add(v2).print();
        v2.sub(v1).print();
        v1.add(0.2f).print();
        v2.sub(0.05f).print();
        System.out.println(v1.inner(v2));
        v1.mult(1.2f).print();
        System.out.println(v2.mag());
    }
}

/**
 * 기본생성자 생성하지 않고 주어진 파라미터 생성자 활용할 수 있었던것 기억하기
 * 메서드안에 불필요한 변수생성 줄이고 return값에 수식넣기
 * 객체를 생성하거나 혹은 기존 객체의 값 바꾸는 방법 무슨 방법을 사용하던 하나로 통일해서 일관성있게 작성
 */
