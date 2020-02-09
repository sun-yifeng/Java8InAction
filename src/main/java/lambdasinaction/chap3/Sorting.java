package lambdasinaction.chap3;

import java.util.*;
import static java.util.Comparator.comparing;

/**
 * 用不同的排序策略给apple排序
 * 第一种方案：向java8的sort方法提供排序策略(实现类);
 * 第二种方案：使用匿名类实现排序策略;
 * 第三种方案：使用lambda表达式实现排序策略;
 * 第四种方案：使用方法引用实现（特定方法的lambda表达式的一种简写）
 */
public class Sorting {

    /** 第一种方案：向java8的sort方法提供排序策略（实现类），排序策略如下 */
    static class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2){
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

    public static void main(String...args){

        // 苹果库存
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80,"green"),
                                       new Apple(155, "green"),
                                       new Apple(120, "red")));

        /** 第一种方案：向java8的sort方法提供排序策略, 排序策略如上 */
        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(30, "green"));
        
        /** 第二种方案：使用匿名类实现排序策略 */
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2){
                return a1.getWeight().compareTo(a2.getWeight()); 
        }});
        System.out.println(inventory);
        // reshuffling things a little
        inventory.set(1, new Apple(20, "red"));
        
        /** 第三种方案：使用lambda表达式实现排序策略 */
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);
        // reshuffling things a little
        inventory.set(1, new Apple(10, "red"));

        /** 第四种方案：使用方法引用实现（特定方法的lambda表达式的简写,如下的comparing()方法是Comparator接口的静态方法） */
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);       
    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                   "color='" + color + '\'' +
                   ", weight=" + weight +
                   '}';
        }
    }


}
