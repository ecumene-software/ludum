package ecuguli.ld.thirtysix;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Sculpture {
    private float[][] sculpture;
    private ShapeRenderer renderer;

    public Sculpture(int width, int height){
        Random random = new Random();
        renderer = new ShapeRenderer();
        sculpture = new float[width][height];
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                sculpture[x][y] = (Math.abs(random.nextFloat()) + 0.999f) - 0.1f;
    }


    public void render(float posX, float posY, int sixelSize){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int x = 0; x < getWidth(); x++)
            for(int y = 0; y < getHeight(); y++) {
                if(sculpture[x][y] > 0) {
                    renderer.setColor(new Color(sculpture[x][y], sculpture[x][y], sculpture[x][y], 255));
                    renderer.rect(posX + (x * sixelSize), posY + (y * sixelSize) , sixelSize, sixelSize);
                }
            }
        renderer.end();
    }

    public void onMouseClick(int sculptureX, int sculptureY, int mx, int my, float sixelSize, float yDiff){
        int indexX = (int)((mx - sculptureX)/sixelSize);
        int indexY = (int)((sculptureY - (my - yDiff))/sixelSize);
        boolean exception;
        try{
            exception = false;
            sculpture[indexX][indexY] = -1;
        }catch (Exception e){
            exception = true;
        }
        if(!exception) SculptingScreen.chiselFrame = false;
    }

    public boolean isInRange(int sculptureX, int sculptureY, int mx, int my, float sixelSize, float yDiff){
        int indexX = (int)((mx - sculptureX)/sixelSize);
        int indexY = (int)((sculptureY - (my - yDiff))/sixelSize);
        boolean exception;
        try{
            exception = false;
            sculpture[indexX][indexY] = -1;
        }catch (Exception e){
            exception = true;
        }
        return exception;
    }

    public int getWidth(){
        return sculpture.length;
    }

    public int getHeight(){
        return sculpture[0].length;
    }
}
