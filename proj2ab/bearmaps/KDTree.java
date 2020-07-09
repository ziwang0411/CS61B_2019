package bearmaps;

import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet {
    private Node root;
    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;

    private class Node {
        public Node leftBottom;
        public Node rightTop;
        private Point p;
        private boolean orientation;


        Node(Point p, boolean orientation) {
            this.p = p;
            this.orientation = orientation;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, HORIZONTAL);
        }
    }

    private Node add(Point p, Node n, boolean orientation) {

        if (n == null) {
            return new Node(p, orientation);
        }
        if (p.equals(n.p)) {
            return n;
        }
        int cmp;
        if (orientation == HORIZONTAL) {  //compare x
            cmp = Double.compare(p.getX(), n.p.getX());
        } else {
            cmp = Double.compare(p.getY(), n.p.getY());
        }
        if (cmp < 0) {
            n.leftBottom = add(p, n.leftBottom, !orientation);
        } else {
            n.rightTop = add(p, n.rightTop, !orientation);
        }
        return n;
    }

    @Override
    public Point nearest(double x, double y) {
        return nearest(root, new Point(x,y), root.p);
    }
    public Point nearest(Node n, Point target, Point best) {
        if (n ==null) return best;
        if (Point.distance(n.p, target) < Point.distance(best, target)) {
            best = n.p;
        }
        int cmp;
        if (n.orientation == HORIZONTAL) {
            cmp = cmp = Double.compare(target.getX(), n.p.getX());
        } else {
            cmp = Double.compare(target.getY(), n.p.getY());
        }
        Node goodSide, badSide;
        if (cmp < 0) {
            goodSide = n.leftBottom;
            badSide = n.rightTop;
        } else {
            goodSide = n.rightTop;
            badSide = n.leftBottom;
        }
        best = nearest(goodSide,target,best);
        if (isWorthLooking(n, target, best)) {
            best = nearest(badSide,target, best);
        }
        return best;
    }

    private boolean isWorthLooking(Node n, Point target, Point best) {
        double distToBest = Point.distance(target, best);
        double distToBad;
        if (!n.orientation == HORIZONTAL) {
            distToBad = Point.distance(new Point(target.getX(), n.p.getY()), target);
        } else {
            distToBad = Point.distance(new Point(n.p.getX(), target.getY()), target);
        }
        return distToBad < distToBest;
    }

    public static void main(String[] args) {

        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);
        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point ret = kd.nearest(1.0, 5.11); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4


/*
        Point p1 = new Point(1, 5); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(2, 2);
        Point p3 = new Point(4, 9);
        Point p4 = new Point(7, 3);
        Point p5 = new Point(6, 2);
        Point p6 = new Point(8, 7);
        Point p7 = new Point(9, 1);
        Point p8 = new Point(5, 6);
        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
//        NaivePointSet kd = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
        Point ret = kd.nearest(3, 6); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
*/
    }
}
