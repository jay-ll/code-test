package com.jay.demo.netty.code.test;

/**
 * @Author Jay
 **/
public class SumOfPrimes {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int i, sum = 0;
        for (i = 2; i <= 200000; i++){
            if (isPrime(i)){
                sum += i;
                System.out.println("i=" + i);
            }
        }
        //
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间" + (endTime - startTime) / 10000 + "s");
        System.out.println("sum=" + sum);
    }

    public static boolean isPrime(int n){
        int j;
        for (j = 2; j*j <= n; j++){
            if (n % j == 0)
                return false;
        }
        return true;
    }
}
