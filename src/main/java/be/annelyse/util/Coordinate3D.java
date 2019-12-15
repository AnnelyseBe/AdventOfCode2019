package be.annelyse.util;

import java.util.List;

public class Coordinate3D {
    long posX;
    long posY;
    long posZ;

    public Coordinate3D(long posX, long posY, long posZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public long getPosX() {
        return posX;
    }

    public void setPosX(long posX) {
        this.posX = posX;
    }

    public long getPosY() {
        return posY;
    }

    public void setPosY(long posY) {
        this.posY = posY;
    }

    public long getPosZ() {
        return posZ;
    }

    public void setPosZ(long posZ) {
        this.posZ = posZ;
    }
}
