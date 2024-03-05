package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class PaymentTest {
    private List<Product> products;
    private Payment payment;
    private Map<String, String> paymentData;
    @BeforeEach
    public void setup() {
        this.products = new ArrayList<>();
        this.payment = new Payment();
        Product product1 = new Product();
        Product product2 = new Product();

        product1.setProductId("f82df7ea-e44d-4b8a-8a54-9088fc23d663");
        product1.setProductName("Sampo Cap Andi");
        product1.setProductQuantity(7);

        product2.setProductId("6cf7da61-62a0-4599-b67b-45a6ed766d2d");
        product2.setProductName("Sabun Cap Budi");
        product2.setProductQuantity(8);

        this.products.add(product1);
        this.products.add(product2);
        
    }
    @Test
    public void testCODSetPaymentAttributes() {
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        String method = "COD";

        this.paymentData = new HashMap<>();
        paymentData.put("address", "jalan basri");
        paymentData.put("deliveryFee", "10000");

        this.payment.setId(id);
        this.payment.setMethod(method);
        this.payment.setPaymentData(paymentData);

        assertEquals(id, this.payment.getId());
        assertEquals(method, this.payment.getMethod());
        assertEquals(paymentData, this.payment.getPaymentData());
        assertEquals("SUCCESS", this.payment.getStatus());
    }

    @Test
    public void testVoucherCodeSetPaymentAttributes() {
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        String method = "voucherCode";
        this.paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        this.payment.setId(id);
        this.payment.setMethod(method);
        this.payment.setPaymentData(paymentData);

        assertEquals(id, this.payment.getId());
        assertEquals(method, this.payment.getMethod());
        assertEquals("SUCCESS", this.payment.getStatus());
        assertEquals(paymentData, this.payment.getPaymentData());
    }

    @Test
    public void testInvalidPaymentMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.payment.setMethod("INVALID_METHOD");
        });
    }

    @Test
    public void testNullPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.payment.setPaymentData(null);
        });
    }

    @Test
    public void testVoucherCODHashCode() {
        Payment payment2 = new Payment();

        assertNotEquals(this.payment.hashCode(), payment2.hashCode());

        this.payment.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        payment2.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotEquals(this.payment.hashCode(), payment2.hashCode());

        this.payment.setMethod("COD");
        payment2.setMethod("COD");

        assertNotEquals(this.payment.hashCode(), payment2.hashCode());

        this.payment.setPaymentData(new HashMap<String, String>());
        payment2.setPaymentData(new HashMap<String, String>());

        assertNotEquals(this.payment.hashCode(), payment2.hashCode());
    }

    @Test
    public void testVoucherCodePaymentHashCode() {
        Payment payment2 = new Payment();

        assertNotEquals(this.payment.hashCode(), payment2.hashCode());

        this.payment.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        payment2.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotEquals(this.payment.hashCode(), payment2.hashCode());

        this.payment.setMethod("voucherCode");
        payment2.setMethod("voucherCode");

        assertNotEquals(this.payment.hashCode(), payment2.hashCode());

        this.payment.setPaymentData(new HashMap<String, String>());
        payment2.setPaymentData(new HashMap<String, String>());

        assertNotEquals(this.payment.hashCode(), payment2.hashCode());
    }
    @Test
    public void testCODPaymentSucces() {
        this.payment.setMethod("COD");
        this.payment.setPaymentData(new HashMap<>());
        this.payment.getPaymentData().put("address", "haji basrii");
        this.payment.getPaymentData().put("deliveryFee", "3000");


        assertEquals("SUCCESS", this.payment.getStatus());
    }
    @Test
    public void testVoucherCodePaymentSucces() {
        String validVoucherCode = "ESHOP1234ABC5678";

        this.payment.setMethod("voucherCode");
        this.payment.setPaymentData(new HashMap<>());
        this.payment.getPaymentData().put("voucherCode", validVoucherCode);

        assertEquals("SUCCESS", this.payment.getStatus());
    }

    @Test
    public void testVoucherCodePaymentInvalidCodePrefix() {
        String invalidVoucherCode = "ESHAP1234ABC5678";

        this.payment.setMethod("voucherCode");
        this.payment.setPaymentData(new HashMap<>());
        this.payment.getPaymentData().put("voucherCode", invalidVoucherCode);

        assertEquals("REJECTED", this.payment.getStatus());
    }
    @Test
    public void testVoucherCodePaymentInvalidCodeLength() {
        String invalidVoucherCode = "ESHOP1234ABC56789";

        this.payment.setMethod("voucherCode");  
        this.payment.setPaymentData(new HashMap<>());
        this.payment.getPaymentData().put("voucherCode", invalidVoucherCode);

        assertEquals("REJECTED", this.payment.getStatus());
    }
    @Test
    public void testVoucherCodePaymentInvalid8Numerical() {
        String invalidVoucherCode = "ESHOPABCD";

        this.payment.setMethod("voucherCode");
        this.payment.setPaymentData(new HashMap<>());
        this.payment.getPaymentData().put("voucherCode", invalidVoucherCode);

        assertEquals("REJECTED", this.payment.getStatus());
    }

    @Test
    public void testCODEmptyPaymentDataAddress() {
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        String method = "COD";
        this.paymentData = new HashMap<>();
        paymentData.put("address", "");

        this.payment.setId(id);
        this.payment.setMethod(method);
        this.payment.setPaymentData(paymentData);

        assertEquals("REJECTED", this.payment.getStatus());
    }
    @Test
    public void testCODNullPaymentDataAddress() {
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        String method = "COD";
        this.paymentData = new HashMap<>();
        paymentData.put("address", null);

        this.payment.setId(id);
        this.payment.setMethod(method);
        this.payment.setPaymentData(paymentData);

        assertEquals("REJECTED", this.payment.getStatus());
    }
    @Test
    public void testCODEmptyPaymentDataFee() {
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        String method = "COD";
        this.paymentData = new HashMap<>();
        paymentData.put("deliveryFee", "");

        this.payment.setId(id);
        this.payment.setMethod(method);
        this.payment.setPaymentData(paymentData);

        assertEquals("REJECTED", this.payment.getStatus());
    }
    @Test
    public void testCODNullPaymentDataFee() {
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        String method = "COD";
        this.paymentData = new HashMap<>();
        paymentData.put("deliveryFee", null);

        this.payment.setId(id);
        this.payment.setMethod(method);
        this.payment.setPaymentData(paymentData);

        assertEquals("REJECTED", this.payment.getStatus());
    }
}

