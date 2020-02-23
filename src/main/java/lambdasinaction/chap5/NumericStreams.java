package lambdasinaction.chap5;
import lambdasinaction.chap4.*;

import java.util.stream.*;
import java.util.*;

import static lambdasinaction.chap4.Dish.menu;

/**
 * 5.6 数值流，解决装箱成本问题
 *
 *
 * */
public class NumericStreams{

    public static void main(String...args){

        // 数值
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);

        System.out.println("---------------------------- 1、forEach方法 -------------------------------");
        Arrays.stream(numbers.toArray()).forEach(System.out::println);

        System.out.println("---------------------------- 2、特化方法mapToInt----------------------------");
        int calories = menu.stream()
                           //.map(Dish::getCalories)返回的是Stream<T>，不能用
                           .mapToInt(Dish::getCalories)
                           .sum();
        System.out.println("Number of calories:" + calories);


        System.out.println("---------------------------- 3、特化方法，求最大值--------------------------");
        // 默认值OptionalInt
        OptionalInt maxCalories = menu.stream()
                                      .mapToInt(Dish::getCalories)
                                      .max();

        int max;
        if(maxCalories.isPresent()){
            max = maxCalories.getAsInt();
        }
        else {
            max = 1;
        }
        // max = maxCalories.orElse(1);
        System.out.println("最大值：" + max);

        System.out.println("---------------------------- 4、从1到100的偶数 ----------------------------");
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                                 .filter(n -> n % 2 == 0);
        // 用count求个数
        System.out.println("从1到100的偶数" + evenNumbers.count());

        System.out.println("---------------------------- 5、勾股数 -----------------------------------");
        Stream<int[]> pythagoreanTriples =
               IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                               .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).boxed()
                                               .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));       

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2])); 

    }
   
    public static boolean isPerfectSquare(int n){
        return Math.sqrt(n) % 1 == 0;
    }

}
