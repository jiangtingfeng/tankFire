package factory;

/**
 * @author jiangtingfeng
 * @description  抽象产品族
 * @date 2021/3/21/021
 */
public abstract class AbstactGameFactory {

    public abstract AbstractTankFactory createTank();
    public abstract AbstractBulletFactory createBullet();
    public abstract AbstractExeploseFactory createExeplose();

}
