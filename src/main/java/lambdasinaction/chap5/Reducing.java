package lambdasinaction.chap5;
import lambdasinaction.chap4.*;

import java.util.stream.*;
import java.util.*;

import static lambdasinaction.chap4.Dish.menu;

/**
 * 5.4 归约
 *  reduce 方法有两个参数
 *  1）初始值
 *  2）所有元素在一起的操作，比如+
 * */
public class Reducing{

    public static void main(String...args){

        // 集合
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);

        // forEach
        System.out.println("---------------------forEach：求和---------------------");
        int total = 0; // 初始值
        for (int i: numbers) {
            total += i;
        }
        System.out.println(total);

        // 归约：求和
        System.out.println("---------------------归约：求和-----------------------");
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        // 归约：求和, reduce变体，不接受初始值
        System.out.println("---------------------归约：求和（省略初始值）-----------");
        Optional<Integer> i = numbers.stream().reduce((a, b) -> a + b);
        System.out.println(i);

        // 归约：方法引用，更加简单，
        System.out.println("---------------------归约：方法引用--------------------");
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        // 归约：求最大值
        System.out.println("---------------------归约：求最大值--------------------");
        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        // 归约：求最大值
        System.out.println("---------------------归约：求最小值--------------------");
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        System.out.println("---------------------归约：菜谱热量--------------------");
        int calories = menu.stream()
                           .map(Dish::getCalories)
                           .reduce(0, Integer::sum);
        System.out.println("Number of calories（总热量）:" + calories);
    }
}
