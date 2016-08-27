package ecuguli.ld.thirtysix;

import java.util.Random;

public class Sculpture {
    private float[][][] sculpture;

    public Sculpture(int width, int height, int length){
        Random random = new Random();
        sculpture = new float[width][height][length];
        for(int x = 0; x < width; x++)
            for(int y = 0; y < width; y++)
                for(int z = 0; z < width; z++) {
                    while(sculpture[x][y][z] == 0) sculpture[x][y][z] = Math.abs(random.nextFloat());
                }
    }

    public float get(int x, int y, int z){
        return sculpture[x][y][z];
    }

    public float[][][] getSculpture() {
        return sculpture;
    }

    public void sculpt(int x, int y, int z){
        sculpture[x][y][z] = 0;
    }

    public int getWidth(){
        return sculpture.length;
    }
    public int getHeight(){
        return sculpture[0].length;
    }
    public int getLength(){
        return sculpture[0][0].length;
    }
}
