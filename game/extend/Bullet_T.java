package tw.edu.ntut.csie.game.extend;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;


public class Bullet_T implements GameObject{
    private int x1, x2, y1, y2;

    private MovingBitmap bullet;
    private boolean isHit = false;

    public Bullet_T(int x, int y){
        x1 = x;
        y1 = y;

        bullet = new MovingBitmap(R.drawable.bullet_tower, x1 + 33, y1 + 27);
        bullet.resize(16, 16);
    }
    @Override
    public void move() {
        bullet.move();
    }

    @Override
    public void show() {
        bullet.show();

        if(isHit){
            bullet.setVisible(false);

        }
    }

    @Override
    public void release() {
        bullet.release();
        bullet = null;
    }

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

    public boolean getBulletHit(){
        return isHit;
    }

    public void setLocation(int x, int y){
        x1 = x;
        y1 = y;
        x2 = x1 + 16;
        y2 = y1 + 16;

        bullet.setLocation(x1, y1);
    }
    public void setBulletHit(boolean hit){
        isHit = hit;
    }
}
