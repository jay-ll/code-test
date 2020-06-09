package com.jay.demo.netty.code.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Jay
 **/
public class MutilSumOfPrimes {

    //线程池大小
    private static int poolSize = 4;

    //分4部分处理
    private static int divideOfParts = 4;

    //素数个数
    private static AtomicInteger count = new AtomicInteger();

    //总和
    private static volatile int sum;

    public static void main(String[] args) {
        MutilSumOfPrimes mutilSumOfPrimes = new MutilSumOfPrimes();
        long startTime = System.currentTimeMillis();
        mutilSumOfPrimes.getSumofPrime(200000);
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间：" + (endTime - startTime) / 1000 + "s");
    }

    /**
     * 判断是否素数
     * @param number
     * @return
     */
    public boolean isPrime(int number){
        if (number <= 1){
            return false;
        }
        for (int i = 2; i < number; i++){
            if (number % i == 0){
                return false;
            }else {
                continue;
            }
        }
        return true;
    }

    /**
     * 获取素数求和
     * @param start
     * @param end
     * @return
     */
    public synchronized int getPrimes(int start, int end){
        for (int i = start; i <= end; i++){
            if (isPrime(i)){
                count.getAndIncrement();
                sum += i;
            }
        }
        return sum;
    }

    /**
     * 求和
     * @param number
     */
    public void getSumofPrime(int number){
        int nums = number / divideOfParts;
        List<Callable<Integer>> callableList = new ArrayList<Callable<Integer>>();
        for (int i = 0; i < divideOfParts; i++){
            final int start = i * nums + 1;
            final int end = (divideOfParts - i == 1) ? number : start + nums -1;
            callableList.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return getPrimes(start, end);
                }
            });
        }

        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        try {
            executorService.invokeAll(callableList, 10000, TimeUnit.SECONDS);
            executorService.shutdown();
            System.out.println("200000万素数总和" + sum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
