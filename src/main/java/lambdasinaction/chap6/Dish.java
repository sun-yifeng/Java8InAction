package lambdasinaction.chap6;

import java.util.*;

import static java.util.Arrays.asList;

public class Dish {

    private final String name; // 名称
    private final boolean vegetarian; // 是否素菜
    private final int calories; // 热量
    private final Type type; // 种类

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    /**
     * 菜的种类枚举
     */
    public enum Type { MEAT, FISH, OTHER }

    @Override
    public String toString() {
        return name;
    }

    /**
     * 菜单列表
     */
    public static final List<Dish> menu =
            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                    new Dish("beef", false, 700, Dish.Type.MEAT),
                    new Dish("chicken", false, 400, Dish.Type.MEAT),
                    new Dish("frenchFries", true, 530, Dish.Type.OTHER),// 炸薯条
                    new Dish("rice", true, 350, Dish.Type.OTHER),
                    new Dish("seasonFruit", true, 120, Dish.Type.OTHER), // 时令水果
                    new Dish("pizza", true, 550, Dish.Type.OTHER),
                    new Dish("prawns", false, 400, Dish.Type.FISH), // 对虾
                    new Dish("salmon", false, 450, Dish.Type.FISH)); // 鲑鱼

    /**
     * 食物标签
     */
    public static final Map<String, List<String>> dishTags = new HashMap<>();

    static {
        dishTags.put("pork", asList("greasy", "salty")); // 油腻的, 咸咸的
        dishTags.put("beef", asList("salty", "roasted")); // 咸咸的, 烧烤的
        dishTags.put("chicken", asList("fried", "crisp")); // 油炸, 脆
        dishTags.put("french fries", asList("greasy", "fried")); // 油腻的, 油炸
        dishTags.put("rice", asList("light", "natural")); // 光亮，自然
        dishTags.put("season fruit", asList("fresh", "natural")); // 新鲜，自然
        dishTags.put("pizza", asList("tasty", "salty")); // 美味，咸味的
        dishTags.put("prawns", asList("tasty", "roasted")); // 美味，烧烤
        dishTags.put("salmon", asList("delicious", "fresh")); // 美味，新鲜
    }
}