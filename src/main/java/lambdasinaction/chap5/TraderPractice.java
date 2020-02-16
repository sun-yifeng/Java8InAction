package lambdasinaction.chap5;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * 查询1： 找出2011年发生的交易，并且按照交易额排序；
 * 查询2： 交易员都在哪些不同的城市工作过；
 * 查询3： 查询所有来自剑桥的交易员，并且按照姓名排序；
 * 查询4： 查询所有交易员的姓名，按照字母顺序排序；
 * 查询5： 有没交易员实在米兰工作的；
 * 查询6： 在剑桥的交易员的交易金额；
 * 查询7： 在所有的交易中，最高的交易金额是多少；
 * 查询8： 最小的交易金额是多少。
 *
 * */
public class TraderPractice {

    public static void main(String ...args){
        // 交易员
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
		// 交易
		List<TraderTransaction> transactions = Arrays.asList(
            new TraderTransaction(brian, 2011, 300),
            new TraderTransaction(raoul, 2012, 1000),
            new TraderTransaction(raoul, 2011, 400),
            new TraderTransaction(mario, 2012, 710),
            new TraderTransaction(mario, 2012, 700),
            new TraderTransaction(alan, 2012, 950)
        );	
        
        
        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        System.out.println("-------------查询1： 找出2011年发生的交易，并且按照交易额排序；----------------------");
        List<TraderTransaction> tr2011 = transactions.stream()
                                               .filter(transaction -> transaction.getYear() == 2011)
                                               .sorted(comparing(TraderTransaction::getValue))
                                               .collect(toList());
        System.out.println(tr2011);
        
        // Query 2: What are all the unique cities where the traders work?
        System.out.println("-------------查询2： 交易员都在哪些不同的城市工作过；------------------------------");
        List<String> cities = 
            transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        System.out.println(cities);

        // Query 3: Find all traders from Cambridge and sort them by name.
        System.out.println("-------------查询3： 查询所有来自剑桥的交易员，并且按照姓名排序；-------------------");
        List<Trader> traders = 
            transactions.stream()
                        .map(TraderTransaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        System.out.println(traders);
        
        
        // Query 4: Return a string of all traders’ names sorted alphabetically.
        System.out.println("-------------查询4： 查询所有交易员的姓名，按照字母顺序排序；---------------------");
        String traderStr = 
            transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);
        
        // Query 5: Are there any trader based in Milan?
        System.out.println("-------------查询5： 有没交易员实在米兰工作的；---------------------------------");
        boolean milanBased =
            transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                                            .getCity()
                                                            .equals("Milan")
                                 );
        System.out.println(milanBased);
        
        
        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        System.out.println("-------------查询6： 在剑桥的交易员的交易金额；---------------------------------");
        transactions.stream()
                    .map(TraderTransaction::getTrader)
                    .filter(trader -> trader.getCity().equals("Milan"))
                    .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);
        
        
        // Query 7: What's the highest value in all the transactions?
        System.out.println("-------------查询7： 在所有的交易中，最高的交易金额是多少；------------------------");
        int highestValue = 
            transactions.stream()
                        .map(TraderTransaction::getValue)
                        .reduce(0, Integer::max);
        System.out.println(highestValue);

        /*
        Optional<TraderTransaction> smallestTransaction =
                transactions.stream()
                .reduce((t1, t2)) ->
                         t1.getValue() < t2.getValue() ? t1 : t2);

         */


    }
}