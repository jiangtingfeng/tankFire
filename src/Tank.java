import enums.DirEnum;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jiangtingfeng
 * @description 坦克
 * @date 2021/3/18/018
 */

public class Tank {
    private int x, y;
    private DirEnum dirEnum = DirEnum.DOWM;
    private static final int SPEED = 5;
    private boolean moving = true;
    public TankFrame tankFrame;
    public static final int width = 50, height = 50;
    private boolean isLive = true;
    private Group group;
    Random random = new Random();
    private Rectangle rectangle = new Rectangle(x, y, width, height);
    List<DirEnum> dirEnumList = new ArrayList<DirEnum>();

    public static int getSPEED() {
        return SPEED;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, DirEnum dirEnum, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dirEnum = dirEnum;
        this.tankFrame = tankFrame;
        this.group = group;
        dirEnumList.add(DirEnum.DOWM);
        dirEnumList.add(DirEnum.UP);
        dirEnumList.add(DirEnum.LEFT);
        dirEnumList.add(DirEnum.RIGHT);
    }

    // 坦克画自己
    public void paint(Graphics g) {
        if (!isLive) {
            tankFrame.anemyTanks.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);
        move();
    }

    private void move() {
        if (!moving) {
            return;
        }
        if (this.group.equals(Group.BAD)) {
            if (random.nextInt(10) > 8) {
                this.dirEnum = getRandomDirEnum();
            }
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
        // 敌方随机打子弹
        if (random.nextInt(10) > 8 && this.group.equals(Group.BAD)) {
            this.fire(DefaultFireStrategy.getInstance());
        }
        // 边界检测
        boundCheck();
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void boundCheck() {
        if (this.x < 0) {
            x = 0;
        }
        if (this.y < 25) {
            y = 25;
        }
        if (this.x > TankFrame.WIDTH - Tank.width) {
            x = TankFrame.WIDTH - Tank.width;
        }
        if (this.y > TankFrame.HEIGHT - Tank.height) {
            y = TankFrame.HEIGHT - Tank.height;
        }
    }

    private DirEnum getRandomDirEnum() {
        int index = random.nextInt(100) % 4;
        return dirEnumList.get(index);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirEnum(DirEnum dirEnum) {
        this.dirEnum = dirEnum;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public DirEnum getDirEnum() {
        return dirEnum;
    }

    public void fire(FireStrategy fireStrategy) {
        fireStrategy.fire(this);
    }

    public void die() {
        this.isLive = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
