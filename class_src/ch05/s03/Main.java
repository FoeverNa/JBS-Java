package ch05.s03;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.lang.annotation.ElementType.*;

/**
 * 에노테이션 (Annotations)
 * - 에노테이션의 사전적 의미는? 주석
 * - JVM, 컴파일러, 프레임워크 등에서 사용할 수 있도록 전달하는 메타데이터
 * - @Override, @Deprecated, @SuppressWarnings, @functionalInterface
 * - @Deprecated: 앞으로(향후 버전에서는) 사용되지 않을 클래스/메소드/변수 .. 사용하지 말라는 의미
 * - @SupresseWarnings: 특정 경고 메세지를 무시하도록 컴팡이러에 전달
 * - @SuprpressWarnings("unused") 변수만들어 놓고 사용하지 않으면 생기는 워닝을 무시
 */

// @Target, @Retention : 메타 에노테이션
// 에노테이션을 사용할 수 있는 대상을 설정한다
// TYPE : 클래스, 인터페이스, 에노테이션, 열거형에 사용할 수 있다
// FIELD : 필드(멤퍼 변수), 열거형 상수(얘도 사실은 열거형상수)
// 빠짐
// CONSTRUCTOR : 클래스의 생성자
// LOCAL_VARIABLE : 로컬 변수
// MODULE : 모듈
// ANNOTATION_TYPE : 에노테이션
// PACKAGE : 패키지
// 다양하고 정밀하게 구분해서 사용범위 지정할 수 있다

// @Retention
// 애노테이션 정보를 어디까지 유지할 것인가를 결정하는 Policy
// SOURCE: 컴파일러가 사용한다. .java -> .class로 변경하는 과정에서 사용 / . class파일에 포함하지 않는다
// 가장 짧게 쓰는 형태
// CLASS: 컴파일러도 사용하고 .class에도 포함이 된다. JVM에는 사용되지 않는다
// RUNTIME: .class에 포함되고 JVM에도 올라와서 Reflection API에서 사용 가능하다.
//Reflection API에서 사용한다는 것은 RunTime에서 사용한다는 것이다.
//Source나 Class는 우리가 사용할 수 없기에 신경쓸필요가없다. RunTime만이 우리가 커스텀해서 사용하는것에 영향을주기에 관심가져야 한다


@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Retention(RetentionPolicy.SOURCE)
// Source이기 때문에 컴파일러에서 사용하고 알아서 버리는것. 내용을 확인할 수는 없다 // 사용법만 알면되는종류
@interface SuppressWarningsClone {
    @SuppressWarnings("unused")// FIELD에 적혀있기 때문에 사용가능하다
    String[] value();
}

//커스텀 에노테이션 만들기

/**
 * 멤버 변수를 대상으로 하는, Reflection API에서 쓸 수 있는 애노테이션
 * 애노테이션 속성은 String[]인 value와 "기본값" 기본값을 가지는 valueTwo로 이루어짐
 */

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
        // 우리가 뭔가해보기 위해선 런타임으로 해야된다
@interface MyAnnotation { //여기까지하면 기본구색 완성성
    String[] value(); // 애노테이션 속성 ( 기본 성성 이름은 value) => 첫번째 속성은 vlaue가 되는게 좋다

    String valueTwo() default "기본값"; // 사용하지 않았을때 기본적으로 들어가는 값
}

class AnnotationUsage {
    @MyAnnotation("game") // valueTwo는 기본값이 있어서 넣지 않더라도 괜찮지만 기본값이 없으면 하나라도 값을 입력해야 된다
    String gameName = "여러분의 틱택토";

    @MyAnnotation(value = "server", valueTwo = "localhost")
    String serverIP;

    @MyAnnotation(value = "server", valueTwo = "8080")
    String serverPort;

    @MyAnnotation("game")
    String gameMode = "AI vs AI";


    @MyAnnotation(value = "db", valueTwo = "localhost")
    String database;

}

public class Main {
    @SuppressWarnings("unsed")
    int x;

    public static void main(String[] args) throws IllegalAccessException {
        @SuppressWarnings("unused")
        int x;
        //public @interface SuppressWarnings => @inteface로 워닝 구현

        AnnotationUsage obj = new AnnotationUsage();
        Map<String, Object> gameProp = new HashMap<>();
        Map<String, Object> serverProp = new HashMap<>();
        Map<String, Object> dbProp = new HashMap<>();

        Field[] fields = AnnotationUsage.class.getDeclaredFields(); // 필드 정보를 가져오는 부분(Reflection API 이용(
        for (Field field : fields) {
            MyAnnotation annotation = field.getDeclaredAnnotation(MyAnnotation.class); // 필드에서 Annotation 정보 가져오는 부분(Reflection API)(멤버이름, 값정도)
            if (field.get(obj) == null) { // 필드 값이 비어있는 경우 valueTwo에서 가져온다
                field.set(obj, annotation.valueTwo());
            }
            if (annotation.value()[0].equals("game")) {
                gameProp.put(field.getName(), field.get(obj));

            } else if (annotation.value()[0].equals("server")) {
                serverProp.put(field.getName(), field.get(obj));
            } else {
                dbProp.put(field.getName(), field.get(obj));
            }
        }
        System.out.println(gameProp);
        System.out.println(serverProp);
        System.out.println(dbProp);

        // 이런식으로 프레임워크를 사용한다면 Reflection API 사용하는 것이라고 생각하고 그냥 사용하면된다
        // Reflection에서서
    }
    // @Override -> 도 들어가서 살펴보고 왔다
}
