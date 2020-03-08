package lambdasinaction.chap5;
import lambdasinaction.chap4.*;

import java.util.stream.*;
import java.util.*;

import static lambdasinaction.chap4.Dish.menu;

/**
 * 5.6 数值流，解决装箱成本问题
 * 5.6.1 原型类型的流特化（专门处理数值流）
 *       java8 引入了3个原始类型特化流接口：IntStream/DoubleStream/LongStream，避免暗含的装箱成本；
 * 1）映射的数值流
 * 2）转换为对象流boxed()
 * 3）默认值OptionalInt
 * 5.6.2 数值范围 range()/rangeClose()
 * 5.6.3 数值应用：勾股数
 * */
public class NumericStreams{

    public static void main(String...args){

        // 数值
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);

        System.out.println("---------------------------- 1、forEach方法 -------------------------------");
        Arrays.stream(numbers.toArray()).forEach(System.out::println);

        System.out.println("---------------------------- 2、特化方法mapToInt----------------------------");
        int calories = menu.stream()
                           //.map(Dish::getCalories) //map返回的是Stream<T>，不能用
                           .mapToInt(Dish::getCalories) // 返回IntStream
                           .sum();
        System.out.println("Number of calories:" + calories);
        //


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
