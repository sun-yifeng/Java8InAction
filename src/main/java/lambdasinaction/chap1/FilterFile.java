package lambdasinaction.chap1;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileFilter;

public class FilterFile {

    // java8之前，筛选隐藏文件的老方法
    File[] hiddenFiles = new File("/").listFiles(new FileFilter() {
        @Override
        public boolean accept(File file) {
            return file.isHidden();
        }
    });

    // java8之后，使用方法引用
    File[] hiddenFiles1 = new File("/").listFiles(File::isHidden);

    public static void main(String[] args) {
        FilterFile ff = new FilterFile();
        File[] arr = ff.hiddenFiles1;
        for (File f: arr) {
            System.out.println(f.getName());
        }
        System.out.println("-----");
    }


}
