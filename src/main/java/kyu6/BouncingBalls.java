package kyu6;

public class BouncingBalls {
    public static int bouncingBall(double h, double bounce, double window) {

        if (h <= 0 || bounce <= 0 || bounce >= 1 || window >= h) {

            return -1;
        }

        int manyTimes = 1;
        double nextH = h * bounce;

        while (nextH > window) {

            nextH *= bounce;
            manyTimes += 2;
        }

        return manyTimes;
    }
}
