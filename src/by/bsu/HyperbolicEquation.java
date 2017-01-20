package by.bsu;

import static java.lang.Math.pow;

/**
 * Created by nadabratb on 1/20/2017.
 */
public class HyperbolicEquation {


    public static double k = 4.3;
    public static double n = 1.7;
    public static double a = 2 + 0.1 * k;
    public static double b = 1 + 0.15 * n;

    /**
     * В качестве сетки возмем совокупность точек (xm, tn) xm = mh, tn = n*tau;
     * h = 0.1, tau = 0.1
     */
    public static double [] t = new double[11];
    public static double [] x = new double[11];
    public static double tau = 0.1;
    public static double h = 0.1;

    public static double f(double x,double t){
        return 2*(pow(a,2)+1)/pow((x + pow(b,2)*t+1),2);
    }

    public static double f0(double t){
        return 1/(t*pow(a,2)+1);}
    public static double f1(double t){
        return 1/(t*pow(a,2)+2);}

    public static double fi0(double x){
        return 1/(x +1);}
    public static double fi1(double x){
        return -b/(pow(x +1,2));}

    public static double s() {return  pow(tau,2) / (pow(h, 2));}

    public static double [][] u = new double[11][11];

    public static void solve(){
        for (int i = 0; i < 11 ; i++) {
            t[i] = i*tau;
            x[i] = i*h;
        }

        for (int i = 0; i < 11; i++) {
            if(i == 0){
                u[i][0] = f0(t[0]) ;
                u[i][10] = f1(t[0]);
                u[0][i] = fi0(x[0]);
                u[1][i] = tau*fi1(x[0] + fi0(x[0]));
            }
            else {
                u[i][0] = f0(t[i - 1]);
                u[i][10] = f1(t[i - 1]);
                u[0][i] = fi0(x[i - 1]);
                u[1][i] = tau * fi1(x[i - 1] + fi0(x[i - 1]));
            }
        }

        for (int i = 1 ; i < 10; i++) {
            for (int j = 1; j < 10 ; j++) {
                u[i+1][j] = s()* u[i][j+1] +2*(1-s())*u[i][j]+s()*u[i][j-1] - u[i-1][j] + pow(tau, 2)*f(x[i-1],t[j-1]);

            }
        }

        for (int i = 0; i < 11 ; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%2.4f  ", u[i][j]);
                if (j == 10) {
                    System.out.println();

                }
            }
        }
    }

    public static void main(String[] args) {
        solve();
    }


}

