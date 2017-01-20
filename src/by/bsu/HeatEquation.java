package by.bsu;

import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 * Created by nadabratb on 1/19/2017.
 */
public class HeatEquation {

    public static double h = 0.1;
    public static double k = 4.3;
    public static double n = 1.7;
    public static double a = 2 + 0.1 * k;
    public static double b = 1 + 0.15 * n;

    /* Найдем решение по явной разностной схеме. В качестве сетки возмем совокупность точек (xm, tm), координаты которых:
    xm = mh, tn=nt;
    h=0.1, tau = 0.02.
     */
    public static double t = 0;
    public static double x = 0;
    public static double tau = 0.02;

    public static double phi(double x, double t) {
        return b * (pow(x, 2) + pow(t, 2));
    }

    public static double f0(double t) {
        return pow(E, a * t);
    }

    public static double f1(double t) {
        return pow(E, a * (t - 1));
    }

    public static double fx(double x) {
        return pow(E, -a * x);
    }

    public static double s = tau / (pow(h, 2));

    public static void table() {
        double[] t = new double[11];
        double[] x = new double[11];
        double[][] u = new double[11][11];
        for (int i = 0; i < 11; i++) {
            t[i] = i * tau;
            x[i] = i * h;

        }
        for (int i = 0; i < 11; i++) {

            u[0][0] = f0(0);
            u[0][10] = f1(0);
        }


        for (int i = 1; i < 11; i++) {

            u[i][0] = f0(t[i - 1]);
            u[i][10] = f1(t[i - 1]);
            u[0][i] = fx(x[i - 1]);

            if (i == 0) {
                u[i][0] = f0(0);
                u[i][10] = f1(0);
            }

        }

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                u[i + 1][j] = (u[i][j]) + s * (u[i][j + 1] - 2 * (u[i][j]) + u[i][j - 1]) + tau * (phi(x[i - 1], t[j - 1]));
                //System.out.println(u[i][j]);
            }
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {

                System.out.printf("%.4f   ", u[i][j]);
                if (j == 10) {
                    System.out.println();
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("f(0) = " + fx(0));
        System.out.println("f(1) = " + fx(1));
        System.out.println(f0(0));
        System.out.println(f1(0));
        table();
    }
}




