package lambdasinaction.chap6;

import java.util.*;
import java.util.function.*;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

/**
 * 6.2 预定义收集器的使用: 归约和汇总
 */
public class Summarizing {

    public static void main(String ... args) {
        //菜单里有多少菜
        System.out.println("有多少种菜: " + howManyDishes());
        //菜单中热量最大的菜，使用reducing()，返回Dish
        System.out.println("热量最大的菜: " + findMostCaloricDish());
        //菜单中热量最大的菜，使用reducing()，返回Dish
        System.out.println("热量最大的菜: " + findMostCaloricDishUsingComparator());
        //菜单列表的总热量(有collect方法)
        System.out.println("菜单的总热量，有collect方法: " + calculateTotalCalories());
        //菜单列表的总热量（不使用收集器collect）
        System.out.println("菜单的总热量，不使用收集器collect: " + totalCalories());
        //菜单列表的总热量（将流映射到IntStream中，最简单，最易读，）
        System.out.println("菜单的总热量，将流映射到IntStream中: " + totalCaloriesWithMap());
        //菜单列表的热量的平均值
        System.out.println("菜的平均热量: " + calculateAverageCalories());
        //汇总，总数，个数，最大，最小,平均值，summarizingInt()
        System.out.println("汇总菜单的各项指标: " + calculateMenuStatistics());
        //用空格连接字符串
        System.out.println("用空格连接菜单名: " + getShortMenu());
        //用逗号连接字符串
        System.out.println("用逗号连接菜单名: : " + getShortMenuCommaSeparated());
    }

    /**
     * 菜单里有多少种菜
     * @return
     */
    private static long howManyDishes() {
        return menu.stream().collect(counting());
    }

    /**
     * 菜单中热量最大的菜，使用reducing()，返回Dish
     * @return
     */
    private static Dish findMostCaloricDish() {
        return menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    /**
     * 菜单中热量最大的菜，使用reducing()，返回Dish
     * @return
     */
    private static Dish findMostCaloricDishUsingComparator() {
        // 先用预定义的收集器创建一个comparator
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        // 将收集器作为参数传入方法mayBy() TODO reducing方法介绍的参数BinaryOperator（二元运算 ）
        // 注意继承关系：BinaryOperator<T> extends BiFunction<T,T,T>
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        //
        return menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    /**
     * 菜单中热量最大的菜, 返回Optional<Dish>
     * @return
     */
    private static Optional<Dish> findMostCaloricDishUsingComparator1() {
        // 先用预定义的收集器创建一个comparator
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        return menu.stream().collect(maxBy(dishCaloriesComparator));
    }

    /**
     * 菜单列表的总热量
     * @return
     */
    private static int calculateTotalCalories() {
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    /**
     * 菜单列表的总热量, 不使用收集器collect()
     * @return
     */
    private static int totalCalories() {
        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    /**
     * 菜单列表的总热量, 不使用收集器collect(),将流映射到IntStream，最简单的写法
     * @return
     */
    private static int totalCaloriesWithMap() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }

    /**
     * 菜单列表的热量的平均值
     * @return
     */
    private static Double calculateAverageCalories() {
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    /**
     * 汇总，总数，个数，最大，最小,平均值，summarizingInt()
     * @return
     */
    private static IntSummaryStatistics calculateMenuStatistics() {
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    /**
     * 用连接菜的名称
     * @return
     */
    private static String getShortMenu() {
        // TODO map ?
        String shortMenu = menu.stream().map(Dish::getName).collect(joining( ));
        //连接字符串6.2.3
        //String shortMenu0 = menu.stream().collect(joining());
        //测验6.1，替代join：使用reducing方法，然后使用get方法提取值
        //第（1）种写法
        String shortMenu1 = menu.stream().map(Dish::getName).collect(reducing( (s1, s2) -> s1 + s2) ).get();
        //第（2）种写法，无法编译
        //String shortMenu2 = menu.stream().collect(reducing((d1, d2) -> d1.getName() + d2.getName())).get();
        //第（3）种写法, reduce()方法的三个参数的原始写法
        String shortMenu3 = menu.stream().collect(reducing("-", Dish::getName, (s1, s2) -> s1 + s2));
        //打印结果
        System.out.println("shortMenu1:" + shortMenu1);
        //System.out.println("shortMenu2:" + shortMenu2);
        System.out.println("shortMenu3:" + shortMenu3);
        return shortMenu1;
    }

    /**
     * 用逗号连接字符串
     * @return
     */
    private static String getShortMenuCommaSeparated() {
        // TODO map ?
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        return shortMenu;
    }




}
