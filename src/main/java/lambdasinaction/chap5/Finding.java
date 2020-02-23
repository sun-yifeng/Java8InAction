package lambdasinaction.chap5;
import lambdasinaction.chap4.*;

import java.util.stream.*;
import java.util.*;

import static lambdasinaction.chap4.Dish.menu;

/**
 * 5.3 查找和匹配
 * 5.3.1 anyMatch()
 * 5.3.2 allMatch(), noneMatch()
 * 5.3.3 findAny() 无参数，可以结合filter和findAny来实现查询；
 * 5.3.4 findFirst() 查找第一个元素
 * */
public class Finding{

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));

    }

    // 5.3.1 anyMatch() 菜单中是否有素食
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    // 5.3.2 allMatch() 菜单中的食物是否健康（小于1000卡）
    private static boolean isHealthyMenu(){
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    // 5.3.2 noneMatch() 菜单中是否无大于1000卡的食物
    private static boolean isHealthyMenu2(){
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    // 5.3.3 findAny() 查找菜单中的任何一道菜
    // Option<T>类是一个容器类，代表值是否存在；避免null;
    private static Optional<Dish> findVegetarianDish(){
        return menu
                .stream()
                .filter(Dish::isVegetarian)
                .findAny(); // 无参数
                // 如果包含则打印，否则do nothing
                //.ifPresent(d -> System.out.println(d.getName()));
    }

    // 5.3.4 findFirst() 查找第一个元素
    List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
    Optional<Integer> firstSquareDivisibleByThree =
            someNumbers.stream()
                    .map(x -> x * x)
                    .filter(x -> x % 3 == 0)
                    .findFirst(); // 9
    
}
