import java.util.*;

class Point implements Comparable<Point> {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Point p) {
        if (this.y == p.y)
            return this.x - p.x;
        return this.y - p.y;
    }
}

public class bai3 {
    public static int crossProduct(Point p, Point q, Point r) {
        return (q.x - p.x) * (r.y - p.y) - (q.y - p.y) * (r.x - p.x);
    }

    // Graham Scanning
    public static List<Point> findWarningStations(Point[] points) {
        int n = points.length;
        if (n < 3)
            throw new IllegalArgumentException("Can it nhat 3 diem");

        Arrays.sort(points);

        final Point P0 = points[0];
        Arrays.sort(points, 1, n, (a, b) -> {
            int cross = crossProduct(P0, a, b);
            if (cross == 0) {
                return (P0.x - a.x) * (P0.x - a.x) + (P0.y - a.y) * (P0.y - a.y)
                        - (P0.x - b.x) * (P0.x - b.x) - (P0.y - b.y) * (P0.y - b.y);
            }
            return -cross;
        });

        Stack<Point> hull = new Stack<>();
        hull.push(points[0]);
        hull.push(points[1]);

        for (int i = 2; i < n; i++) {
            Point top = hull.pop();
            while (!hull.isEmpty() && crossProduct(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }

        return new ArrayList<>(hull);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong tram phat: ");
        int n = scanner.nextInt();
        Point[] points = new Point[n];

        System.out.println("Nhap toa do cac tram phat:");
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }
        scanner.close();

        List<Point> warningStations = findWarningStations(points);

        System.out.println("Cac tram canh bao:");
        for (Point p : warningStations) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
        scanner.close();
    }
}
