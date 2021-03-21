import java.io.IOException;
import java.util.Properties;

/**
 * @author jiangtingfeng
 * @description
 * @date 2021/3/20/020
 */

public class PropertyMrg {

    private static class PropertyMrgHolder{
        private static final PropertyMrg INSTANCE = new PropertyMrg();
    }

    private PropertyMrg(){}

    private static PropertyMrg propertyMrg = new PropertyMrg();

    public static PropertyMrg getInstance() {
        return PropertyMrgHolder.INSTANCE;
    }

    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMrg.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object get(String key) {
        return properties.get(key);
    }

}
