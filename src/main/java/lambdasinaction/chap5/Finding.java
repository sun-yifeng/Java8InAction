package lambdasinaction.chap5;
import lambdasinaction.chap4.*;

import java.util.stream.*;
import java.util.*;

import static lambdasinaction.chap4.Dish.menu;

/**
 * 5.3 查找和匹配
 *
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

    // 菜单中是否有素食
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    // 菜单中的食物是否健康（小于1000卡）
    private static boolean isHealthyMenu(){
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    // 菜单中没有大于1000卡的食物
    private static boolean isHealthyMenu2(){
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    // 查找菜单中的任何一道菜
    private static Optional<Dish> findVegetarianDish(){
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
    
}
