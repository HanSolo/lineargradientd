/*
 * Copyright (c) 2018 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.fx.lineargradientd;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Demo extends Application {
    private LinearGradientD gradient;
    private Circle          circle;
    private double          angle;
    private long            lastTimerCall;
    private AnimationTimer  timer;

    @Override public void init() {
        gradient  = new LinearGradientD(0, new Stop(0, Color.BLACK), new Stop(1, Color.WHITE));

        circle    = new Circle(100);
        circle.setFill(gradient.get());

        angle         = 0;
        lastTimerCall = System.nanoTime();
        timer         = new AnimationTimer() {
            @Override public void handle(final long now) {
                if (now > lastTimerCall + 10_000_000l) {
                    angle += 10;
                    if (angle >= 360) { angle = 0; }
                    gradient.setAngle(angle);
                    circle.setFill(gradient.get());
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane(circle);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);

        stage.setTitle("Gradient with Angle");
        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
