package org.pluralsight.Services;
import org.pluralsight.Models.Order;
import org.pluralsight.Models.Sandwich;

import java.io.*;
import java.lang.classfile.attribute.LocalVariableTableAttribute;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.pluralsight.Models.Order.orderToString;



public class Receipt {
    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    static String date = now.format(formatter);
    Sandwich sandwich;


    public static void saveReceipt(Order order, Sandwich sandwich) {

        File file = new File(STR. "/Users/nolimit/Desktop/GitProjYU/HoagieInOne/HoagieInOne/src/main/java/org/pluralsight/Receipts/\{date}.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(STR."\nDate : \{date} \n ");
            bw.write(orderToString(sandwich));
bw.close();
        } catch (Exception e) {
            System.out.println("Oops... something went wrong.");

        }
    }
}





