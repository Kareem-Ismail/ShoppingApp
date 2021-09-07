package com.example.ecommerce;

import com.example.ecommerce.integration.model.*;
import com.example.ecommerce.integration.repository.*;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.dto.CartDTO;
import com.example.ecommerce.service.dto.OrderInfoDTO;
import com.example.ecommerce.service.enums.ResponseMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    CustomerOrderRepository customerOrderRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderLineItemRepository orderLineItemRepository;

    @Mock
    CustomerOrderOrderLineItemRepository customerOrderOrderLineItemRepository;

    @InjectMocks
    CartService cartService;

    String customerEmail = "karim.ismail";

    @Test
    public void testAddNewCart() {
        when(customerRepository.findCustomerByEmail(customerEmail)).thenReturn(new Customer());
        String newCart = cartService.createNewCart(customerEmail);
        Assert.assertEquals(ResponseMessage.SUCCESS.getMessage(), newCart);
    }

    @Test
    public void testViewCart() {
        when(customerRepository.findCustomerByEmail(customerEmail)).thenReturn(prepareCustomerData());
        CartDTO cartDTO = cartService.viewCartForCustomer(customerEmail);
        Assert.assertEquals(1, cartDTO.getProducts().size());
    }

    @Test
    public void testGetProductsForCustomer() {
        when(customerRepository.findCustomerByEmail(customerEmail)).thenReturn(prepareCustomerData());
        CartDTO cartDTO = cartService.viewCartForCustomer(customerEmail);
        Assert.assertEquals(1, cartDTO.getProducts().size());
    }

    @Test
    public void testEmptyCartForCustomer() {
        when(customerRepository.findCustomerByEmail(customerEmail)).thenReturn(prepareCustomerData());
        String emptyCartResponse = cartService.emptyCartForCustomer(customerEmail);
        Assert.assertEquals(ResponseMessage.SUCCESS.getMessage(), emptyCartResponse);
    }

    @Test
    public void testAddProductToCart() {
        Customer customer = prepareCustomerData();
        String productName = "Honda Shadow";
        when(customerRepository.findCustomerByEmail(customerEmail)).thenReturn(customer);
        when(productRepository.findProductByCode(productName)).thenReturn(prepareProductData());
        when(customerOrderRepository.findCustomerOrderByCustomer(customer)).thenReturn(new CustomerOrder());
        when(productRepository.save(ArgumentMatchers.any())).thenReturn(new Product());
        when(orderLineItemRepository.save(ArgumentMatchers.any())).thenReturn(new OrderLineItem());
        when(customerOrderOrderLineItemRepository.save(ArgumentMatchers.any())).thenReturn(new CustomerOrderOrderLineItem());
        String addProductToCart = cartService.addProductToCart(new OrderInfoDTO(customerEmail, productName, 10));
        Assert.assertEquals(ResponseMessage.SUCCESS.getMessage(), addProductToCart);
    }

    private Customer prepareCustomerData() {
        Customer customer = new Customer();
        CustomerOrder customerOrder = new CustomerOrder();
        CustomerOrderOrderLineItem customerOrderOrderLineItem = new CustomerOrderOrderLineItem();
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setProduct(new Product());
        customerOrderOrderLineItem.setLineItems(orderLineItem);
        customerOrder.setCustomerOrderOrderLineItemList(new ArrayList<>(Collections.singletonList(customerOrderOrderLineItem)));
        customer.setCustomerOrderList(new ArrayList<>(Collections.singletonList(customerOrder)));
        return customer;
    }

    private Product prepareProductData() {
        Product product = new Product();
        product.setQuantity(20);
        product.setPrice(20000.0);
        return product;
    }

}
