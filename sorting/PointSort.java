import java.util.Arrays;

public class PointSort {

    private static class Point {

        public int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void sortPoints(Point[] points) {
        Arrays.sort(points, (p1, p2) -> {
            if (p1.x != p2.x) {
                return Integer.compare(p1.x, p2.x);
            } else {
                return Integer.compare(p1.y, p2.y);
            }
        });
    }

    private static int inBoth(Point[] a, Point[] b) {
        int count = 0;

        int sz = a.length + b.length;

        Point[] all = new Point[sz];

        for (int i = 0; i < a.length; i++) {
            all[i] = a[i];
        }

        for (int i = 0; i < b.length; i++) {
            all[i + a.length] = b[i];
        }

        for (int i = 0; i < sz - 1; i++) {
            if (all[i] == all[i + 1])
                count++;
        }

        sortPoints(all);

        for (int i = 0; i < sz - 1; i++)
            if (all[i].x == all[i + 1].x && all[i].y == all[i + 1].y)
                count++;

        return count;
    }

    public static void main(String[] args) {
        Point[] a = new Point[] {
                new Point(1, 2),
                new Point(3, 4),
                new Point(5, 6)
        };

        Point[] b = new Point[] {
                new Point(7, 8),
                new Point(3, 4),
                new Point(11, 12)
        };

        System.out.println(inBoth(a, b));
    }

}