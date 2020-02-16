package lambdasinaction.chap4;

import java.util.*;
import java.util.stream.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import static lambdasinaction.chap4.Dish.menu;

/**
 *
 *  java7和java8对集合处理的不同
 *  需求：从菜单中筛选出低热量（小于400卡）的菜单，并且排序，打印出菜单名称
 *  第一种方案：用java7操作集合
 *  第二种方案：用java8操作集合
 *
 *  流操作包括三件事：
 *  1、数据源（如集合）
 *  2、中间操作（filter,map,limit,sorted,distinct）
 *  3、终端操作（forEach,count,collect）
 *
 *  流的定义：操作（流方法）数据源（集合、数字，IO）生成元素序列（集合）
 *
 * */
public class StreamBasic {

    public static void main(String...args){

        // Java 7
        System.out.println("----------------java7 开始-----------------");
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);
        System.out.println("----------------java7 结束-----------------");

        // Java 8
        System.out.println("----------------java8 开始-----------------");
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
        System.out.println("----------------java8 结束-----------------");

    }

    /** 第一种方案：用java7操作集合  */
    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }
        // 中间临时（垃圾）变量
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    /** 第二种方案：用java8操作集合(流:以声明的方式处理集合，不是临时的写一个实现)  */
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)   // 选出400卡的菜肴
                .sorted(comparing(Dish::getCalories)) // 按照卡路里排序
                .map(Dish::getName)                   // 提取菜肴的名称
                .collect(toList());                   // 名称保存到list中
    }
}
