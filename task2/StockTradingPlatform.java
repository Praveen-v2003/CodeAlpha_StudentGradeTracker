import java.util.*;


    class Stock {
        private String symbol;
        private String name;
        private double price;

        public Stock(String symbol, String name, double price) {
            this.symbol = symbol;
            this.name = name;
            this.price = price;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    class Portfolio {
        private double balance;
        private Map<String, Integer> ownedStocks;

        public Portfolio(double balance) {
            this.balance = balance;
            this.ownedStocks = new HashMap<>();
        }

        public double getBalance() {
            return balance;
        }

        public void buyStock(Stock stock, int quantity) {
            double cost = stock.getPrice() * quantity;
            if (cost <= balance) {
                balance -= cost;
                ownedStocks.put(stock.getSymbol(), ownedStocks.getOrDefault(stock.getSymbol(), 0) + quantity);
                System.out.println("Bought " + quantity + " shares of " + stock.getName());
            } else {
                System.out.println("Not enough balance to buy " + stock.getName());
            }
        }

        public void sellStock(Stock stock, int quantity) {
            if (ownedStocks.getOrDefault(stock.getSymbol(), 0) >= quantity) {
                balance += stock.getPrice() * quantity;
                ownedStocks.put(stock.getSymbol(), ownedStocks.get(stock.getSymbol()) - quantity);
                System.out.println("Sold " + quantity + " shares of " + stock.getName());
            } else {
                System.out.println("You don't own enough shares of " + stock.getName());
            }
        }

        public void viewPortfolio(List<Stock> marketStocks) {
            System.out.println("\n===== Portfolio =====");
            System.out.println("Balance: $" + balance);
            for (Stock stock : marketStocks) {
                int qty = ownedStocks.getOrDefault(stock.getSymbol(), 0);
                if (qty > 0) {
                    System.out.println(stock.getName() + " (" + stock.getSymbol() + "): " + qty + " shares");
                }
            }
        }
    }

    public class StockTradingPlatform {
        private static List<Stock> marketStocks = new ArrayList<>();
        private static Portfolio portfolio = new Portfolio(10000);

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            // Sample Market Stocks
            marketStocks.add(new Stock("AAPL", "Apple Inc.", 150.0));
            marketStocks.add(new Stock("GOOGL", "Alphabet Inc.", 2800.0));
            marketStocks.add(new Stock("AMZN", "Amazon.com Inc.", 3400.0));
            marketStocks.add(new Stock("TSLA", "Tesla Inc.", 700.0));

            boolean running = true;
            while (running) {
                System.out.println("\n===== Stock Trading Platform =====");
                System.out.println("1. View Market Stocks");
                System.out.println("2. Buy Stock");
                System.out.println("3. Sell Stock");
                System.out.println("4. View Portfolio");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\n===== Market Stocks =====");
                        for (Stock stock : marketStocks) {
                            System.out.println(stock.getSymbol() + " - " + stock.getName() + " : $" + stock.getPrice());
                        }
                        break;

                    case 2:
                        System.out.print("Enter stock symbol to buy: ");
                        String buySymbol = sc.next();
                        System.out.print("Enter quantity: ");
                        int buyQty = sc.nextInt();
                        Stock buyStock = findStock(buySymbol);
                        if (buyStock != null) {
                            portfolio.buyStock(buyStock, buyQty);
                        } else {
                            System.out.println("Stock not found!");
                        }
                        break;

                    case 3:
                        System.out.print("Enter stock symbol to sell: ");
                        String sellSymbol = sc.next();
                        System.out.print("Enter quantity: ");
                        int sellQty = sc.nextInt();
                        Stock sellStock = findStock(sellSymbol);
                        if (sellStock != null) {
                            portfolio.sellStock(sellStock, sellQty);
                        } else {
                            System.out.println("Stock not found!");
                        }
                        break;

                    case 4:
                        portfolio.viewPortfolio(marketStocks);
                        break;

                    case 5:
                        running = false;
                        System.out.println("Exiting... Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option! Try again.");
                }
            }

            sc.close();
        }

        private static Stock findStock(String symbol) {
            for (Stock stock : marketStocks) {
                if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                    return stock;
                }
            }
            return null;
        }

}
