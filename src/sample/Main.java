package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main extends Application {

    private FireEmitter emitter = new FireEmitter();
    List<FireEmitter> listOfEmitters;
    private GraphicsContext graphicsContext;
    com.sun.glass.ui.Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
    double forcex = 0;
    double forcey = 0;
    private List<Particle> particles= new ArrayList<>();


    private void onUpdate(){

        graphicsContext.setGlobalAlpha(1.0);
        graphicsContext.setGlobalBlendMode(BlendMode.SRC_OVER);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,600,600);
        //populacja czasteczkami
        //scene.setOnMousePressed();
        particles.addAll(emitter.emit(300,300));
        emitter.setWindforce(-0.01);
        // update particles i dodawanie ich do canvasa
        for(Iterator<Particle> particleIterator= particles.iterator(); particleIterator.hasNext();){
            Particle particle = particleIterator.next();

            particle.applyForce(forcex,forcey);
            particle.update();

            if(!particle.isAlive()){
                particleIterator.remove();
                continue;
            }
            particle.render(graphicsContext);
        }
    }

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(600,600);
        Canvas canvas = new Canvas(600,600);
        graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        final Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();

                scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        particles.addAll(emitter.emit(20,300));
                        particles.addAll(emitter.emit(550,300));

                        for(Iterator<Particle> particleIterator= particles.iterator(); particleIterator.hasNext();){
                            Particle particle = particleIterator.next();
                            particle.update();

                            if(!particle.isAlive()){
                                particleIterator.remove();
                                continue;
                            }
                            particle.render(graphicsContext);
                        }

                    }
                });
        }
        };
        timer.start();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
