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

import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LinearGradientD {
    private double         angle;
    private LinearGradient gradient;


    // ******************** Constructors **************************************
    public LinearGradientD(final double angle, final Stop... stops) {
        this(angle, Arrays.asList(stops));
    }
    public LinearGradientD(final double angle, final List<Stop> stops) {
        init(angle, stops);
    }

    private void init(final double angle, final List<Stop> stops) {
        this.angle = angle % 360;

        double angleRad = Math.toRadians(angle);
        double x        = Math.cos(angleRad);
        double y        = Math.sin(-angleRad);

        double x1 = x > 0 ? 0 : Math.abs(x);
        double y1 = y > 0 ? 0 : Math.abs(y);
        double x2 = x > 0 ? Math.abs(x) : 0;
        double y2 = y > 0 ? Math.abs(y) : 0;

        gradient = new LinearGradient(x1, y1, x2, y2, true, CycleMethod.NO_CYCLE, stops);
    }


    // ******************** Methods *******************************************
    public LinearGradient get() { return gradient; }

    public double getAngle() { return angle; }
    public void setAngle(final double angle) { init(angle, getStops()); }

    public List<Stop> getStops() {
        return null == gradient ? new ArrayList<>() : gradient.getStops(); }
    public void setStops(final List<Stop> stops) {
        gradient.getStops().clear();
        gradient.getStops().addAll(stops);
    }
}

