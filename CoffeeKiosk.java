import java.util.ArrayList;

class CoffeeKiosk {
    private ArrayList<Item> menu;
    private ArrayList<Order> orders;
    private int index = 0;

    public CoffeeKiosk() {
        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void addMenuItem(String name, double price) {
        Item item = new Item(name, price, index);
        this.menu.add(item);
        index++;
    }

    public void addMenuItemByInput(String name) {

    }

    public void displayMenu() {
        for (Item item : this.menu) {
            System.out.printf("%d %s -- $%.2f\n", item.getIndex(), item.getName(), item.getPrice());
        }
    }

    public void newOrder() {
        System.out.println("Please enter customer name for new order: ");
        String name = System.console().readLine();

        Order order = new Order(name);
        displayMenu();
        System.out.println("Please enter a menu item index [ (q)uit | (n)ew menu item]: ");
        String itemNumber = System.console().readLine();

        while (!itemNumber.equals("q")) {
            if (itemNumber.equals("n")) {
                System.out.println("What's the item name? ");
                String itemName = System.console().readLine();
                double price = 0;
                while (price == 0) {
                    System.out.println("What's the price? ");
                    try {
                        price = Double.parseDouble(System.console().readLine());
                        this.addMenuItem(itemName, price);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Price must be a number");
                    }
                }
                this.displayMenu();
                System.out.println("Please enter a menu item index [ (q)uit | (n)ew menu item]: ");
                itemNumber = System.console().readLine();
                continue;
            }
            try {
                Item item = null;
                for (Item i : menu) {
                    if (i.getIndex() == Integer.parseInt(itemNumber)) {
                        item = i;
                    }
                }
                if (item != null) {
                    order.addItem(item);
                    order.display();
                } else {
                    System.out.println("Item not found at the index: " + itemNumber);
                }
            } catch (Exception e) {
                System.out.println("Invalid index: " + itemNumber);
            }
            System.out.println("Please enter a menu item index or q to quit: ");
            itemNumber = System.console().readLine();
        }
        this.orders.add(order);
        order.display();
        System.out.println("Thank you for your order. We hope to see you again!");
    }
}
