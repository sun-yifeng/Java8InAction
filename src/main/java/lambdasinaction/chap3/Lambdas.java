package lambdasinaction.chap3;

import java.util.*;

/**
 * lambda表达式：需要在函数式接口中使用
 *
 */
public class Lambdas {

	// 调用程序
	public static void main(String ...args){
		/** java8中有效的lambda表达式 */
		/** 第1个例子，返回int，隐含了return */
		// (String s) -> s.length()
		/** 第2个例子，返回boolean */
		// (Apple a) -> a.getWeight() > 150;
		/** 第3个例子，有两个int参数，没有返回类型（void） */
		// (int x, int y) -> {
		//       System.out.println("Result");
		//      System.out.println(x + y);
	    //   }
		/** 第4个例子，没有参数，返回一个int */
		//() -> 42;
		/** 第5个例子，有两个参数，返回int*/
		//(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

	}

}