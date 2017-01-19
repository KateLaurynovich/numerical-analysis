package by.bsu;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.Math.*;

import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by nadabratb on 1/19/2017.
 */
public class RungeKuttaMethods {
    public static double h = 0.05;
    public static double k = 4.3;
    public static double n = 1.7;

    public static double a = 2 + 0.125*k;
    public static double b = 2 + 0.15*n;

    public static double y = 0;
    public static double z = 1;
    public static double x = 0;

    public static double y(double x, double z){
       double Y = log(pow(b*x, 2) + sqrt(pow(a*x, 2) + pow(z, 2)));
       return Y;
    }
    public static double z(double x, double y) {
        double Z = sqrt(pow(a*x, 2) + pow(y, 2));
        return Z;
    }

    public static void method(){
        System.out.println("Results");
        for(double i = 0 ; i < 1.05; i = i + h ){
            double K1 = h* y( i, z);
            double K2 = h* y(h/2 + i, z + K1/2);
            double K3 = h* y(h/2 + i,z + K2/2);
            double K4 = h* y(i + h, z + K3);
            double L1 = h* z( i, y);
            double L2 = h* z(h/2 + i, y + L1/2);
            double L3 = h* z(h/2 + i,y + L2/2);
            double L4 = h* z(i + h, y + L3);
            z = z + ((L1 + (2 * L2) + (2 * L3) + L4) / 6);
            y = y + (K1 + 2 * K2 + 2 * K3 + K4) / 6;


           System.out.printf(" x = %.2f K1 = %.6f; K2 = %.6f; K3 = %.6f; K4 = %.6f;  y = %.6f\n",i,K1,K2,K3,K4,y);
           System.out.printf("               %.6f;      %.6f;      %.6f;      %.6f;      %.6f\n",i,L1,L2,L3,L4,z);

        }
    }






}
