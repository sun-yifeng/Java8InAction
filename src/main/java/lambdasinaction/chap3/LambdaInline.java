package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * lambda表达式：需要在函数式接口中使用
 *
 */
public class LambdaInline {

	// 通用方法
	public static void process(Runnable r) {
		r.run();
	}

	// 调用程序
	public static void main(String ...args){
		// 内联的形式
		Runnable r1 = () -> System.out.println("hello 1");
		// 匿名类
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("hello 2");
			}
		};

		// 调用程序
		process(r1);
		process(r2);
		// 直接传递lambda
		process(() -> System.out.println("hello 3"));
	}

}