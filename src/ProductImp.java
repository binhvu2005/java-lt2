import enti.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;



public class ProductImp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Product[] products = new Product[100];
        int productCount = 0;

        while (true) {
            System.out.println("***********************MENU**************************");
            System.out.println("1.  Nhập thông tin n sản phẩm");
            System.out.println("2.  Hiển thị thông tin các sản phẩm");
            System.out.println("3.  Tính lợi nhuận các sản phẩm");
            System.out.println("4.  Sắp xếp các sản phẩm theo lợi nhuận giảm dần");
            System.out.println("5.  Thống kê các sản phẩm theo giá");
            System.out.println("6.  Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("7.  Nhập sản phẩm");
            System.out.println("8.  Bán sản phẩm");
            System.out.println("9.  Cập nhật trạng thái sản phẩm");
            System.out.println("10. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng sản phẩm muốn thêm: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < n; i++) {
                        products[productCount] = new Product();
                        products[productCount].inputData(scanner, products);
                        productCount++;
                    }
                    break;
                case 2:
                    System.out.println("Thông tin các sản phẩm:");
                    for (int i = 0; i < productCount; i++) {
                        products[i].displayData();
                    }
                    break;
                case 3:
                    for (int i = 0; i < productCount; i++) {
                        products[i].calProfit();
                    }
                    System.out.println("Lợi nhuận các sản phẩm đã được tính.");
                    break;
                case 4:
                    Arrays.sort(products, 0, productCount, Comparator.comparing(Product::getProfit).reversed());
                    System.out.println("Sản phẩm đã được sắp xếp theo lợi nhuận giảm dần.");
                    break;
                case 5:
                    System.out.print("Nhập khoảng giá từ: ");
                    float fromPrice = Float.parseFloat(scanner.nextLine());
                    System.out.print("Nhập khoảng giá đến: ");
                    float toPrice = Float.parseFloat(scanner.nextLine());
                    int count = 0;
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getExportPrice() >= fromPrice && products[i].getExportPrice() <= toPrice) {
                            count++;
                        }
                    }
                    System.out.printf("Có %d sản phẩm có giá bán trong khoảng %.2f - %.2f.\n", count, fromPrice, toPrice);
                    break;
                case 6:
                    System.out.print("Nhập tên sản phẩm cần tìm: ");
                    String searchName = scanner.nextLine();
                    boolean found = false;
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getProductName().toLowerCase().contains(searchName.toLowerCase())) {
                            products[i].displayData();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Không tìm thấy sản phẩm nào có tên chứa '" + searchName + "'.");
                    }
                    break;
                case 7:
                    System.out.print("Nhập mã sản phẩm cần nhập thêm: ");
                    String importProductId = scanner.nextLine();
                    System.out.print("Nhập số lượng sản phẩm nhập thêm: ");
                    int importQuantity = Integer.parseInt(scanner.nextLine());
                    boolean imported = false;
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getProductId().equals(importProductId)) {
                            products[i].setQuantity(products[i].getQuantity() + importQuantity);
                            System.out.println("Đã nhập thêm " + importQuantity + " sản phẩm có mã " + importProductId + ".");
                            imported = true;
                            break;
                        }
                    }
                    if (!imported) {
                        System.out.println("Không tìm thấy sản phẩm có mã " + importProductId + ".");
                    }
                    break;
                case 8:
                    System.out.print("Nhập tên sản phẩm cần bán: ");
                    String sellProductName = scanner.nextLine();
                    System.out.print("Nhập số lượng sản phẩm cần bán: ");
                    int sellQuantity = Integer.parseInt(scanner.nextLine());
                    boolean sold = false;
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getProductName().toLowerCase().equals(sellProductName.toLowerCase())) {
                            if (products[i].getQuantity() >= sellQuantity) {
                                products[i].setQuantity(products[i].getQuantity() - sellQuantity);
                                System.out.println("Đã bán " + sellQuantity + " sản phẩm " + sellProductName + ".");
                                sold = true;
                            } else {
                                System.out.println("Số lượng sản phẩm không đủ.");
                            }
                            break;
                        }
                    }
                    if (!sold) {
                        System.out.println("Không tìm thấy sản phẩm " + sellProductName + ".");
                    }
                    break;
                case 9:
                    System.out.print("Nhập mã sản phẩm cần cập nhật trạng thái: ");
                    String updateProductId = scanner.nextLine();
                    boolean updated = false;
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getProductId().equals(updateProductId)) {
                            products[i].setStatus(!products[i].isStatus());
                            System.out.println("Đã cập nhật trạng thái sản phẩm có mã " + updateProductId + ".");
                            updated = true;
                            break;
                        }
                    }
                    if (!updated) {
                        System.out.println("Không tìm thấy sản phẩm có mã " + updateProductId + ".");
                    }
                    break;
                case 10:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}