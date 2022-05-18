package tw.edu.ntut.csie.game.extend;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.extend.GameMap;

public class Enemy2 implements GameObject {

    private int x1, x2, y1, y2;
    private boolean isDied = false;
    int walkSpeed = 5;
    int HP = 4;
    private boolean Trigger = false;

    private boolean haveFire = false;
    private Animation fire;

    private Animation enemy_B1;
    private MovingBitmap enemy_B2;

    private MovingBitmap HP_white;
    private MovingBitmap HP_red;
    private MovingBitmap HP_gray;

    int bulletMAX = 10;
    private Bullet_T[] bullet = new Bullet_T[bulletMAX];

    private int[] bulletDirX = new int[bulletMAX];
    private int[] bulletDirY = new int[bulletMAX];
    int bulletCount = 0;

    public Enemy2(int x, int y){
        x1 = x;
        y1 = y;
        x2 = x1 + 71;
        y2 = y1 + 104;

        HP_white = new MovingBitmap(R.drawable.hp_white, x1+16, y1-1);
        HP_red = new MovingBitmap(R.drawable.hp_red, x1+17, y1);
        HP_gray = new MovingBitmap(R.drawable.hp_gray, x1+17, y1);

        enemy_B1 = new Animation();
        enemy_B1.setLocation(x1-4, y1-29);
        enemy_B1.addFrame(R.drawable.plasma_b_1);
        enemy_B1.addFrame(R.drawable.plasma_b_2);
        enemy_B1.addFrame(R.drawable.plasma_b_3);
        enemy_B1.addFrame(R.drawable.plasma_b_4);

        enemy_B2 = new MovingBitmap(R.drawable.tower, x1, y1);

        fire = new Animation();
        fire.addFrame(R.drawable.fire1);
        fire.addFrame(R.drawable.fire2);
        fire.addFrame(R.drawable.fire3);
        fire.addFrame(R.drawable.fire4);
        fire.addFrame(R.drawable.fire5);
        fire.addFrame(R.drawable.fire6);
        fire.addFrame(R.drawable.fire7);
        fire.addFrame(R.drawable.fire8);
        fire.addFrame(R.drawable.fire7);
        fire.addFrame(R.drawable.fire8);
        fire.addFrame(R.drawable.fire7);
        fire.addFrame(R.drawable.fire8);

        fire.setVisible(false);
        fire.setRepeating(false);
    }
    @Override
    public void move(){
        enemy_B1.move();
        enemy_B2.move();
        fire.move();

        HP_red.move();
        HP_white.move();
        HP_gray.move();

        for(int i = 0; i < bulletCount; i++){
            if(!bullet[i].getBulletHit()){
                bullet[i].move();
                bullet[i].setLocation(bullet[i].getX1() + bulletDirX[i], bullet[i].getY1() + bulletDirY[i]);
            }
        }

    }
    @Override
    public void show(){
        enemy_B2.show();
        enemy_B1.show();
        fire.show();

        HP_white.show();
        HP_gray.show();
        HP_red.resize(HP * 10, HP_red.getHeight());
        HP_red.show();

        for(int i = 0; i < bulletCount; i++){
            if(bullet[i] != null){
                bullet[i].show();
            }
        }
        if(isDied){
            enemy_B1.setVisible(false);
            enemy_B2.setVisible(false);

            HP_white.setVisible(false);
            HP_gray.setVisible(false);
            HP_red.setVisible(false);
            Trigger = false;

            if(!haveFire){
                fire.setLocation(enemy_B2.getX(), enemy_B2.getY());
                fire.setVisible(true);
                haveFire = true;
            }
            if(Trigger) {
                x1 = x1 - walkSpeed;
                x2 = x1 + 29;
            }
        }
    }
    @Override
    public void release(){
        enemy_B1.release();
        enemy_B2.release();
        fire.release();

        HP_white.release();
        HP_red.release();
        HP_gray.release();

        for(int i = 0; i < bulletCount; i++){
            bullet[i].release();
        }


        enemy_B1 = null;
        enemy_B2 = null;
        fire = null;

        HP_white = null;
        HP_red = null;
        HP_gray = null;

        for(int i = 0; i < bulletCount; i++){
            bullet[i] = null;
        }
    }

    public boolean isCollision(MovingBitmap bullet){
        int bullet_x1 = bullet.getX();
        int bullet_x2 = bullet.getX() + bullet.getWidth();
        int bullet_y1 = bullet.getY();
        int bullet_y2 = bullet.getY() + bullet.getHeight();

        if(     (bullet_x1 < x1&&bullet_y1 < y1&&bullet_x2 > x1&&bullet_y2 > y1)||
                (bullet_x1 < x1&&bullet_y1 < y2&&bullet_x2 > x1&&bullet_y2 > y2)||
                (bullet_x1 < x2&&bullet_y1 < y1&&bullet_x2 > x2&&bullet_y2 > y1)||
                (bullet_x1 < x2&&bullet_y1 < y2&&bullet_x2 > x2&&bullet_y2 > y2)||
                (bullet_x1 >= x1&&bullet_y1 <= y1&&bullet_x2 <= x2&&bullet_y2 >= y1)||
                (bullet_x1 >= x1&&bullet_y1 <= y2&&bullet_x2 <= x2&&bullet_y2 >= y2)||
                (bullet_x1 <= x1&&bullet_y1 >= y1&&bullet_x2 >= x1&&bullet_y2 <= y2)||
                (bullet_x1 <= x2&&bullet_y1 >= y1&&bullet_x2 >= x2&&bullet_y2 <= y2)||
                (bullet_x1 > x1&&bullet_y1 > y1&&bullet_x2 < x2&&bullet_y2 < y2)){
            HP -= 1;
            if(HP <= 0){
                isDied = true;
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isMax(){
        if(bulletCount != bulletMAX)
            return false;
        else
            return true;
    }

    public boolean getState(){ return !Trigger;}

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getBulletCount() {
        return bulletCount;
    }

    public Bullet_T getBullet(int i){
        return bullet[i];
    }

    public boolean getBulletHit(int i){
        return bullet[i].getBulletHit();
    }
    public void setLocation(int x, int y){
        x1 = x;
        y1 = y;
        enemy_B1.setLocation(x-4, y-29);
        enemy_B2.setLocation(x, y);
        fire.setLocation(x, y);

        HP_white.setLocation(x+16, y-1);
        HP_gray.setLocation(x+17,y);
        HP_red.setLocation(x+17,y);
    }
    public void isTrigger(int x) {
        if(!isDied){
            if(x1 - x <= 656 && x1 - x > -250){
                Trigger = true;
            }
            else
                Trigger = false;
        }
    }
    public void makeBullet(int x, int y){

        bullet[bulletCount] = new Bullet_T( x1 + 33, y1 + 27);

        bulletDirX[bulletCount] = (int)(10 * (x - x1) / (Math.sqrt(Math.pow(x - x1, 2)+Math.pow(y - y1, 2))));
        bulletDirY[bulletCount] = (int)(10 * (y - y1) / (Math.sqrt(Math.pow(x - x1, 2)+Math.pow(y - y1, 2))));
        bulletCount += 1;
    }


}
