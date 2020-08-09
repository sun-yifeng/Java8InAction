package lambdasinaction.chap6;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class GroupingTransactions {

    // 交易List
    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
                                                                  new Transaction(Currency.USD, 2300.0),
                                                                  new Transaction(Currency.GBP, 9900.0),
                                                                  new Transaction(Currency.EUR, 1100.0),
                                                                  new Transaction(Currency.JPY, 7800.0),
                                                                  new Transaction(Currency.CHF, 6700.0),
                                                                  new Transaction(Currency.EUR, 5600.0),
                                                                  new Transaction(Currency.USD, 4500.0),
                                                                  new Transaction(Currency.CHF, 3400.0),
                                                                  new Transaction(Currency.GBP, 3200.0),
                                                                  new Transaction(Currency.USD, 4600.0),
                                                                  new Transaction(Currency.JPY, 5700.0),
                                                                  new Transaction(Currency.EUR, 6800.0) );
    public static void main(String ... args) {
        groupImperatively();
        groupFunctionally();

    }

    /**
     * 交易列表按照货币分组，用map分组，key为货币（key），相同货币的交易，放到同一个list中；
     * java8之前的写法：
     */
    private static void groupImperatively() {
        // 新建一个map，用来分组，key为货币（枚举类），value为list
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        // 遍历交易
        for (Transaction transaction : transactions) {
            // 交易使用的货币
            Currency currency = transaction.getCurrency();
            // 取分组map中的货币
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            // 如果分组map中货币为空，则：
            if (transactionsForCurrency == null) {
                // 新建存放交易的list
                transactionsForCurrency = new ArrayList<>();
                // 将list放到map中，此时list没有存放交易
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            // list中添加交易
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }

    /**
     * 易列表按照货币分组
     * java8的写法，使用流的collect()方法
     */
    private static void groupFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }

    // 交易实体类（静态类）
    public static class Transaction {
        private final Currency currency; //货币
        private final double value;      //金额

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    // 货币枚举
    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }
}
