package kyu7;

public class TheFuscFunctionPartOne {
    public static int fusc(int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n % 2 == 0) return fusc(n / 2);

        return fusc((n - 1) / 2) + fusc((n - 1) / 2 + 1);
    }
}
