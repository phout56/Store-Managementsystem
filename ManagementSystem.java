import java.util.*;

// Class for Product
class Product {
    private String productName;
    private String productId;
    private double price;
    private int quantity;

    // Constructor
    public Product(String productName, String productId, double price, int quantity) {
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productId='" + productId + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

// Class for Customer
class Customer {
    private String name;
    private String customerId;
    private String address;
    private String phone;

    // Constructor
    public Customer(String name, String customerId, String address, String phone) {
        this.name = name;
        this.customerId = customerId;
        this.address = address;
        this.phone = phone;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

// Class for Invoice
class Invoice {
    private String invoiceId;
    private Customer customer;
    private List<Product> products;
    private double totalAmount;

    // Constructor
    public Invoice(String invoiceId, Customer customer) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    // Add product to the invoice
    public void addProduct(Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            this.products.add(new Product(product.getProductName(), product.getProductId(), product.getPrice(), quantity));
            this.totalAmount += product.getPrice() * quantity;
        } else {
            System.out.println("Insufficient stock for product: " + product.getProductName());
        }
    }

    // Getters
    public String getInvoiceId() {
        return invoiceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", customer=" + customer +
                ", products=" + products +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

// Class for StoreManager
class StoreManager {
    private final List<Product> products;
    private final List<Customer> customers;
    private final List<Invoice> invoices;

    // Constructor
    public StoreManager() {
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.invoices = new ArrayList<>();
    }

    // Getter for products
    public List<Product> getProducts() {
        return products;
    }

    // Getter for customers
    public List<Customer> getCustomers() {
        return customers;
    }

    // Product management methods
    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(String productId, String productName, double price, int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setProductName(productName);
                product.setPrice(price);
                product.setQuantity(quantity);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getProductId().equals(productId));
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    // Customer management methods
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void updateCustomer(String customerId, String name, String address, String phone) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                customer.setName(name);
                customer.setAddress(address);
                customer.setPhone(phone);
                return;
            }
        }
        System.out.println("Customer not found.");
    }

    public void removeCustomer(String customerId) {
        customers.removeIf(customer -> customer.getCustomerId().equals(customerId));
    }

    public void displayCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    // Invoice management methods
    public void createInvoice(String invoiceId, Customer customer) {
        Invoice invoice = new Invoice(invoiceId, customer);
        invoices.add(invoice);
    }

    public void addProductToInvoice(String invoiceId, Product product, int quantity) {
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId().equals(invoiceId)) {
                invoice.addProduct(product, quantity);
                return;
            }
        }
        System.out.println("Invoice not found.");
    }

    public void displayInvoices() {
        for (Invoice invoice : invoices) {
            System.out.println(invoice);
        }
    }
}

// Main Class
public class StoreManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StoreManager manager = new StoreManager();

        while (true) {
            System.out.println("\nStore Management System:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Display Products");
            System.out.println("5. Add Customer");
            System.out.println("6. Update Customer");
            System.out.println("7. Remove Customer");
            System.out.println("8. Display Customers");
            System.out.println("9. Create Invoice");
            System.out.println("10. Display Invoices");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product ID: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter product quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    manager.addProduct(new Product(productName, productId, price, quantity));
                    break;
                case 2:
                    System.out.print("Enter product ID to update: ");
                    String updateProductId = scanner.nextLine();
                    System.out.print("Enter new product name: ");
                    String updateProductName = scanner.nextLine();
                    System.out.print("Enter new product price: ");
                    double updatePrice = scanner.nextDouble();
                    System.out.print("Enter new product quantity: ");
                    int updateQuantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    manager.updateProduct(updateProductId, updateProductName, updatePrice, updateQuantity);
                    break;
                case 3:
                    System.out.print("Enter product ID to remove: ");
                    String removeProductId = scanner.nextLine();
                    manager.removeProduct(removeProductId);
                    break;
                case 4:
                    System.out.println("Products:");
                    manager.displayProducts();
                    break;
                case 5:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter customer address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter customer phone: ");
                    String phone = scanner.nextLine();
                    manager.addCustomer(new Customer(customerName, customerId, address, phone));
                    break;
                case 6:
                    System.out.print("Enter customer ID to update: ");
                    String updateCustomerId = scanner.nextLine();
                    System.out.print("Enter new customer name: ");
                    String updateCustomerName = scanner.nextLine();
                    System.out.print("Enter new customer address: ");
                    String updateAddress = scanner.nextLine();
                    System.out.print("Enter new customer phone: ");
                    String updatePhone = scanner.nextLine();
                    manager.updateCustomer(updateCustomerId, updateCustomerName, updateAddress, updatePhone);
                    break;
                case 7:
                    System.out.print("Enter customer ID to remove: ");
                    String removeCustomerId = scanner.nextLine();
                    manager.removeCustomer(removeCustomerId);
                    break;
                case 8:
                    System.out.println("Customers:");
                    manager.displayCustomers();
                    break;
                case 9:
                    System.out.print("Enter invoice ID: ");
                    String invoiceId = scanner.nextLine();
                    System.out.print("Enter customer ID for the invoice: ");
                    String invoiceCustomerId = scanner.nextLine();
                    Customer invoiceCustomer = null;
                    for (Customer customer : manager.getCustomers()) {
                        if (customer.getCustomerId().equals(invoiceCustomerId)) {
                            invoiceCustomer = customer;
                            break;
                        }
                    }
                    if (invoiceCustomer != null) {
                        manager.createInvoice(invoiceId, invoiceCustomer);
                        System.out.println("Invoice created.");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 10:
                    System.out.println("Invoices:");
                    manager.displayInvoices();
                    break;
                case 11:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


