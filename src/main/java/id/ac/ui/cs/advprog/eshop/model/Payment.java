package id.ac.ui.cs.advprog.eshop.model;

import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import java.util.*;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    public Payment() {
        
    }
    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
    }


    public void generateStatus() {
        if (method.equals("COD")) {
            boolean isValid = true;
            if (!paymentData.containsKey("address") || paymentData.get("address").isEmpty()) {
                isValid = false;
            }
            if (!paymentData.containsKey("deliveryFee") || paymentData.get("deliveryFee").isEmpty()) {
                isValid = false;
            }
            if (isValid) {
                status = "SUCCESS";
            } else {
                status = "REJECTED";
            }
        } else if (method.equals("voucherCode")) {
            String voucherCode = paymentData.get("voucherCode");
            if (isValidVoucherCode(voucherCode)) {
                status = "SUCCESS";
            } else {
                status = "REJECTED";
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
        String numericalPart = voucherCode.substring(5, 15);
        if (!numericalPart.matches("\\d{8}")) {
            return false;
        }
        return true;
    }
    
    public String getStatus() {    
        generateStatus();
        return status;
    }


}
