package tw.edu.ntut.csie.game.extend;



import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.extend.GameMap;

public class Enemy implements GameObject {
    private int x1, x2, y1, y2;

    private boolean Trigger = false;
    private boolean isDied = false;
    int walkSpeed = 5;
    int HP = 8;



    boolean isTrigger = false;
    int index = 0;
    double fall_velocity = 0;
    double fall_acceleration = 1;

    private boolean haveBoom = false;
    private Animation boom;
    private Animation enemy_A;
    private MovingBitmap HP_white;
    private MovingBitmap HP_red;
    private MovingBitmap HP_gray;

    private GameMap _map = new GameMap();

    public Enemy(int x, int y){

        x1 = x;
        y1 = y;
        x2 = x1 + 29;
        y2 = y1 + 42;

        HP_white = new MovingBitmap(R.drawable.hp_white, x1 - 5, y1);
        HP_red = new MovingBitmap(R.drawable.hp_red, x1 - 4, y1 + 1);
        HP_gray = new MovingBitmap(R.drawable.hp_gray, x1 - 4, y1 + 1);

        enemy_A = new Animation();
        enemy_A.setLocation(x1, y1);
        enemy_A.addFrame(R.drawable.enemy_cu1);
        enemy_A.addFrame(R.drawable.enemy_cu2);
        enemy_A.addFrame(R.drawable.enemy_cu3);
        enemy_A.addFrame(R.drawable.enemy_cu4);
        enemy_A.addFrame(R.drawable.enemy_cu5);
        enemy_A.addFrame(R.drawable.enemy_cu6);
        enemy_A.addFrame(R.drawable.enemy_cu7);
        enemy_A.addFrame(R.drawable.enemy_cu8);
        enemy_A.addFrame(R.drawable.enemy_cu9);
        enemy_A.addFrame(R.drawable.enemy_cu10);

        boom = new Animation();
        boom.addFrame(R.drawable.boom1);
        boom.addFrame(R.drawable.boom2);
        boom.addFrame(R.drawable.boom3);
        boom.setDelay(3);
        boom.setVisible(false);
        boom.setRepeating(false);

    }
    @Override
    public void move(){
        enemy_A.move();
        boom.move();

        HP_red.move();
        HP_white.move();
        HP_gray.move();

        if(!isDied){
            if(x1 <= walkSpeed*2){
                isDied = true;
            }

            int passH = y2 +(int)fall_velocity;

            if(passH < 72) index = 5;
            else if(passH < 104) index = 0;
            else if(passH < 136) index = 1;
            else if(passH < 168) index = 2;
            else if(passH < 200) index = 3;
            else if(passH < 240) index = 4;
            else{
                isDied = true;
            }

            if(isTrigger){
                fall_velocity = 0;
                fall_acceleration = 1;

            }
            else{
                fall_velocity += fall_acceleration;
                fall_acceleration *= 1.12;
            }
            if(Trigger) {
                x1 = x1 - walkSpeed;
                x2 = x1 + 29;
            }
            y1 = y1 + (int)fall_velocity;
            y2 = y1 + 42;

            enemy_A.setLocation(x1, y1);

            HP_white.setLocation(x1 - 5, y1);
            HP_red.setLocation(x1 - 4, y1 + 1);
            HP_gray.setLocation(x1 - 4, y1 + 1);

        }

    }

    @Override
    public void show(){
        enemy_A.show();
        boom.show();

        HP_white.show();
        HP_gray.show();
        HP_red.resize(HP * 5, HP_red.getHeight());
        HP_red.show();

        if(isDied){
            enemy_A.setVisible(false);

            HP_white.setVisible(false);
            HP_gray.setVisible(false);
            HP_red.setVisible(false);

            Trigger = false;

            if(!haveBoom){
                boom.setLocation(enemy_A.getX() - 112, enemy_A.getY() - 83);
                boom.setVisible(true);
                haveBoom = true;
            }

        }

    }
    @Override
    public void release(){
        enemy_A.release();
        boom.release();

        HP_white.release();
        HP_red.release();
        HP_gray.release();

        _map.release();

        enemy_A = null;
        boom = null;

        HP_white = null;
        HP_red = null;
        HP_gray = null;

        _map = null;
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

    public boolean getState(){ return !Trigger;}

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2(){
        return x2;
    }

    public int getY2(){
        return y2;
    }

    public void setLocation(int x, int y){
        x1 = x;
        x2 = x1 + 29;
        y1 = y;
        y2 = y1 + 42;

        enemy_A.setLocation(x, y);
        boom.setLocation(x-112, y-83);
    }
    public void isTrigger(int x) {
        if(!isDied){
            if(x1 - x <= 656 && x1 - x > -656){
                Trigger = true;
            }
            else
                Trigger = false;
        }
    }

    public void Obj_trigger(GameMap map){
        isTrigger = map.Obj_trigger(x1, index)
                || map.Obj_trigger(x2, index);
    }
    public void Obj_trigger(GameMap2 map){
        isTrigger = map.Obj_trigger(x1, index)
                || map.Obj_trigger(x2, index);
    }
    public void Obj_trigger(GameMap3 map){
        isTrigger = map.Obj_trigger(x1, index)
                || map.Obj_trigger(x2, index);
    }
}
