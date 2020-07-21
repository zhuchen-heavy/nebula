package com.nebula.lambdademo.test;

import com.nebula.lambdademo.entity.Trader;
import com.nebula.lambdademo.entity.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test6 {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(Collectors.toList());
        System.out.println(cities);

        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted().collect(Collectors.joining());


    }

}
