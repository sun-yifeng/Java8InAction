package lambdasinaction.chap5;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.*;
import java.nio.charset.Charset;
import java.nio.file.*;

/**
 * 5.7 构建流
 * 5.7.1 由值创建流
 * 5.7.2 数组创建流
 * 5.7.3 文件构建流
 * 5.7.4 iterate 函数创建无线流
 * */
public class BuildingStreams {

    public static void main(String...args) throws Exception{
        
        // 5.7.1 字符串创建流
        System.out.println("---------------------------------- 1.由值创建流 ------------------------------------");
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // 创建空流
        Stream<String> emptyStream = Stream.empty();

        // 5.7.2 数组创建流
        System.out.println("---------------------------------- 2.数组创建流 ------------------------------------");
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println("求和：" + Arrays.stream(numbers).sum());

        // 5.7.3 文件构建流
        System.out.println("---------------------------------- 3.文件创建流 ------------------------------------");
        long uniqueWords = Files.lines(Paths.get("./target/classes/lambdasinaction/chap5/data.txt"), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();

        // 5.7.4 iterate 函数创建无线流
        System.out.println("---------------------------------- 4.文件创建流 ------------------------------------");
        Stream.iterate(0, n -> n + 2)
              .limit(10)
              .forEach(System.out::println);

        // 5.7.4 iterate 函数创建无线流，斐波那契数，0,1开始，后面数是前面的两个数之和
        System.out.println("---------------------------------- 4.斐波那契元组数列 --------------------------------");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
              .limit(10)
              .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
        
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
              .limit(10)
              .map(t -> t[0])
              .forEach(System.out::println);

        // 5.7.4 generate 函数创建无线流
        Stream.generate(Math::random)
              .limit(10)
              .forEach(System.out::println);
 
        // stream of 1s with Stream.generate
        IntStream.generate(() -> 1)
                 .limit(5)
                 .forEach(System.out::println);
        // IntSupplier接口，匿名类（有状态）
        IntStream.generate(new IntSupplier(){
            public int getAsInt(){
                return 2;
            }
        }).limit(5)
          .forEach(System.out::println);

        // IntSupplier接口，匿名类（有状态）
        IntSupplier fib = new IntSupplier(){
                  private int previous = 0;
                  private int current = 1;
                  public int getAsInt(){
                      int nextValue = this.previous + this.current;
                      this.previous = this.current;
                      this.current = nextValue;
                      return this.previous;
                  }
              };
        IntStream.generate(fib).limit(10).forEach(System.out::println);



        System.out.println("There are " + uniqueWords + " unique words in data.txt");


    }
}
