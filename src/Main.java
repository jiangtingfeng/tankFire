import enums.DirEnum;

public class Main {

    public static void main(String[] args) throws Exception {
        TankFrame tankFrame = new TankFrame();
        int initAnemys = Integer.parseInt((String) PropertyMrg.getInstance().get("initAnemys"));
        // 初始化敌方坦克
        for (int i = 0; i < initAnemys; i++) {
            tankFrame.anemyTanks.add(new Tank(50 + i * 80, 200, DirEnum.DOWM, tankFrame, Group.BAD));
        }
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
