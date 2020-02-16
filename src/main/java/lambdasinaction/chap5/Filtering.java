package lambdasinaction.chap5;
import lambdasinaction.chap4.*;

import java.util.stream.*;
import java.util.*;
import static java.util.stream.Collectors.toList;

import static lambdasinaction.chap4.Dish.menu;

/**
 * 5.1 筛选和切片
 * */
public class Filtering{

    public static void main(String...args){

        // 5.1.1 用谓词筛选(filter)
        List<Dish> vegetarianMenu =
            menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        // 5.1.2 筛选各异（去重distinct）的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
               .filter(i -> i % 2 == 0)
               .distinct()
               .forEach(System.out::println);

        // 5.1.3 截短流（limit）
        List<Dish> dishesLimit3 =
            menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        dishesLimit3.forEach(System.out::println);

        // 5.1.4 跳过（skip）
        List<Dish> dishesSkip2 =
            menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        dishesSkip2.forEach(System.out::println);
    }
}
