package enti;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, float importPrice, float exportPrice, int quantity, String descriptions, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
        this.profit = exportPrice - importPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
        calProfit();
    }

    public float getProfit() {
        return profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProduct) {
        while (true) {
            System.out.print("Nhập mã sản phẩm (4 ký tự): ");
            this.productId = scanner.nextLine();
            if (this.productId.length() == 4) {
                boolean isExist = false;
                for (Product product : arrProduct) {
                    if (product != null && product.getProductId().equals(this.productId)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    break;
                } else {
                    System.err.println("Mã sản phẩm đã tồn tại. Vui lòng nhập lại.");
                }
            } else {
                System.err.println("Mã sản phẩm phải có 4 ký tự.");
            }
        }

        while (true) {
            System.out.print("Nhập tên sản phẩm (6-50 ký tự): ");
            this.productName = scanner.nextLine();
            if (this.productName.length() >= 6 && this.productName.length() <= 50) {
                boolean isExist = false;
                for (Product product : arrProduct) {
                    if (product != null && product.getProductName().equals(this.productName)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    break;
                } else {
                    System.err.println("Tên sản phẩm đã tồn tại. Vui lòng nhập lại.");
                }

            } else {
                System.err.println("Tên sản phẩm phải từ 6-50 ký tự.");
            }
        }

        while (true) {
            System.out.print("Nhập giá nhập (> 0): ");
            try {
                this.importPrice = Float.parseFloat(scanner.nextLine());
                if (this.importPrice > 0) {
                    break;
                } else {
                    System.err.println("Giá nhập phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Giá nhập phải là số thực.");
            }
        }

        while (true) {
            System.out.print("Nhập giá xuất (>= 1.2 * giá nhập): ");
            try {
                this.exportPrice = Float.parseFloat(scanner.nextLine());
                if (this.exportPrice >= 1.2 * this.importPrice) {
                    break;
                } else {
                    System.err.println("Giá xuất phải lớn hơn hoặc bằng 1.2 lần giá nhập.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Giá xuất phải là số thực.");
            }
        }

        while (true) {
            System.out.print("Nhập số lượng (> 0): ");
            try {
                this.quantity = Integer.parseInt(scanner.nextLine());
                if (this.quantity > 0) {
                    break;
                } else {
                    System.err.println("Số lượng phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Số lượng phải là số nguyên.");
            }
        }

        System.out.print("Nhập mô tả sản phẩm: ");
        this.descriptions = scanner.nextLine();
        System.out.print("Nhập trạng thái sản phẩm (true/false): ");
        this.status = Boolean.parseBoolean(scanner.nextLine());
        calProfit();
    }

    public void displayData() {
        System.out.printf("Mã SP: %s, Tên SP: %s, Giá nhập: %.2f, Giá xuất: %.2f, Lợi nhuận: %.2f, Số lượng: %d, Mô tả: %s, Trạng thái: %s\n",
                productId, productName, importPrice, exportPrice, profit, quantity, descriptions, status ? "Đang bán" : "Không bán");
    }

    public void calProfit() {
        this.profit = this.exportPrice - this.importPrice;
    }
}