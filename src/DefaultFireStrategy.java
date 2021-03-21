/**
 * @author jiangtingfeng
 * @description
 * @date 2021/3/21/021
 */
public class DefaultFireStrategy implements FireStrategy {

    private static class DefaultFireStatrtyHold {
        public static DefaultFireStrategy INSTANCE = new DefaultFireStrategy();
    }

    private DefaultFireStrategy() {
    }

    @Override
    public void fire(Tank tank) {
        new Bullet(tank.getX() + Tank.width / 2 - 10, tank.getY() + Tank.height / 2 - 10, tank.getDirEnum(), tank.tankFrame, tank.getGroup());
    }

    public static DefaultFireStrategy getInstance() {
        return DefaultFireStatrtyHold.INSTANCE;
    }
}
