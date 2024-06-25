package Task3.Tasks.Kruchkov;

import java.awt.*;

public class Circule implements Circuleable {
    private Point centre;
    private double radius;
    int count = 0;
    int countP = 0;

    Circule(Point centre,  double radius)   {
        this.centre = centre;
        this.radius = radius;
        count = 0;
        countP = 0;
    }
    @Cashe
    public double diametr()    {
        count ++;
        return 2*this.radius;
    }
    @Cashe
    public double circusLen()  {
        countP ++;
        return 2*Math.PI*this.radius;
    }
    @Mutator
    public void setRadius(double radius) {
        this.radius = radius;
    }
    @Mutator
    public void setCentre(Point centre)  {
        this.centre = centre;
    }
    public double getRadius() {
        return this.radius;
    }
    public Point getCentre()  {
        return  this.centre;
    }
}

