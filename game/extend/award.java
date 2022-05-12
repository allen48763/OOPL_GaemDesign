package tw.edu.ntut.csie.game.extend;

import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;

public class award implements GameObject {
    private int x1, x2, y1, y2;

    private MovingBitmap _award;
    private boolean isHit = false;

    public award(int x, int y){
        x1 = x;
        y1 = y;

    }
    @Override
    public void move() {

    }

    @Override
    public void show() {

    }

    @Override
    public void release() {

    }
}
