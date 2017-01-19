package by.bsu;

import static java.lang.Math.sin;


/**
 * Created by nadabratb on 1/18/2017.
 * С помощью двойного пересчета с точностью  ep=10^-4 решить задачу Коши.
 */

public class EulerMethod {
    public static double k = 4.3;
    public static double n = 1.7;

    public static double a = 1 + 0.125*k;
    public static double b = -0.3 + 0.1*n;

   // F[x_, y_] = 1 - Sin[\[Alpha] x + x] + (\[Beta] y)/(\[Alpha] + x);

    public static double f(double x, double y) {
        double  fun = 1 - sin(a * x + y) + b * y / (a + x);
        return fun;
    }

    public static void zk() {
        double h = 0.1;
        double z = 0;
        double z2 = 0;
        for (int i = 0; i < 10; i++) {
            double t = i*h;
            z = z + h*f(i,z);
            double p = z2 + h * f(t, z2)/2;
            z2 = p + h * f(t, z2) / 2;
            int q = i + 1;
            System.out.println("i = "+ q + "     z ["+q+"] ="+z+";\n"+"          z2["+q+"] ="+z2+";");

        }
      return;
    }
}

