/**
 * @author jiangtingfeng
 * @description  抽象出坦克的fire方法
 * @date 2021/3/21/021
 */
@FunctionalInterface
public interface FireStrategy {
    void fire(Tank tank);
}
