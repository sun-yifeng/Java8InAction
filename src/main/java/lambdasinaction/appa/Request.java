package lambdasinaction.appa;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: lambdasinaction
 * @description: 泛型
 * @author: sunyf
 * @create: 2020-01-27 16:24
 **/
public class Request<T> {

    // 返回值为<T> T
    private <T> T getListFirst(List<T> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    // 返回值为T
    private T getListFirst2(List<T> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        // 入参由List<T>的T决定
        int a = new Request<String>().getListFirst(data);
        // 返回值为<T> T,所以入参不受Request<T>影响
        // new Request<String>().getListFirst2(data);//error,入参由request<T>的T决定,受Request<T>影响
        String aa = new Request<String>().getListFirst(data2);//没区别
        String bb = new Request<String>().getListFirst2(data2);//没区别
    }
}