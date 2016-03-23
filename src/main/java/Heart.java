
/******************************************************************************
 *  Compilation:  javac Heart.java
 *  Execution:    java Heart
 *  Dependencies: StdDraw.java
 *
 *  Draw a pink heart (a filled diamond plus two filled circles).
 *
 ******************************************************************************/

public class Heart {
    public static void main(String[] args) {
        StdDraw.setXscale(-1.5, +1.5);
        StdDraw.setYscale(-1.5, +1.5);
        StdDraw.setPenColor(StdDraw.PINK);

        // draw diamond
        double[] xs = { -1,  0, 1, 0 };
        double[] ys = {  0, -1, 0, 1 };
        StdDraw.filledPolygon(xs, ys);

        // circles
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(+0.5, 0.5, 1 / Math.sqrt(2));
        StdDraw.filledCircle(-0.5, 0.5, 1 / Math.sqrt(2));


    }

}
