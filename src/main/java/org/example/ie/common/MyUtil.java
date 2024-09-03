package org.example.ie.common;
import java.util.Random;

public class MyUtil {
    /**
     * 将实际的文件名重命名
     */
    public static String getNewFileName(String oldFileName) {
        int lastIndex = oldFileName.lastIndexOf(".");
        String fileType = oldFileName.substring(lastIndex);
        // 生成 Random 对象
        Random random = new Random();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            // 生成 0-9 随机整数
            str = str.append(random.nextInt(10));
        }
        String newFileName = str + fileType;
        return newFileName;
    }
}
