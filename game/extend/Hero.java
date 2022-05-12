package tw.edu.ntut.csie.game.extend;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.extend.GameMap;

public class Hero implements GameObject{
    private Animation people_idle_r;
    private Animation people_run_r;
    private Animation people_roll_r;
    private Animation people_getDown_r;
    private Animation people_runShoot_r;
    private Animation people_upShoot_r;
    private Animation people_died_r;

    private Animation people_idle_l;
    private Animation people_run_l;
    private Animation people_roll_l;
    private Animation people_getDown_l;
    private Animation people_runShoot_l;
    private Animation people_upShoot_l;
    private Animation people_died_l;

    int px, py;
    int height, width;

    public Hero(int x, int y){
        px = x;
        py = y;

        people_run_r = new Animation();
        people_run_r.setLocation(px, py+7);
        people_run_r.addFrame(R.drawable.people_run1_r);
        people_run_r.addFrame(R.drawable.people_run2_r);
        people_run_r.addFrame(R.drawable.people_run3_r);
        people_run_r.addFrame(R.drawable.people_run4_r);
        people_run_r.addFrame(R.drawable.people_run5_r);
        people_run_r.addFrame(R.drawable.people_run6_r);
        people_run_r.setDelay(2);

        people_roll_r = new Animation();
        people_roll_r.setLocation(px,py+23);
        people_roll_r.addFrame(R.drawable.people_roll1_r);
        people_roll_r.addFrame(R.drawable.people_roll2_r);
        people_roll_r.addFrame(R.drawable.people_roll3_r);
        people_roll_r.addFrame(R.drawable.people_roll4_r);
        people_roll_r.setDelay(2);

        people_idle_r = new Animation();
        people_idle_r.setLocation(px,py);
        people_idle_r.addFrame(R.drawable.people_idle1_r);
        people_idle_r.addFrame(R.drawable.people_idle2_r);
        people_idle_r.setDelay(2);

        people_runShoot_r = new Animation();
        people_runShoot_r.setLocation(px,py+9);
        people_runShoot_r.addFrame(R.drawable.people_run_sh1_r);
        people_runShoot_r.addFrame(R.drawable.people_run_sh2_r);
        people_runShoot_r.addFrame(R.drawable.people_run_sh3_r);
        people_runShoot_r.setDelay(2);

        people_getDown_r = new Animation();
        people_getDown_r.setLocation(px,py+26);
        people_getDown_r.addFrame(R.drawable.people_getdown_sh1_r);
        people_getDown_r.addFrame(R.drawable.people_getdown_sh2_r);
        people_getDown_r.setDelay(2);

        people_upShoot_r = new Animation();
        people_upShoot_r.setLocation(px, py);
        people_upShoot_r.addFrame(R.drawable.people_up_sh1_r);
        people_upShoot_r.addFrame(R.drawable.people_up_sh2_r);
        people_upShoot_r.setDelay(2);

        people_died_r = new Animation();
        people_died_r.setLocation(px, py);
        people_died_r.addFrame(R.drawable.people_de1_r);
        people_died_r.addFrame(R.drawable.people_de2_r);
        people_died_r.addFrame(R.drawable.people_de3_r);
        people_died_r.addFrame(R.drawable.people_de4_r);
        people_died_r.addFrame(R.drawable.people_de5_r);
        people_died_r.addFrame(R.drawable.people_de5_r);
        people_died_r.addFrame(R.drawable.people_de5_r);
        people_died_r.addFrame(R.drawable.people_de5_l);
        people_died_r.setDelay(2);

        people_run_l = new Animation();
        people_run_l.setLocation(px, py+7);
        people_run_l.addFrame(R.drawable.people_run1_l);
        people_run_l.addFrame(R.drawable.people_run2_l);
        people_run_l.addFrame(R.drawable.people_run3_l);
        people_run_l.addFrame(R.drawable.people_run4_l);
        people_run_l.addFrame(R.drawable.people_run5_l);
        people_run_l.addFrame(R.drawable.people_run6_l);
        people_run_l.setDelay(2);

        people_roll_l = new Animation();
        people_roll_l.setLocation(px,py+23);
        people_roll_l.addFrame(R.drawable.people_roll1_l);
        people_roll_l.addFrame(R.drawable.people_roll2_l);
        people_roll_l.addFrame(R.drawable.people_roll3_l);
        people_roll_l.addFrame(R.drawable.people_roll4_l);
        people_roll_l.setDelay(2);

        people_idle_l = new Animation();
        people_idle_l.setLocation(px,py);
        people_idle_l.addFrame(R.drawable.people_idle1_l);
        people_idle_l.addFrame(R.drawable.people_idle2_l);
        people_idle_l.setDelay(2);

        people_runShoot_l = new Animation();
        people_runShoot_l.setLocation(px,py+9);
        people_runShoot_l.addFrame(R.drawable.people_run_sh1_l);
        people_runShoot_l.addFrame(R.drawable.people_run_sh2_l);
        people_runShoot_l.addFrame(R.drawable.people_run_sh3_l);
        people_runShoot_l.setDelay(2);

        people_getDown_l = new Animation();
        people_getDown_l.setLocation(px,py+26);
        people_getDown_l.addFrame(R.drawable.people_getdown_sh1_l);
        people_getDown_l.addFrame(R.drawable.people_getdown_sh2_l);
        people_getDown_l.setDelay(2);

        people_upShoot_l = new Animation();
        people_upShoot_l.setLocation(px, py);
        people_upShoot_l.addFrame(R.drawable.people_up_sh1_l);
        people_upShoot_l.addFrame(R.drawable.people_up_sh2_l);
        people_upShoot_l.setDelay(2);

        people_died_l = new Animation();
        people_died_l.setLocation(px, py);
        people_died_l.addFrame(R.drawable.people_de1_l);
        people_died_l.addFrame(R.drawable.people_de2_l);
        people_died_l.addFrame(R.drawable.people_de3_l);
        people_died_l.addFrame(R.drawable.people_de4_l);
        people_died_l.addFrame(R.drawable.people_de5_l);
        people_died_l.addFrame(R.drawable.people_de5_l);
        people_died_l.addFrame(R.drawable.people_de5_l);
        people_died_l.addFrame(R.drawable.people_de5_l);
        people_died_l.setDelay(2);

        height = people_idle_r.getHeight();
        width = people_idle_r.getWidth();
    }

    @Override
    public void move() {
        people_run_r.move();
        people_roll_r.move();
        people_getDown_r.move();
        people_idle_r.move();
        people_runShoot_r.move();
        people_upShoot_r.move();
        people_died_r.move();

        people_run_l.move();
        people_roll_l.move();
        people_getDown_l.move();
        people_idle_l.move();
        people_runShoot_l.move();
        people_upShoot_l.move();
        people_died_l.move();
    }

    @Override
    public void show() {
        people_run_r.show();
        people_roll_r.show();
        people_runShoot_r.show();
        people_idle_r.show();
        people_getDown_r.show();
        people_upShoot_r.show();
        people_died_r.show();

        people_run_l.show();
        people_roll_l.show();
        people_runShoot_l.show();
        people_idle_l.show();
        people_getDown_l.show();
        people_upShoot_l.show();
        people_died_l.show();
    }

    @Override
    public void release() {
        people_run_r.release();
        people_roll_r.release();
        people_runShoot_r.release();
        people_idle_r.release();
        people_getDown_r.release();
        people_upShoot_r.release();
        people_died_r.release();

        people_run_l.release();
        people_roll_l.release();
        people_runShoot_l.release();
        people_idle_l.release();
        people_getDown_l.release();
        people_upShoot_l.release();
        people_died_l.release();

        people_run_r = null;
        people_roll_r = null;
        people_runShoot_r = null;
        people_idle_r = null;
        people_getDown_r = null;
        people_upShoot_r = null;
        people_died_r = null;

        people_run_l = null;
        people_roll_l = null;
        people_runShoot_l = null;
        people_idle_l = null;
        people_getDown_l = null;
        people_upShoot_l = null;
        people_died_l = null;
    }
    public void setLocation(int x, int y){
        px = x;
        py = y;

        people_run_r.setLocation(px, py+7);
        people_roll_r.setLocation(px, py+23);
        people_idle_r.setLocation(px, py);
        people_runShoot_r.setLocation(px, py+9);
        people_getDown_r.setLocation(px, py+26);
        people_upShoot_r.setLocation(px, py);
        people_died_r.setLocation(px, py);

        people_run_l.setLocation(px, py+7);
        people_roll_l.setLocation(px, py+23);
        people_idle_l.setLocation(px, py);
        people_runShoot_l.setLocation(px, py+9);
        people_getDown_l.setLocation(px, py+26);
        people_upShoot_l.setLocation(px, py);
        people_died_l.setLocation(px, py);

    }
    public void setLocationN(int x, int y){
        px = x;
        py = y-people_idle_r.getHeight();

        people_run_r.setLocation(px, y-people_run_r.getHeight());
        people_idle_r.setLocation(px, y-people_idle_r.getHeight());
        people_runShoot_r.setLocation(px, y-people_runShoot_r.getHeight());
        people_roll_r.setLocation(px, y-people_roll_r.getHeight());
        people_getDown_r.setLocation(px, y-people_getDown_r.getHeight());
        people_upShoot_r.setLocation(px, y-people_upShoot_r.getHeight());
        people_died_r.setLocation(px, y-people_died_r.getHeight());

        people_run_l.setLocation(px, y-people_run_l.getHeight());
        people_idle_l.setLocation(px, y-people_idle_l.getHeight());
        people_runShoot_l.setLocation(px, y-people_runShoot_l.getHeight());
        people_roll_l.setLocation(px, y-people_roll_l.getHeight());
        people_getDown_l.setLocation(px, y-people_getDown_l.getHeight());
        people_upShoot_l.setLocation(px, y-people_upShoot_l.getHeight());
        people_died_l.setLocation(px, y-people_died_l.getHeight());
    }

    public int getX(){
        return px;
    }

    public int getY(int mode){
        if(mode == 1) return py+7;
        else if(mode == 2) return py+9;
        else if(mode == 3) return py;
        else if(mode == 4) return py+26;
        else if(mode == 5) return py+23;
        else if(mode == 6) return py+7;
        else if(mode == 7) return py+9;
        else if(mode == 8) return py;
        else if(mode == 9) return py+26;
        else if(mode == 10) return py+23;
        else if(mode == 11) return py;
        else if(mode == 12) return py;
        else if(mode == 13) return py;
        else return py;

    }

    public int getHeight(int mode){
        if(mode == 1) return people_run_r.getHeight();
        else if(mode == 2) return people_runShoot_r.getHeight();
        else if(mode == 3) return people_idle_r.getHeight();
        else if(mode == 4) return people_getDown_r.getHeight();
        else if(mode == 5) return people_roll_r.getHeight();
        else if(mode == 6) return people_run_l.getHeight();
        else if(mode == 7) return people_runShoot_l.getHeight();
        else if(mode == 8) return people_idle_l.getHeight();
        else if(mode == 9) return people_getDown_l.getHeight();
        else if(mode == 10) return people_roll_l.getHeight();
        else if(mode == 11) return people_upShoot_r.getHeight();
        else if(mode == 12) return people_upShoot_l.getHeight();
        else if(mode == 13) return people_died_r.getHeight();
        else return people_died_l.getHeight();
    }

    public int getWidth(int mode){
        if(mode == 1) return people_run_r.getWidth();
        else if(mode == 2) return people_runShoot_r.getWidth();
        else if(mode == 3) return people_idle_r.getWidth();
        else if(mode == 4) return people_getDown_r.getWidth();
        else if(mode == 5) return people_roll_r.getWidth();
        else if(mode == 6) return people_run_l.getWidth();
        else if(mode == 7) return people_runShoot_l.getWidth();
        else if(mode == 8) return people_idle_l.getWidth();
        else if(mode == 9) return people_getDown_l.getWidth();
        else if(mode == 10) return people_roll_l.getWidth();
        else if(mode == 11) return people_upShoot_r.getWidth();
        else if(mode == 12) return people_upShoot_l.getWidth();
        else if(mode == 13) return people_died_r.getWidth();
        else return people_died_l.getWidth();
    }

    public void setVisible(int x){
        people_run_r.setVisible(x == 1);
        people_runShoot_r.setVisible(x == 2);
        people_idle_r.setVisible(x == 3);
        people_getDown_r.setVisible(x == 4);
        people_roll_r.setVisible(x == 5);

        people_run_l.setVisible(x == 6);
        people_runShoot_l.setVisible(x == 7);
        people_idle_l.setVisible(x == 8);
        people_getDown_l.setVisible(x == 9);
        people_roll_l.setVisible(x == 10);

        people_upShoot_r.setVisible(x == 11);
        people_upShoot_l.setVisible(x == 12);

        people_died_r.setVisible(x == 13);
        people_died_l.setVisible(x == 14);

    }
    public boolean isEndDied(int mode){
        if(mode == 13){
            if(people_died_r.isLastFrame()){
                people_died_r.setCurrentFrameIndex(0);
                return true;
            }
            else
                return false;
        }
        else{
            if(people_died_l.isLastFrame()){
                people_died_l.setCurrentFrameIndex(0);
                return true;
            }
            else
                return false;
        }
    }
}
