package org.pluralsight.Models;
import java.io.*;
import java.lang.classfile.attribute.LocalVariableTableAttribute;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Receipt {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String date = now.format(formatter);




public void saveReceipt() {
    File file = new File(STR."Receipts/\{date}.txt");
}




}
