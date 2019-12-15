package be.annelyse.year2019;

import be.annelyse.util.Coordinate3D;

import java.util.List;

public class Moon extends Coordinate3D {

    long velocityX;
    long velocityY;
    long velocityZ;

    public Moon() {
        this(0, 0, 0);
    }

    public Moon(long posX, long posY, long posZ) {
        super(posX, posY, posZ);
        velocityX= 0;
        velocityY = 0;
        velocityZ = 0;
    }

    public void velocityAndPositionAdjustmentOneTimeUnit(List<Moon> moons) {
        calculateVelocityByGravity(moons);
        calculatePositionAfterTimeUnit();
    }

    public long calculateTotalEnergy(){
        long potentialEnergy = Math.abs(this.getPosX()) + Math.abs(this.getPosY()) + Math.abs(this.getPosZ());
        long kineticEnergy = Math.abs(velocityX) + Math.abs(velocityY) + Math.abs(velocityZ);
        return potentialEnergy * kineticEnergy;
    }

    public void calculateVelocityByGravity(List<Moon> moons) {


        for (Moon otherMoon : moons) {
            if (this.getPosX() > otherMoon.getPosX()) {
                this.velocityX = this.velocityX - 1;
            } else if (this.getPosX() < otherMoon.getPosX()) {
                this.velocityX = this.velocityX + 1;
            }

            if (this.getPosY() > otherMoon.getPosY()) {
                this.velocityY = this.velocityY - 1;
            } else if (this.getPosY() < otherMoon.getPosY()) {
                this.velocityY = this.velocityY + 1;
            }

            if (this.getPosZ() > otherMoon.getPosZ()) {
                this.velocityZ = this.velocityZ - 1;
            } else if (this.getPosZ() < otherMoon.getPosZ()) {
                this.velocityZ = this.velocityZ + 1;
            }
        }
    }

    public void calculatePositionAfterTimeUnit() {

        this.setPosX(this.getPosX() + this.velocityX );
        this.setPosY(this.getPosY() + this.velocityY );
        this.setPosZ(this.getPosZ() + this.velocityZ );

    }

    public Moon copyMoon(){
        Moon copy = new Moon();
        copy.setPosX(this.getPosX());
        copy.setPosY(this.getPosY());
        copy.setPosZ(this.getPosZ());

        copy.velocityX= this.velocityX;
        copy.velocityY = this.velocityY;
        copy.velocityZ = this.velocityZ;

        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Moon)) return false;

        Moon moon = (Moon) o;

        if (velocityX != moon.velocityX) return false;
        if (velocityY != moon.velocityY) return false;
        return velocityZ == moon.velocityZ;
    }

    @Override
    public int hashCode() {
        int result = (int) (velocityX ^ (velocityX >>> 32));
        result = 31 * result + (int) (velocityY ^ (velocityY >>> 32));
        result = 31 * result + (int) (velocityZ ^ (velocityZ >>> 32));
        return result;
    }
}
