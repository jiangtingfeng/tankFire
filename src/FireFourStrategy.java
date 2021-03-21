import enums.DirEnum;

/**
 * @author jiangtingfeng
 * @description
 * @date 2021/3/21/021
 */
public class FireFourStrategy implements FireStrategy {

    private static class DefaultFireStatrtyHold {
        public static FireFourStrategy INSTANCE = new FireFourStrategy();
    }

    private FireFourStrategy() {
    }

    @Override
    public void fire(Tank tank) {
        DirEnum[] values = DirEnum.values();
        for (int i = 0; i < values.length; i++) {
            new Bullet(tank.getX() + Tank.width / 2 - 10, tank.getY() + Tank.height / 2 - 10, values[i], tank.tankFrame, tank.getGroup());
        }
    }

    public static FireFourStrategy getInstance() {
        return DefaultFireStatrtyHold.INSTANCE;
    }
}
