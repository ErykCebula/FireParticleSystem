package sample;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Paint;


import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Particle {
    private  double x;
    private double y;
    // wspolrzedne czasteczki
    private Point2D przyspieszenie;
    private Point2D locationOfEmitter;
 SimpleDateFormat format;
    private double radius;// rozmiar czasteczki
    private double life = 1.0;
    private double decay;
    private double masa;
    private BlendMode blendMode;//sposob w jaki sposob beda sie mieszac czasteczki
    private Paint  color;
DateFormat formatter;
    public Particle(double x, double y, Point2D przyspieszenie, double radius, double expireTime, Paint color, BlendMode blendMode, double masa) {
        this.masa=masa;
        this.x = x;
        this.y = y;
        this.przyspieszenie = przyspieszenie;
        this.radius = radius;
        this.color = color;
        this.blendMode = blendMode;

        this.decay = 0.016 / expireTime;
    }

    public boolean isAlive(){
        return life>0;
    }


        public void update(){

        x += przyspieszenie.getX()/masa;
        y += przyspieszenie.getY()/masa;

        life -= decay;

        }

        public void render(GraphicsContext graphicsContext){
        graphicsContext.setGlobalAlpha(life);
        // kiedy zycie dobiegnie do zera wtedy czasteczka tez niknie
            graphicsContext.setGlobalBlendMode(blendMode);
            graphicsContext.setFill(color);
            graphicsContext.fillOval(x,y,radius,radius) ;
        }

    public void applyForce(double xacc,double yacc) {
        x=x-xacc;
        y=y-yacc;


    }


}
