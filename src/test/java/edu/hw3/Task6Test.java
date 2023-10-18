package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    void stockMarketBasicTest() {
        var market = new Task6.StockMarketImpl();

        var stockCheap = new Task6.Stock("Amazon", 131.22);
        var stockMedium = new Task6.Stock("Tesla", 255.06);
        var stockExpensive = new Task6.Stock("Microsoft", 330.95);

        market.add(stockCheap);
        market.add(stockExpensive);
        market.add(stockMedium);
        assertThat(market.mostValuableStock()).isEqualTo(stockExpensive);

        market.remove(stockExpensive);
        assertThat(market.mostValuableStock()).isEqualTo(stockMedium);

        market.remove(stockMedium);
        assertThat(market.mostValuableStock()).isEqualTo(stockCheap);
    }

    @Test
    void stockMarketNearPriceTest() {
        var market = new Task6.StockMarketImpl();

        var stockCheap = new Task6.Stock("Amazon", 100.0000);
        var stockMedium = new Task6.Stock("Tesla", 100.0001);
        var stockExpensive = new Task6.Stock("Microsoft", 100.0002);

        market.add(stockCheap);
        market.add(stockExpensive);
        market.add(stockMedium);
        assertThat(market.mostValuableStock()).isEqualTo(stockExpensive);

        market.remove(stockExpensive);
        assertThat(market.mostValuableStock()).isEqualTo(stockMedium);

        market.remove(stockMedium);
        assertThat(market.mostValuableStock()).isEqualTo(stockCheap);
    }

    @Test
    void stockMarketSamePriceTest() {
        var market = new Task6.StockMarketImpl();

        var stockCheap = new Task6.Stock("C", 100.0);
        var stockMedium = new Task6.Stock("B", 100.0);
        var stockExpensive = new Task6.Stock("A", 100.0);

        market.add(stockCheap);
        market.add(stockExpensive);
        market.add(stockMedium);
        assertThat(market.mostValuableStock()).isEqualTo(stockExpensive);

        market.remove(stockExpensive);
        assertThat(market.mostValuableStock()).isEqualTo(stockMedium);

        market.remove(stockMedium);
        assertThat(market.mostValuableStock()).isEqualTo(stockCheap);
    }
}
