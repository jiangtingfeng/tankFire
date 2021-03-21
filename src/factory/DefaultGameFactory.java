package factory;

/**
 * @author jiangtingfeng
 * @description
 * @date 2021/3/21/021
 */

public class DefaultGameFactory extends AbstactGameFactory {
    @Override
    public AbstractTankFactory createTank() {
        return new DefaultTank();
    }

    @Override
    public AbstractBulletFactory createBullet() {
        return new DefaultBullet();
    }

    @Override
    public AbstractExeploseFactory createExeplose() {
        return new DefaultExeplose();
    }
}
