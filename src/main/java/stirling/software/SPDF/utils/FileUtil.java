package stirling.software.SPDF.utils;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author：xp
 * @date：2024/6/16 15:51
 */
public class FileUtil {

    /**
     * 生成随机的10位文件名
     *
     * @param prefix
     * @return
     */
    public static String genRandomFileName(String prefix) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10); // 生成0到9之间的随机数
            sb.append(digit);
        }
        sb.append(".").append(prefix);
        return sb.toString();
    }

    /**
     * 创建临时文件
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static File mkdirTempFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    /**
     * 创建临时文件夹
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static File mkdirTempFileDir(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
