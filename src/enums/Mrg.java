package enums;

import java.io.IOException;
import java.util.Properties;

/**
 * @author jiangtingfeng
 * @description
 * @date 2021/3/20/020
 */
public enum Mrg {
    INSTANCE;
    private static Properties properties = new Properties();
    static {
        try {
            properties.load(Mrg.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Object get(String key) {
        return properties.get(key);
    }
}
