package lambdasinaction.appa;

import java.util.Arrays;
import java.util.List;

/**
 * @program: lambdasinaction
 * @description: 泛型类
 * @author: sunyf
 * @create: 2020-01-27 17:14
 **/
public class Test<T> {

    private T a;

    public static<T> void test(T a){
        System.out.println(a);
    }

    public static void main(String[] args) {
        //List<String> list = Arrays.asList("sun", "yi", "feng");
        //new Test<String>().test("Hello");
        System.out.println("hello");
    }
}
