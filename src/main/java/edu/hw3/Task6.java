package edu.hw3;

import java.util.PriorityQueue;

public final class Task6 {
    private Task6() {
    }

    public record Stock(String name, double price) {
    }

    interface StockMarket {
        // Добавить акцию
        void add(Stock stock);

        // Удалить акцию
        void remove(Stock stock);

        // Самая дорогая акция
        Stock mostValuableStock();
    }

    public static class StockMarketImpl implements StockMarket {
        private final PriorityQueue<Stock> stocks;

        public StockMarketImpl() {
            stocks = new PriorityQueue<>((o1, o2) -> {
                if (o2.price > o1.price) {
                    return 1;
                } else {
                    if (o2.price == o1.price) {
                        return o1.name.compareTo(o2.name);
                    }
                    return -1;
                }
            });
        }

        @Override
        public void add(Stock stock) {
            stocks.add(stock);
        }

        @Override
        public void remove(Stock stock) {
            stocks.remove(stock);
        }

        @Override
        public Stock mostValuableStock() {
            return stocks.peek();
        }
    }
}
