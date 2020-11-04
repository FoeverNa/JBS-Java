package ch03.s02.p02;



    /**
     * 클래스와 객체의 메모리 구조
     * - 클래스 영역 ( Class area, method area, code area, static area)
     *  -> field 정보, method 정보, type 정보, constant pool
     *  
     * - 스택 영역(Stack area)
     *  -> method 호출 시 선언된 로컬 변수 (임시로 있다가 사라짐)
     *  
     *  - 힙 영역 (Heap area)
     *    -> new 키워드로 생성된 객체(객체의 속성(멤버 변수))
     *    -> garbage collection(GC)이 동작하는 영역 
     *      : 더이상 사용하지 않는 메모리를 알아서 변환하는 JVM의 기능
     *      
     *  클래스영역은 항상 잡혀 있고 힙영역은 사용후에 GC가 없애주고 스택영역은 메소드 사용 후에 알아서 사라짐
     *   
     */

        // 어떤 메모리에 잡히게 되는가
        
    public class MemoryStructure {// 클래스 영역
        int x, y;  // 힙 영역(new 키워드로 생선된 객체에 속한 변수로 생성됨)
        // int x,y의 값은 값이 실제로 힙영역에 메모리가 생성되서 그값을 담게됨
        
        String string = "String!!!"; // 힙 영역(변수자체는 힙영역에 생성), 상수풀("String!!!"은 상수풀에 생성)
        // string은 클래스이기 때문에 값이 아닌 상수풀에 잡힌 값에 참조값이 담기게됨
        
        public void method(int value) {// 클래스 영역'
            // int value => 스택 영역 // 다른 변수가 있다면
            char c = 'w'; // 스택 영역
        } // 메서드 사용시에 임시로 스택영역에 생기고 사용후에 사라지는 영역(지역 변수)
        // 주소값을 몇bit로 쓰느냐에 따라 32-bit, 64-bit으로 나뉨, 근데 요즘은 대부분 64-bit os를 쓰기 때문에 대부분 64bit
        // 메모리 주소를 몇 bit를 쓰느냐에 따라 운영체제가 32-bit 64-bit나뉘고 ram을 4gb이상쓰기위해 64bit os가 주를 이룸
}
