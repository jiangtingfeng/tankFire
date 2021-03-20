import enums.DirEnum;

import java.awt.*;

/**
 * @author jiangtingfeng
 * @description
 * @date 2021/3/18/018
 */

public class Bullet {
    private static final int SPEED = 10;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private int x, y;
    private DirEnum dirEnum;
    private boolean isLive = true;
    private TankFrame tankFrame;
    private Group group;
    private Rectangle rectangle = new Rectangle(x, y, WIDTH, HEIGHT);

    public Bullet(int x, int y, DirEnum dirEnum, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dirEnum = dirEnum;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
        move();
    }

    private void move() {
        if (!isLive) {
            tankFrame.bulletList.remove(this);
        }
        switch (dirEnum) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWM:
                y += SPEED;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.WIDTH || y > TankFrame.HEIGHT) isLive = false;
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    public void touchTank(Tank tank) {
        if (this.group.equals(tank.getGroup())) {
            return;
        }
        // 子弹的矩阵
        if (this.rectangle.intersects(tank.getRectangle())) {
            this.die();
            tank.die();
        }
    }

    private void die() {
        isLive = false;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
