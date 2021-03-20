import enums.DirEnum;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangtingfeng
 * @description
 * @date 2021/3/17/017
 */

public class TankFrame extends Frame {
    public final static int WIDTH = 1320, HEIGHT = 700;
    Tank tank = new Tank(200, 400, DirEnum.DOWM, this, Group.GOOD);
    List<Bullet> bulletList = new ArrayList<Bullet>();
    List<Tank> anemyTanks = new ArrayList<>();

    public TankFrame() {
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setResizable(false);
        setTitle("Tank item");
        // 串口监听事件
        this.addKeyListener(new MykeyListener());
        // 窗口关闭监听
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    // 处理界面闪烁问题
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(WIDTH, HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        tank.paint(g);
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bulletList.size(), 20, 60);
        g.drawString("敌方坦克的数量：" + anemyTanks.size(), 20, 80);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
        for (int i = 0; i < anemyTanks.size(); i++) {
            anemyTanks.get(i).paint(g);
        }
        // 子弹碰到坦克 子弹和坦克都消失
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < anemyTanks.size(); j++) {
                bulletList.get(i).touchTank(anemyTanks.get(j));
            }
        }
    }

    class MykeyListener extends KeyAdapter {
        // 设置一个判定用户按下了那个键
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }


        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bU && !bL && !bD && !bR) {
                tank.setMoving(false);
            } else {
                tank.setMoving(true);
                if (bL) {
                    tank.setDirEnum(DirEnum.LEFT);
                }
                if (bR) {
                    tank.setDirEnum(DirEnum.RIGHT);
                }
                if (bU) {
                    tank.setDirEnum(DirEnum.UP);
                }
                if (bD) {
                    tank.setDirEnum(DirEnum.DOWM);
                }
            }
        }
    }
}
