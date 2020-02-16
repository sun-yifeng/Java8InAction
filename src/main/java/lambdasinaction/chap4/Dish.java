package lambdasinaction.chap4;
import java.util.*;

/**
 * 菜肴
 * */
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    /** 菜称 */
    public String getName() {
        return name;
    }

    /** 是否为蔬菜 */
    public boolean isVegetarian() {
        return vegetarian;
    }

    /** 热量 */
    public int getCalories() {
        return calories;
    }

    /** 类型 */
    public Type getType() {
        return type;
    }

    /** 肉、鱼、其他 */
    public enum Type { MEAT, FISH, OTHER }

    @Override
    public String toString() {
        return name;
    }

    // 菜单（集合）
    public static final List<Dish> menu =
            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                           new Dish("beef", false, 700, Dish.Type.MEAT),
                           new Dish("chicken", false, 400, Dish.Type.MEAT),
                           new Dish("french fries", true, 530, Dish.Type.OTHER),
                           new Dish("rice", true, 350, Dish.Type.OTHER),
                           new Dish("season fruit", true, 120, Dish.Type.OTHER),
                           new Dish("pizza", true, 550, Dish.Type.OTHER),
                           new Dish("prawns", false, 400, Dish.Type.FISH),
                           new Dish("salmon", false, 450, Dish.Type.FISH));
}