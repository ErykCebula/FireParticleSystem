package sample;


import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class FireEmitter implements Emitter {
    // do zapelniania czasteczkami
    private double windforce;
    public void setWindforce(double x){
        windforce=windforce+x;

    }
    @Override
    public List<Particle> emit(double x, double y) {
       List<Particle> particles = new ArrayList<>();


        int numParticles = 15;
        for(int i = 0; i< numParticles; i++){

            // tutaj w konstruktorze poprzez x definiuje kierunek poziomy w ktorym bedzie leciec czasteczka a y jak wysoko
            Particle particle = new Particle(x,y, new Point2D(Math.random() -0.5- windforce, Math.random() * -3), 10 , 2.0, Color.rgb(230,45,35), BlendMode.ADD,1);

            particles.add(particle);


        }


        return particles;
    }
}
