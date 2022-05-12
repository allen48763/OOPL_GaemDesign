package tw.edu.ntut.csie.game.state;

import android.graphics.Bitmap;

import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;

public class StateRun3 extends GameState{
    /**
     * 建構一個<code>GameState</code>實體。
     *
     * @param engine 執行狀態處理者的引擎
     */

    int bulletMAX = 10;

    private MovingBitmap[] bullet;
    private int[] bulletDir;
    private boolean[] bulletHit;
    int bulletCount;

    private MovingBitmap bullet2;
    private int bulletDir2;
    private boolean bulletHit2;
    int bulletCount2 = 0;


    BitmapButton[] M;
    public StateRun3(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data){
        bullet = new MovingBitmap[bulletMAX];
        bulletDir = new int[bulletMAX];
        bulletHit = new boolean[bulletMAX];
        bulletCount = 0;
        M = new BitmapButton[5];

        for(int i = 0; i < 5; i++){
            M[i] = new BitmapButton(R.drawable.button_d1, 100+i*50, 50);
        }
        for(int i = 0; i < bulletMAX; i++){
            bullet[i]= null;
        }
    }

    @Override
    public void move() {
        for(int i = 0; i < 5; i++){
            M[i].move();
        }
        for(int i = 0; i < bulletCount; i++){
            if(!bulletHit[i]){
                if(bulletDir[i] == 0){
                    bullet[i].move();
                    bullet[i].setLocation(bullet[i].getX() + 25, bullet[i].getY());
                }
                else if(bulletDir[i] == 1){
                    bullet[i].move();
                    bullet[i].setLocation(bullet[i].getX() - 25, bullet[i].getY());
                }
                else if(bulletDir[i] == 2){
                    bullet[i].move();
                    bullet[i].setLocation(bullet[i].getX(), bullet[i].getY() - 25);
                }
            }
        }
    }

    @Override
    public void show() {
        for(int i = 0; i < 5; i++){
            M[i].show();
        }

        for(int i = 0; i < bulletCount; i++){
            bullet[i].show();
        }
    }

    @Override
    public void keyPressed(int keyCode) {

    }

    @Override
    public void keyReleased(int keyCode) {

    }

    @Override
    public boolean pointerPressed(Pointer actionPointer, List<Pointer> pointers) {
        if(M[1].pointerPressed(actionPointer, pointers)||M[2].pointerPressed(actionPointer, pointers)||M[3].pointerPressed(actionPointer, pointers)||M[4].pointerPressed(actionPointer, pointers)){
            changeState(Game.INITIAL_STATE);
            return true;
        }
        if(M[0].pointerPressed(actionPointer, pointers)){
            makeBullet(10, 100, 0);
            return true;
        }
        return false;
    }

    @Override
    public boolean pointerMoved(Pointer actionPointer, List<Pointer> pointers) {
        return false;
    }

    @Override
    public boolean pointerReleased(Pointer actionPointer, List<Pointer> pointers) {
        return false;
    }

    @Override
    public void release() {
        for(int i = 0; i < 5; i++){
            M[i].release();
            M[i] = null;
        }

        for(int i = 0; i < bulletCount; i++){
            bullet[i].release();
            bullet[i]=null;
        }

    }

    @Override
    public void orientationChanged(float pitch, float azimuth, float roll) {

    }

    @Override
    public void accelerationChanged(float dX, float dY, float dZ) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void makeBullet(int x, int y, int dir) {
        if(dir == 0){
            x += 33;
            y += 27;
        }
        else if(dir == 1){
            x += -8;
            y += 27;
        }
        else if(dir == 2){
            x += 7;
            y += -6;
        }
        bullet[bulletCount] = new MovingBitmap(R.drawable.bullet, x, y);
        bulletDir[bulletCount] = dir;
        bulletHit[bulletCount] = false;
        bulletCount += 1;

    }
}
