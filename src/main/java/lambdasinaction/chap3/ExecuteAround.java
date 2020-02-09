package lambdasinaction.chap3;

import java.io.*;

/**
 * 环绕执行模式
 * 第一种方案：java7增强的try语句
 * 第二种方案：java8函数式写法
 */
public class ExecuteAround {

	/** 第一种方案：java7增强的try语句 */
    public static String processFileLimited() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("./target/classes/lambdasinaction/chap3/data.txt"))) { // 只能读一句
            return br.readLine();
        }
    }

    /** 第二种方案：java8函数式写法 */
	// 函数式接口
	public interface BufferedReaderProcessor{
		String process(BufferedReader b) throws IOException;
	}

	// 函数接口参数
	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("./target/classes/lambdasinaction/chap3/data.txt"))){
			return p.process(br);
		}

	}

	/** 调用以上两种方案 */
	public static void main(String ...args) throws IOException{
		//当前工作路径（vm调用路径）
		//System.out.println(System.getProperty("user.dir"));

		// method we want to refactor to make more flexible
		System.out.println("--java7的写法：输出一行的内容-----------------------------------------");
		String result = processFileLimited();
		System.out.println(result);

		System.out.println("--java8的写法：输出一行的内容-----------------------------------------");
		String oneLine = processFile((BufferedReader b) -> b.readLine());
		System.out.println(oneLine);

		System.out.println("--java8的写法：输出两行的内容（多输出了8）-------------------------------");
		String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
		System.out.println(twoLines);

	}
}
