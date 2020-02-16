package lambdasinaction.chap5;

import lambdasinaction.chap4.*;

import java.util.*;
import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap4.Dish.menu;

/**
 * 5.2 映射
 *  map()：映射
 *  flatMap():把流中的每一个值都转换为一个流，然后把所有的流连接起来成一个流
 * */
public class Mapping{

    public static void main(String...args){

        // map
        System.out.println("------------------映射方法map()，返回菜单名称（列表形式）-------------------");
        List<String> dishNames = menu.stream()
                                     .map(Dish::getName)
                                     .collect(toList());
        System.out.println(dishNames);

        // map
        System.out.println("------------------映射方法map()，返回单词长度（列表形式）-------------------");
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        // flatMap
        System.out.println("------------------映射方法flatMap()，扁平化返回字母列表-------------------");
        words.stream()
                // 第1种写法
                //.map(w -> w.split(""))
                //.flatMap(Arrays::stream)
                // 第2种写法
                .flatMap((String w) -> Arrays.stream(w.split("")))
                .distinct()
                .forEach(System.out::println);

        // flatMap
        System.out.println("------------------映射方法map()，返回单两个数字列表的对应关系--------------");
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                              .map(j -> new int[]{i, j})
                                 )
                                // 返回能被3整除的数对
                                //.filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
