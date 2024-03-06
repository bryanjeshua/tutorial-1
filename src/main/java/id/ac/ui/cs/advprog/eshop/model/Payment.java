package id.ac.ui.cs.advprog.eshop.model;

import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import java.util.*;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

@Getter
@Setter
public class Payment {
    private String orderId;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    public Payment() {
        
    }
    public Payment(String orderId, String method, Map<String, String> paymentData) {
        // Asumsi 1 Order 1 Payment maka dipakai ID yang sama
        this.orderId = orderId;
        if ("COD".equals(method) || "voucherCode".equals(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }
        if(paymentData == null) {
            throw new IllegalArgumentException("Payment data cannot be null");
        }
        else {
            this.paymentData = paymentData;
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if(paymentData == null) {
            throw new IllegalArgumentException("Payment data cannot be null");
        }
        else {
            this.paymentData = paymentData;
        }
    }
    
    public void setMethod(String method) {
        if ("COD".equals(method) || "voucherCode".equals(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }
    }
    public void generateStatus() {
        if (method.equals("COD")) {
            boolean isValid = true;
            
            if (!paymentData.containsKey("address") || paymentData.get("address") == null || paymentData.get("address").isEmpty()) {
                isValid = false;
            }
            if (!paymentData.containsKey("deliveryFee") || paymentData.get("deliveryFee") == null || paymentData.get("deliveryFee").isEmpty()) {
                isValid = false;
            }
            if (isValid) {
                status = PaymentStatus.SUCCESS.getValue();
            } else {
                status = PaymentStatus.REJECTED.getValue();
            }
        } else if (method.equals("voucherCode")) {
            String voucherCode = paymentData.get("voucherCode");
            if (isValidVoucherCode(voucherCode)) {
                status = PaymentStatus.SUCCESS.getValue();
            } else {
                status = PaymentStatus.REJECTED.getValue();
            }
        }
    }
    public boolean isValidVoucherCode(String voucherCode) {
        if (voucherCode.length() != 16) {
            return false;
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }
        if (!containsAtLeast8NumericalCharacters(voucherCode)) {
            return false;
        }
        return true;
    }
    
    public String getStatus() {    
        generateStatus();
        return status;
    }

    public boolean containsAtLeast8NumericalCharacters(String str) {
        if (str.length() <= 5) {
            return false; 
        }
        
        String substring = str.substring(5); 
        int count = 0; 
        

        for (int i = 0; i < substring.length(); i++) {
            char c = substring.charAt(i);
            if (Character.isDigit(c)) { // Check if the character is a digit
                count++;
                if (count >= 8) {
                    return true; // Found at least 8 numerical characters
                }
            }
        }
        return false; // Less than 8 numerical characters found
    }
}
