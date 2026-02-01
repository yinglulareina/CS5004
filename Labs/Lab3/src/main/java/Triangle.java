/**
 * This class represents a triangle.  It defines all the operations mandated by
 * the Shape interface
 *
 *
 */
public class Triangle extends AbstractShape {
    private final Point2D p1;
    private final Point2D p2;
    private final Point2D p3;

    /**
     * Constructs a triangle object with the given location of three (x, y) points
     *
     * @param p1 reference point of this triangle(also used for distanceFromOrigin)
     * @param p2 second point of this triangle
     * @param p3 third point of this triangle
     * @throws IllegalArgumentException if any two points are identical
     */
    public Triangle(Point2D p1, Point2D p2, Point2D p3) {
        super(p1);
        if (p1 == null || p2 == null || p3 == null) {
            throw new IllegalArgumentException("Triangle can not be null.");
        }
        if (samePoint(p1, p2) || samePoint(p2, p3) || samePoint(p1, p3)) {
            throw new IllegalArgumentException("Triangle can not have two identical points");
        }
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    /**
     * Convenience constructor: constructs a Triangle from coordinates
     *
     * @param x1 x of reference point
     * @param y1 y of reference point
     * @param x2 x of second point
     * @param y2 y of second point
     * @param x3 x of third point
     * @param y3 y of third point
     * @throws IllegalArgumentException if any two or three points are identical
     */
    public Triangle(double x1, double y1,
                    double x2, double y2,
                    double x3, double y3) {
        this(new Point2D(x1, y1), new Point2D(x2, y2), new Point2D(x3, y3));
    }

    @Override
    public double area() {
        // Heron's Formula
        double a = distance(p1, p2);
        double b = distance(p2, p3);
        double c = distance(p1, p3);
        double s = (a + b + c) / 2.0;

        // For collinear points, the expression under sqrt becomes 0
        double under = s * (s - a) * (s - b) * (s - c);

        // numeric safety: avoid tiny negative due to floating point
        if (under < 0 && under > -1e-12) {
            under = 0;
        }
        return Math.sqrt(under);
    }

    @Override
    public double perimeter() {
        double a = distance(p1, p2);
        double b = distance(p2, p3);
        double c = distance(p1, p3);
        return a + b + c;
    }

    @Override
    public Shape resize(double factor) {
        if (factor < 0) {
            throw new IllegalArgumentException("Resize factor cannot be negative.");
        }
        // scale distance by sqrt(factor) to scale area by factor
        double scale = Math.sqrt(factor);

        Point2D newP1 = this.p1; //reference stays the same point
        Point2D newP2 = scaleFromReference(this.p1, this.p2, scale);
        Point2D newP3 = scaleFromReference(this.p1, this.p3, scale);
        return new Triangle(
                newP1, newP2, newP3
        );
    }

    public String toString() {
        return String.format("Triangle: p1(%.3f,%.3f) p2(%.3f,%.3f) p3(%.3f,%.3f)",
                p1.getX(), p1.getY(),
                p2.getX(), p2.getY(),
                p3.getX(), p3.getY());
    }


// helpers(private)

    private static boolean samePoint(Point2D a, Point2D b) {
        //exact match;
        return a.getX() == b.getX() && a.getY() == b.getY();
    }

    private static double distance(Point2D a, Point2D b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static Point2D scaleFromReference(Point2D ref, Point2D p, double scale) {
        double newX = ref.getX() + (p.getX() - ref.getX()) * scale;
        double newY = ref.getY() + (p.getY() - ref.getY()) * scale;
        return new Point2D(newX, newY);
    }
}

