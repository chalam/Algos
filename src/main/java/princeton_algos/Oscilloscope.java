package princeton_algos;

/******************************************************************************
 *  Compilation:  javac princeton_algos.Oscilloscope.java
 *  Execution:    java princeton_algos.Oscilloscope A B wX wY phiX phiY
 *  Dependencies: princeton_algos.StdDraw.java
 *
 *  Simluate the output of an oscilloscope. Assume that the vertical and
 *  horizontal inputs are sinusoidal. Produce Lissajous patterns. These patterns are
 *  named after the French physicist, Jules A. Lissajous, who studied the patterns
 *  that arise when two mutually perpendicular periodic disturbances occur simultaneously.
 *  Assume that the inputs are sinusoidal, so tha the following parametric equations
 *  describe the curve:
 *
       x = Ax sin (wxt + θx)
       y = Ay sin (wyt + θy)
       Ax, Ay = amplitudes
       wx, wy = angular velocity
       θx, θy = phase factors
 *        x = A sin (wX + phiX)
 *        y = B sin (wY + phiY)
 *  
 *  % java princeton_algos.Oscilloscope 1 1 2 3 20 45
 *  % java princeton_algos.Oscilloscope 1 1 5 3 30 45
 *
 ******************************************************************************/

public class Oscilloscope {

    public static void main(String[] args) {
        StdDraw.setXscale(-1, +1);
        StdDraw.setYscale(-1, +1);

        double A    = Double.parseDouble(args[0]);    // amplitudes
        double B    = Double.parseDouble(args[1]);
        double wX   = Double.parseDouble(args[2]);    // angular frequencies
        double wY   = Double.parseDouble(args[3]);
        double phiX = Double.parseDouble(args[4]);    // phase factors
        double phiY = Double.parseDouble(args[5]);

        // convert from degrees to radians
        phiY = Math.toRadians(phiX);
        phiY = Math.toRadians(phiY);


        for (double t = 0.0; t < 10; t += 0.0001) {
            double x = A * Math.sin(wX * t + phiX);
            double y = B * Math.sin(wY * t + phiY);
            StdDraw.point(x, y);
            StdDraw.show();
        }
    }
   
}
