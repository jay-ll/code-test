package com.jay.demo.netty.code.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

/**
 * @Author Jay
 **/
public class CodeTest2 {

    public static void main(String[] args) throws IOException {
        List<Money> list = new ArrayList<Money>();
        Map<String, String> map = new IdentityHashMap<String, String>();
        List<Money> newList = new ArrayList<Money>();
        Map<String, String> newMap = new HashMap<String, String>();
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equals("")) {
                break;
            }
            String key = Arrays.asList(str.split(" ")).get(0);
            String value = Arrays.asList(str.split(" ")).get(1);
            map.put(key, value);
        }

        System.out.println("----" + map);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            Money money = new Money();
            money.setCurrency(entry.getKey());
            money.setValue(entry.getValue());
            list.add(money);
        }

        for (Money m : list){
            if (newMap.containsKey(m.getCurrency())){
                int value = Integer.parseInt(newMap.get(m.getCurrency())) + Integer.parseInt(m.getValue());
                newMap.put(m.getCurrency(), String.valueOf(value));
            }else {
                newMap.put(m.getCurrency(), m.getValue());
            }
        }

        for (String s : newMap.keySet()){
            Money mon = new Money();
            mon.setCurrency(s);
            mon.setValue(newMap.get(s));
            System.out.println("!!!!" + mon);
        }


    }

    public static class Money {
        private String currency;
        private String value;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Money{" +
                    "currency='" + currency + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
