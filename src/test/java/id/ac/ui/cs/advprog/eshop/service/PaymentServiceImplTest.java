package id.ac.ui.cs.advprog.eshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    List<Payment> payments;
    List<Product> products;
    List<Order> orders ;
    @BeforeEach
    void setup(){    
        products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);

        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        
        payments = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("address", "Jl. Basri");
        paymentData1.put("deliveryFee", "10000");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "COD", paymentData1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078", "voucherCode", paymentData2);
        
        payments.add(payment1);
        payments.add(payment2);

    }

    @Test
    void testAddPaymentVoucherCode(){
        Payment payment = payments.get(1);
        Order order = orders.get(1);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        Payment result = paymentService.addPayment(order, "voucherCode", payment.getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getOrderId(), result.getOrderId());
    }

    @Test
    void testAddPaymentCOD(){
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        Payment result = paymentService.addPayment(order, "COD", payment.getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getOrderId(), result.getOrderId());
    }

    @Test
    void testAddPaymentIfAlreadyExists(){
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getOrderId());
        
        assertNull(paymentService.addPayment(order, "COD", payment.getPaymentData()));
        verify(paymentRepository, times(0)).save(payment);
    }
    @Test
    void testAddPaymentInvalidPaymentType(){
        Payment payment = payments.get(0);
        doReturn(null).when(paymentRepository).findById(payment.getOrderId());
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(orders.get(0), "MEOW", payment.getPaymentData());
        });
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }
    @Test
    void testSetStatus() {

        Payment payment = payments.get(0);
        Order relatedOrder = orders.stream().filter(o -> o.getId().equals(payment.getOrderId())).findFirst().orElse(null);

        doReturn(payment).when(paymentRepository).findById(payment.getOrderId());
        doReturn(new Payment(payment.getOrderId(), payment.getMethod(), payment.getPaymentData())).when(paymentRepository).save(any(Payment.class));

        doReturn(relatedOrder).when(orderRepository).findById(payment.getOrderId());
        
        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(payment.getOrderId(), result.getOrderId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }


    @Test
    void testSetStatusInvalidStatus(){
        Payment payment = payments.get(0);
//        doReturn(payment).when(paymentRepository).findById(payment.getOrderId());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payment, "MEOW");
        });
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }
    @Test
    void testSetStatusInvalidPaymentId(){
//        doReturn(null).when(paymentRepository).findById("zczc");
        assertThrows(NoSuchElementException.class, () -> {
                    paymentService.setStatus(payments.get(0), PaymentStatus.SUCCESS.getValue());
                });
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }
    @Test
    void testGetPayment(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getOrderId());
        Payment result = paymentService.getPayment(payment.getOrderId());
        assertEquals(payment.getOrderId(), result.getOrderId());
    }
    @Test
    void testGetPaymentIfIdNotFound(){
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }
    @Test
    void testGetAllPayments(){
        doReturn(payments).when(paymentRepository).findAll();
        List<Payment> result = paymentService.getAllPayments();
        assertEquals(payments, result);
    }

}

