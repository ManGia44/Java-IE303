
import java.util.Random;
import java.util.Scanner;

public class bai2 {
    public static void main(String[] args) {
        Scanner var1 = new Scanner(System.in);
        int numPoint = 2000000;

        double Pi = Pi(numPoint);
        System.out.println("Xap xi so Pi: " + Pi);
        var1.close();
    }

    public static double Pi(int numPoint) {
        Random random = new Random();
        int insideCircle = 0;
        double r = 1;
        for (int i = 0; i < numPoint; i++) {
            double x = (random.nextDouble() * 2 - 1) * r;
            double y = (random.nextDouble() * 2 - 1) * r;

            if (x * x + y * y <= r * r) {
                insideCircle++;
            }
        }
        return 4.0 * insideCircle / numPoint;
    }
}
