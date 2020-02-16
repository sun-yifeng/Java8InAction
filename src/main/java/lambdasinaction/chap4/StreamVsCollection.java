package lambdasinaction.chap4;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;


/**
 * 4.3 流与集合（区别）
 * 4.3.1 流只能遍历一次
 * */
public class StreamVsCollection {

    public static void main(String...args){
        List<String> names = Arrays.asList("Java", "8", "In", "Action");
        // 获取集合中的流
        Stream<String> s = names.stream();
        // 打印标题（书名）中的每个单词（打印用了方法引用）
        s.forEach(System.out::println);
        // 不能再次打印，流只能遍历一次（类似迭代器），要从原始数据源那里重新获取流
        //s.forEach(System.out::println);
    }
}