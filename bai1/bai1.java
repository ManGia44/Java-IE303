import java.util.Random;
import java.util.Scanner;

public class bai1 {
    public static void main(String[] args) {
        Scanner var1 = new Scanner(System.in);

        System.out.print("Nhap ban kinh r: ");
        double r = var1.nextDouble();

        int numPoint = 2000000;

        double acreageCircle = AcreageCircle(r, numPoint);
        System.out.println("Dien tich xap xi cua hinh tron: " + acreageCircle);
        var1.close();
    }

    public static double AcreageCircle(double r, int numPoint) {
        Random random = new Random();
        int insideCircle = 0;

        for (int i = 0; i < numPoint; i++) {
            double x = (random.nextDouble() * 2 - 1) * r;
            double y = (random.nextDouble() * 2 - 1) * r;

            if (x * x + y * y <= r * r) {
                insideCircle++;
            }
        }
        return 4.0 * r * r * insideCircle / numPoint;
    }
}