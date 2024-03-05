package id.ac.ui.cs.advprog.eshop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.expression.MapAccessor;

import com.github.dockerjava.api.model.Repository;

import enums.OrderStatus;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    void setup(){
        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();

        Map<String, String> paymentDataA = new HashMap<>();
        Map<String, String> paymentDataB = new HashMap<>();

        paymentDataA.put("address", "jalan basri");
        paymentDataA.put("deliveryFee", "10000");

        paymentDataB.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "COD", paymentDataA);
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078", "voucherCode", paymentDataB);

        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate(){
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById(payments.get(1).getId());

        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }
    @Test
    void testSaveUpdate(){
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData());
        Payment result = paymentRepository.save(newPayment);
        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
        assertEquals(payment.getStatus(), result.getStatus());
    }
    @Test
    void testFindByIdIfIdFound(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById(payments.get(1).getId());

        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }
    @Test
    void testFindByIdIfIdNotFound(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById("zczc");

        assertNull(findResult);
    }
    @Test
    void testFindAll(){
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        List<Payment> findResult = paymentRepository.findAll();

        assertEquals(payments.size(), findResult.size());
    }
    
}
