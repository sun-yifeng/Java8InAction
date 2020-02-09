package lambdasinaction.chap2;

import java.util.*;

/**
 * 筛选苹果，要两个参数，一个是库存，一个是筛选逻辑；
 * 第一种方案：颜色写死才方法中；
 * 第二种方案：把颜色作为参数；
 * 第三种方案：把颜色、重量等作为参数，方法会非常复杂；
 * 第四种方案：行为参数化，定义接口（谓词），作为参数，传递实现类实现test()方法；
 *           优点：多种行为，一个参数（接口）；
 *           缺点：要写不同的实现类，作为参数；
 * 第五种方案：用匿名类代理实现类（直接才参数中，new 一个接口实例）；
 * 第六种方案：lambda表达式
 * 第七中方案：将list类型抽象化（泛型类）
 *
 * */
public class FilteringApples{

	// 库存
	List<Apple> inventory = Arrays.asList(
			new Apple(80,"green"),
			new Apple(155, "green"),
			new Apple(120, "red"));

	// 第一种方案：颜色写死才方法中；
	public static List<Apple> filterGreenApples(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if("green".equals(apple.getColor())){
				result.add(apple);
			}
		}
		return result;
	}

	// 第二种方案：把颜色作为参数；
	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			// 颜色作为参数
			if(apple.getColor().equals(color)){
				result.add(apple);
			}
		}
		return result;
	}

	// 第三种方案：把颜色、重量等作为参数，方法会非常复杂；
	public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			// 颜色作为参数
			if((flag && apple.getColor().equals(color)) ||
				!flag && apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	/*************第四种方案（行为参数化）开始 ：定义接口（谓词），作为参数，传递实现类实现test()方法；******************/
	// 定义接口（以后几种方案都用到）
	interface ApplePredicate{
		public boolean test(Apple a);
	}
	// 超过150g的苹果
	static class AppleWeightPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return apple.getWeight() > 150;
		}
	}
	// 绿色的苹果
	static class AppleColorPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return "green".equals(apple.getColor());
		}
	}
	// 红色并且超高跟150克的苹果
	static class AppleRedAndHeavyPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return "red".equals(apple.getColor())
					&& apple.getWeight() > 150;
		}
	}
    // 通用方法（以后几种方案都用到）
	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}
	/*************第四种方案（行为参数化）结束 ：定义接口（谓词），作为参数，传递实现类实现test()方法；******************/

	/*************第五种方案(开始)：用匿名类代理实现类（直接才参数中，new 一个接口实例）； ****************************/
	List<Apple> reaApples = filter(inventory, new ApplePredicate() {
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor());
		}
	});

	/*************第六种方案(开始)：lambda表达式     *************************************************************/
	List<Apple> result = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));

	/*************第七中方案：将list类型抽象化（泛型类）  *********************************************************/
	// 定义接口
	public interface Predicate<T> {
		boolean test(T t);
	}
	// 过滤方法
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T e: list) {
			if (p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}
	// 筛选苹果
	List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
	// 筛选偶数
	List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
	List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);

	/******************************************** 以下是实体类 ***********************************************/
	// 苹果实体类
	public static class Apple {
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color){
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