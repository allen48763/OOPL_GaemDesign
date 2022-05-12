package tw.edu.ntut.csie.game.state;

import static tw.edu.ntut.csie.game.Game.GAME_FRAME_HEIGHT;
import static tw.edu.ntut.csie.game.Game.GAME_FRAME_WIDTH;
import static tw.edu.ntut.csie.game.Game.OVER_STATE;

import android.graphics.Bitmap;
import android.support.annotation.AnimRes;
import android.text.method.MovementMethod;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.BuildConfig;
import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.Bullet_T;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;
import tw.edu.ntut.csie.game.extend.Enemy2;
import tw.edu.ntut.csie.game.extend.Enemy3;
import tw.edu.ntut.csie.game.extend.GameMap;
import tw.edu.ntut.csie.game.extend.Hero;
import tw.edu.ntut.csie.game.extend.Integer;
import tw.edu.ntut.csie.game.extend.Enemy;

public class StateRun extends GameState {
    public static final int DEFAULT_SCORE_DIGITS = 4;
    double fall_velocity;
    double fall_acceleration;
    private MovingBitmap[] hero_hp;

    private int hp;
    private int Invincible;
    private boolean Dieding = false;

    private boolean keepPressW = false;
    private boolean keepPressA = false;
    private boolean keepPressS = false;
    private boolean keepPressD = false;
    private boolean keepPressSh = false;

    int runSpeed = 7;

    boolean isTrigger = false;

    boolean jump = true;
    boolean keepJump = false;
    boolean isRight = true;
    boolean collisionToR = true;
    private int[] x = {-8,-8,-5,-5,-2,-2,-1,0};
    private int[] y = {72,104,136,168,200,0};

    int i;
    int time;
    int index;
    private MovingBitmap _background;

    int bulletMAX = 1000;
    private MovingBitmap[] bullet;

    private int[] bulletDir;
    private boolean[] bulletHit;
    int bulletCount;

    private BitmapButton ButtonW;
    private BitmapButton ButtonA;
    private BitmapButton ButtonS;
    private BitmapButton ButtonD;
    private BitmapButton ButtonSh;
    private BitmapButton ButtonJump;

    private Hero player1;
    private int HeroState;

    int boobMax = 20;
    private Enemy[] boob1;

    int towerMax = 1;
    private Enemy2[] tower;

    int ufoMax = 10;
    private Enemy3[] ufo;

    private Integer _scores;
    private GameMap _map;
    private Audio _music;

    private Pointer _pointer1;
    private Pointer _pointer2;

    int px = 200, py = -100;
    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {

        fall_velocity = 0;
        fall_acceleration = 1;

        hero_hp = new MovingBitmap[5];
        bullet = new MovingBitmap[bulletMAX];
        bulletDir = new int[bulletMAX];
        bulletHit = new boolean[bulletMAX];

        boob1 = new Enemy[boobMax];
        tower = new Enemy2[towerMax];
        ufo = new Enemy3[ufoMax];
        _map = new GameMap();

        hp = 5;
        Invincible = 160;
        bulletCount = 0;

        i = 0;
        time = 0;
        index = 0;

        for(int i = 0; i < boobMax; i++){
            boob1[i] = new Enemy(500+i*100,0);
        }
        for(int i = 0; i < towerMax; i++){
            tower[i] = new Enemy2(427+i*150, 1);
        }
        for(int i = 0; i < ufoMax; i++){
            ufo[i] = new Enemy3(i*200, 1);
        }

        player1 = new Hero(px, py);
        player1.setVisible(3);
        HeroState = 3;

        hero_hp[0] = new MovingBitmap(R.drawable.hero_hp, 0, 0);
        hero_hp[1] = new MovingBitmap(R.drawable.hero_hp, 20, 0);
        hero_hp[2] = new MovingBitmap(R.drawable.hero_hp, 40, 0);
        hero_hp[3] = new MovingBitmap(R.drawable.hero_hp, 60, 0);
        hero_hp[4] = new MovingBitmap(R.drawable.hero_hp, 80, 0);

        hero_hp[0].resize(16,32);
        hero_hp[1].resize(16,32);
        hero_hp[2].resize(16,32);
        hero_hp[3].resize(16,32);
        hero_hp[4].resize(16,32);

        ButtonW = new BitmapButton(R.drawable.button_w1, R.drawable.button_w2, 63, 240 );
        ButtonA = new BitmapButton(R.drawable.button_a1, R.drawable.button_a2, 10, 300 );
        ButtonS = new BitmapButton(R.drawable.button_s1, R.drawable.button_s2, 63, 300 );
        ButtonD = new BitmapButton(R.drawable.button_d1, R.drawable.button_d2, 116, 300 );
        ButtonSh = new BitmapButton(R.drawable.shoot_button, R.drawable.shoot_button2, 176, 300);
        ButtonJump = new BitmapButton(R.drawable.jump1, R.drawable.jump2, 236, 300);

        _background = new MovingBitmap(R.drawable.map2);

        _scores = new Integer(DEFAULT_SCORE_DIGITS, 0, 550, 10);

        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();

        _pointer1 = null;
        _pointer2 = null;
    }

    @Override
    public void show() {

        _background.show();
        _map.show();

        for(int i = 0; i < towerMax; i++){
            tower[i].show();
        }

        for(int i = 0; i < bulletCount; i++){
            if(bullet[i] != null){
                bullet[i].show();
            }

        }
        for(int i = 0; i < boobMax; i++){
            boob1[i].show();
        }

        for(int i = 0; i < ufoMax; i++){
            ufo[i].show();
        }

        ButtonW.show();
        ButtonA.show();
        ButtonS.show();
        ButtonD.show();
        ButtonSh.show();
        ButtonJump.show();

        player1.show();

        _scores.show();
        _scores.setValue(Invincible);

        for(int i = 0; i < hp; i++) {
            hero_hp[i].show();
        }
        if(Invincible < 200){
            Invincible += 1;
        }
    }

    @Override
    public void move() {
        if(px < -2600){
            changeState(Game.RUNNING_STATE2);
        }
        for(int i = 0; i < boobMax; i++){
            boob1[i].Obj_trigger(_map);
            boob1[i].move();
            boob1[i].isTrigger(px);

            if(!boob1[i].getState()){
                if(isCollision(boob1[i])&&!Dieding&&(Invincible == 200)){
                    if(px > boob1[i].getX1())
                        collisionToR = true;
                    else
                        collisionToR = false;
                    Dieding = true;
                    keepJump = true;
                    jump = true;
                    hp -= 1;
                    Invincible = 160;
                }
            }
        }

        for(int i = 0; i < towerMax; i++){
            tower[i].move();
            tower[i].isTrigger(px);
            if(!tower[i].getState()){
                time += 1;
                if(time == 40){
                    tower[i].makeBullet(player1.getX(), player1.getY(3));
                    time = 0;
                }
            }
            for(int j = 0; j <tower[i].getBulletCount(); j++){
                if(!tower[i].getBulletHit(j)){
                    if(isCollision(tower[i].getBullet(j))&&!Dieding&&(Invincible == 200)){
                        if(px > boob1[i].getX1())
                            collisionToR = true;
                        else
                            collisionToR = false;
                        tower[i].getBullet(j).setBulletHit(true);
                        Dieding = true;
                        keepJump = true;
                        jump = true;
                        hp -= 1;
                        Invincible = 160;
                    }
                }
            }
        }

        for(int i = 0; i < ufoMax; i++){
            ufo[i].move();
            ufo[i].isTrigger(px);
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
                for(int j = 0; j < boobMax; j++){
                    if(!boob1[j].getState()&&boob1[j].isCollision(bullet[i])){
                        bulletHit[i] = true;
                        bullet[i].setVisible(false);
                    }
                }
                for(int j = 0; j < towerMax; j++){
                    if(!tower[j].getState()&&tower[j].isCollision(bullet[i])){
                        bulletHit[i] = true;
                        bullet[i].setVisible(false);
                    }
                }
                for(int j = 0; j < ufoMax; j++){
                    if(!ufo[j].getState()&&ufo[j].isCollision(bullet[i])){
                        bulletHit[i] = true;
                        bullet[i].setVisible(false);
                    }
                }
            }
        }
        player1.move();

        for(int i = 0; i < hp; i++){
            hero_hp[i].move();
        }

        //passH 用來偵測當前在第幾層地面
        int passH = player1.getY(3) + player1.getHeight(3) + (int)fall_velocity;

        if(passH < 72) index = 5;
        else if(passH < 104) index = 0;
        else if(passH < 136) index = 1;
        else if(passH < 168) index = 2;
        else if(passH < 200) index = 3;
        else if(passH < 224) index = 4;
        else{
            Dieding = true;
            keepJump = true;
            jump = true;
            hp -= 1;
            Invincible = 160;
        }

        //isTrigger 用來判斷是否碰到地面
        isTrigger = _map.Obj_trigger(player1.getX(), index)
                || _map.Obj_trigger(player1.getX() + player1.getWidth(3), index);


        if(keepJump){ //keepJump => true: "往上跳躍" false: "往下掉落"
            if(x[i] == 0){
                fall_velocity = x[i];
                keepJump = false;
                i = 0;
            }
            else{
                fall_velocity = x[i];
                i++;
            }
        }
        else{
            if(isTrigger){//如果碰到地面，意味著跳躍結束，"速度"和"加速度"變為零
                fall_velocity = 0;
                fall_acceleration = 1;

                player1.setLocationN(px, y[index]);//使著地後，人物y座標貼合地面
                jump = false;
            }
            else{//如果沒有碰到地面，則繼續掉落
                fall_velocity += fall_acceleration;
                fall_acceleration *= 1.16;
            }
        }

        //將得到的"速度"加到座標位置
        player1.setLocation(px, player1.getY(3)+(int)fall_velocity);

        if(!Dieding){//如果沒有死
            if(keepPressW){//如果W按鍵被點擊
                if(isRight){//如果人物當前是面向右邊
                    HeroState = 11;
                    player1.setVisible(11 + (Invincible % 2)*20);
                }
                else{//如果人物當前是面向左邊
                    HeroState = 12;
                    player1.setVisible(12 + (Invincible % 2)*20);
                }
                if(keepPressSh)//如果Shoot按鍵被點擊
                    //發射子彈
                    makeBullet(player1.getX(), player1.getY(3), 2);
            }
            else if(keepPressA&&!keepPressD){//如果A按鍵被點擊 (D鍵沒有被點擊)
                if(px > runSpeed){//如果沒有碰到最左邊界
                    //人物往左移動
                    px -= runSpeed;
                    player1.setLocation(px, player1.getY(3));
                }
                if(keepPressS){//如果S按鍵被點擊
                    HeroState = 10;
                    player1.setVisible(10 + (Invincible % 2)*20);
                }
                else if(keepPressSh){//如果Shoot按鍵被點擊
                    HeroState = 7;
                    player1.setVisible(7 + (Invincible % 2)*20);
                    if(isRight)//如果面向右邊
                        //生成"往右的子彈"
                        makeBullet(player1.getX(), player1.getY(3) - 9, 0);
                    else//如果面向左邊
                        //生成"往左的子彈"
                        makeBullet(player1.getX(), player1.getY(3) - 9, 1);
                }
                else{
                    HeroState = 6;
                    player1.setVisible(6 + (Invincible % 2)*20);
                }
            }

            else if(keepPressD&&!keepPressA){
                if(px < 250 ){
                    px += runSpeed;
                    player1.setLocation(px, player1.getY(3));
                }
                else{
                    if(_background.getX() > GAME_FRAME_WIDTH - _background.getWidth()) {
                        _background.setLocation(_background.getX() - runSpeed, _background.getY());
                        _map.move();
                        for(int i = 0; i < boobMax; i++){
                            boob1[i].setLocation(boob1[i].getX1() - runSpeed, boob1[i].getY1());
                        }

                        for(int i = 0; i < towerMax; i++){
                            tower[i].setLocation(tower[i].getX1() - runSpeed, tower[i].getY1());
                        }
                        for(int i = 0; i < ufoMax; i++){
                            ufo[i].setLocation(ufo[i].getX1() - runSpeed, ufo[i].getY1());
                        }
                    }
                    else{
                        px += runSpeed;
                        player1.setLocation(px, player1.getY(3));
                    }
                }
                if(keepPressS){
                    HeroState = 5;
                    player1.setVisible(5 + (Invincible % 2)*20);

                }
                else if(keepPressSh){

                    HeroState = 2;
                    player1.setVisible(2 + (Invincible % 2)*20);

                    if(isRight)
                        makeBullet(player1.getX() - 2, player1.getY(3) - 9, 0);
                    else
                        makeBullet(player1.getX() - 2, player1.getY(3) - 9, 1);
                }
                else{
                    HeroState = 1;
                    player1.setVisible(1 + (Invincible % 2)*20);

                }
            }
            else if(keepPressS&&!keepPressA&&!keepPressD){
                if(keepPressSh){
                    if(isRight)
                        makeBullet(player1.getX(), player1.getY(3) + 2, 0);
                    else
                        makeBullet(player1.getX(), player1.getY(3) + 2, 1);
                }
                if(isRight){
                    HeroState = 4;
                    player1.setVisible(4 + (Invincible % 2)*20);

                }
                else{
                    HeroState = 9;
                    player1.setVisible(9 + (Invincible % 2)*20);

                }
            }

            else{
                if(isRight){
                    HeroState = 3;
                    player1.setVisible(3 + (Invincible % 2)*20);

                }
                else{
                    HeroState = 8;
                    player1.setVisible(8 + (Invincible % 2)*20);
                }
                if(keepPressSh){
                    if(isRight)
                        makeBullet(player1.getX() - 2, player1.getY(3) - 9, 0);
                    else
                        makeBullet(player1.getX() - 2, player1.getY(3) - 9, 1);
                }
            }
        }else{
            if(collisionToR){
                px += 4 ;
            }else{
                px -= 4;
            }
            player1.setLocation(px, player1.getY(3));

            if(isRight){
                player1.setVisible(13);
                if(player1.isEndDied(13)){
                    if(hp <= 0){
                        changeState(OVER_STATE);
                    }
                    Dieding = false;
                    px = 100;
                    py = 20;
                    player1.setLocation(px, py);
                    player1.setVisible(3);
                    HeroState = 3;
                    jump = true;
                }
            }
            else{
                player1.setVisible(14);
                if(player1.isEndDied(14)){
                    if(hp <= 0){
                        changeState(OVER_STATE);
                    }
                    Dieding = false;
                    px = 100;
                    py = 20;
                    player1.setLocation(px, py);
                    player1.setVisible(8);
                    HeroState = 8;
                }
            }
        }
    }

    @Override
    public void release() {

        for(int i = 0; i < boobMax; i++){
            boob1[i].release();
            boob1[i] = null;
        }

        for(int i = 0; i < towerMax; i++){
            tower[i].release();
            tower[i] = null;
        }

        for(int i = 0; i < ufoMax; i++){
            ufo[i].release();
            ufo[i] = null;
        }
        for(int i = 0; i < bulletCount; i++){
            bullet[i].release();
            bullet[i] = null;
        }

        _background.release();
        _background = null;

        _map.release();
        _map = null;

        _scores.release();
        _scores = null;

        _music.release();
        _music = null;

        player1.release();
        player1 = null;

        for(int i = 0; i < hp; i++){
            hero_hp[i].release();
            hero_hp[i] = null;
        }

        ButtonW.release();
        ButtonW = null;

        ButtonA.release();
        ButtonA = null;

        ButtonS.release();
        ButtonS = null;

        ButtonD.release();
        ButtonD = null;

        ButtonSh.release();
        ButtonSh = null;

        ButtonJump.release();
        ButtonJump = null;

    }

    @Override
    public void keyPressed(int keyCode) { }

    @Override
    public void keyReleased(int keyCode) { }

    @Override
    public void orientationChanged(float pitch, float azimuth, float roll) { }

    @Override
    public void accelerationChanged(float dX, float dY, float dZ) { }

    @Override
    public boolean pointerPressed(Pointer actionPointer, List<Pointer> pointers) {

        if(ButtonW.pointerPressed(actionPointer, pointers)){
            keepPressW = true;
            return true;
        }
        if(ButtonS.pointerPressed(actionPointer, pointers)){
            keepPressS = true;
            return true;
        }
        if(ButtonA.pointerPressed(actionPointer, pointers)){
            keepPressA = true;
            isRight = false;
            return true;
        }
        if(ButtonD.pointerPressed(actionPointer, pointers)){
            keepPressD = true;
            isRight = true;
            return true;
        }
        if(ButtonSh.pointerPressed(actionPointer, pointers)){
            keepPressSh = true;
            return true;
        }
        if(ButtonJump.pointerPressed(actionPointer, pointers)){
            if(!jump){
                jump = true;
                keepJump = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean pointerMoved(Pointer actionPointer, List<Pointer> pointers) {

        ButtonW.pointerMoved(actionPointer, pointers);
        ButtonS.pointerMoved(actionPointer, pointers);
        ButtonA.pointerMoved(actionPointer, pointers);
        ButtonD.pointerMoved(actionPointer, pointers);
        if(_pointer1 != null && _pointer2 != null) {
            if(actionPointer.getID() == _pointer1.getID()){
                 _pointer1 = actionPointer;
            }else if (actionPointer.getID() == _pointer2.getID()){
                _pointer2 = actionPointer;
            }
            resizeAndroidIcon();
        }
        return false;
    }

    public void resizeAndroidIcon() {}

    @Override
    public boolean pointerReleased(Pointer actionPointer, List<Pointer> pointers) {


        if(ButtonW.pointerReleased(actionPointer,pointers)){
            keepPressW = false;
            return true;
        }
        if(ButtonS.pointerReleased(actionPointer,pointers)){
            keepPressS = false;
            return true;
        }
        if(ButtonA.pointerReleased(actionPointer, pointers)){
            keepPressA = false;
            return true;
        }
        if(ButtonD.pointerReleased(actionPointer, pointers)){
            keepPressD = false;
            return true;
        }
        if(ButtonSh.pointerReleased(actionPointer,pointers)){
            keepPressSh = false;
            return true;
        }
        if(ButtonJump.pointerReleased(actionPointer, pointers)){

        }
        return false;
    }

    @Override
    public void pause() {
        _music.pause();
    }

    @Override
    public void resume() {
        _music.resume();
    }

    public void makeBullet(int x, int y, int dir){

        if(dir == 0) bullet[bulletCount] = new MovingBitmap(R.drawable.bullet, x+33, y+27);
        else if(dir == 1) bullet[bulletCount] = new MovingBitmap(R.drawable.bullet, x-8, y+27);
        else if(dir == 2) bullet[bulletCount] = new MovingBitmap(R.drawable.bullet, x+7, y-6);
        bulletDir[bulletCount] = dir;
        bulletHit[bulletCount] = false;
        bulletCount += 1;
    }
    public boolean isCollision(Enemy enemy){

        int enemy_x1 = enemy.getX1();
        int enemy_x2 = enemy.getX2();
        int enemy_y1 = enemy.getY1();
        int enemy_y2 = enemy.getY2();

        int x1 = player1.getX();
        int y1 = player1.getY(HeroState);
        int x2 = x1 + player1.getWidth(HeroState);
        int y2 = y1 + player1.getHeight(HeroState);


        if(     (enemy_x1 < x1&&enemy_y1 < y1&&enemy_x2 > x1&&enemy_y2 > y1)||
                (enemy_x1 < x1&&enemy_y1 < y2&&enemy_x2 > x1&&enemy_y2 > y2)||
                (enemy_x1 < x2&&enemy_y1 < y1&&enemy_x2 > x2&&enemy_y2 > y1)||
                (enemy_x1 < x2&&enemy_y1 < y2&&enemy_x2 > x2&&enemy_y2 > y2)||
                (enemy_x1 >= x1&&enemy_y1 <= y1&&enemy_x2 <= x2&&enemy_y2 >= y1)||
                (enemy_x1 >= x1&&enemy_y1 <= y2&&enemy_x2 <= x2&&enemy_y2 >= y2)||
                (enemy_x1 <= x1&&enemy_y1 >= y1&&enemy_x2 >= x1&&enemy_y2 <= y2)||
                (enemy_x1 <= x2&&enemy_y1 >= y1&&enemy_x2 >= x2&&enemy_y2 <= y2)||
                (enemy_x1 > x1&&enemy_y1 > y1&&enemy_x2 < x2&&enemy_y2 < y2)){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isCollision(Bullet_T bt){

        int bt_x1 = bt.getX1();
        int bt_x2 = bt.getX2();
        int bt_y1 = bt.getY1();
        int bt_y2 = bt.getY2();

        int x1 = player1.getX();
        int y1 = player1.getY(HeroState);
        int x2 = x1 + player1.getWidth(HeroState);
        int y2 = y1 + player1.getHeight(HeroState);


        if(     (bt_x1 < x1&&bt_y1 < y1&&bt_x2 > x1&&bt_y2 > y1)||
                (bt_x1 < x1&&bt_y1 < y2&&bt_x2 > x1&&bt_y2 > y2)||
                (bt_x1 < x2&&bt_y1 < y1&&bt_x2 > x2&&bt_y2 > y1)||
                (bt_x1 < x2&&bt_y1 < y2&&bt_x2 > x2&&bt_y2 > y2)||
                (bt_x1 >= x1&&bt_y1 <= y1&&bt_x2 <= x2&&bt_y2 >= y1)||
                (bt_x1 >= x1&&bt_y1 <= y2&&bt_x2 <= x2&&bt_y2 >= y2)||
                (bt_x1 <= x1&&bt_y1 >= y1&&bt_x2 >= x1&&bt_y2 <= y2)||
                (bt_x1 <= x2&&bt_y1 >= y1&&bt_x2 >= x2&&bt_y2 <= y2)||
                (bt_x1 > x1&&bt_y1 > y1&&bt_x2 < x2&&bt_y2 < y2)){
            return true;
        }
        else {
            return false;
        }
    }
    public void reLifeSet(){
        Dieding = true;
        keepJump = true;
        jump = true;
        hp -= 1;
        Invincible = 30;
    }
}

