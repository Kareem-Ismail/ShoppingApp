package com.example.ecommerce.service;

import com.example.ecommerce.integration.model.*;
import com.example.ecommerce.integration.repository.*;
import com.example.ecommerce.service.dto.CartDTO;
import com.example.ecommerce.service.dto.OrderInfoDTO;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.enums.OrderStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderLineItemRepository orderLineItemRepository;

    @Autowired
    CustomerOrderOrderLineItemRepository customerOrderOrderLineItemRepository;

    public String createNewCart(String customerEmail) {
        CustomerOrder customerOrder = new CustomerOrder();
        try {
            Customer customer = customerRepository.findCustomerByEmail(customerEmail);
            customerOrder.setCustomer(customer);
            customerOrderRepository.save(customerOrder);
            return "Success";
        } catch (Exception e) {
            return "Cart creation failed due to " + e.getMessage();
        }
    }

    public void addProductToCart(OrderInfoDTO orderInfoDTO) {
        Customer customer = customerRepository.findCustomerByEmail(orderInfoDTO.getCustomerEmail());
        Product product = getProduct(orderInfoDTO);
        CustomerOrder customerOrder = prepareCustomerOrder(customer, orderInfoDTO.getQuantity(), product);
        OrderLineItem orderLineItem = prepareOrderLineItem(product, orderInfoDTO.getQuantity());
        submitCustomerOrder(customerOrder, orderLineItem);
    }

    public CartDTO viewCartForCustomer(String customerEmail) {
        Customer customer = customerRepository.findCustomerByEmail(customerEmail);
        return getProductsForCustomer(customer);
    }

    public String emptyCartForCustomer(String customerEmail) {
        Customer customer = customerRepository.findCustomerByEmail(customerEmail);
        for (CustomerOrder customerOrder : customer.getCustomerOrderList()) {
            for (CustomerOrderOrderLineItem customerOrderOrderLineItem : customerOrder.getCustomerOrderOrderLineItemList()) {
                customerOrderOrderLineItemRepository.deleteCustomerOrderOrderLineItems(customerOrderOrderLineItem.getLineItems().getId(), customerOrder.getId());
                orderLineItemRepository.deleteOrderLineItemById(customerOrderOrderLineItem.getLineItems().getId());
            }
            customerOrderRepository.deleteCustomerOrder(customerOrder.getId());
        }
        return "Success";
    }

    private CartDTO getProductsForCustomer(Customer customer) {
        CartDTO cartDTO = new CartDTO();
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (CustomerOrder customerOrder : customer.getCustomerOrderList()) {
            for (CustomerOrderOrderLineItem customerOrderOrderLineItem : customerOrder.getCustomerOrderOrderLineItemList()) {
                Product product = customerOrderOrderLineItem.getLineItems().getProduct();
                ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
                productDTOList.add(productDTO);
            }
        }
        cartDTO.setProducts(productDTOList);
        return cartDTO;
    }

    private Product getProduct(OrderInfoDTO orderInfoDTO) {
        Product product = productRepository.findProductByCode(orderInfoDTO.getProductName());
        product.setQuantity(product.getQuantity() - orderInfoDTO.getQuantity());
        productRepository.save(product);
        return product;
    }

    private CustomerOrder prepareCustomerOrder(Customer customer, Integer quantity, Product product) {
        CustomerOrder customerOrderByCustomer = customerOrderRepository.findCustomerOrderByCustomer(customer);
        customerOrderByCustomer.setOrderAmount(product.getPrice() * quantity);
        customerOrderByCustomer.setOrderDate(new Date(System.currentTimeMillis()));
        customerOrderByCustomer.setOrderStatus(OrderStatus.PENDING.toString());
        return customerOrderByCustomer;
    }

    private OrderLineItem prepareOrderLineItem(Product product, Integer quantity) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setProduct(product);
        orderLineItem.setQuantity(quantity);
        orderLineItemRepository.save(orderLineItem);
        return orderLineItem;
    }

    private void submitCustomerOrder(CustomerOrder customerOrder, OrderLineItem orderLineItem) {
        CustomerOrderOrderLineItem customerOrderOrderLineItem = new CustomerOrderOrderLineItem();
        customerOrderOrderLineItem.setCustomerOrder(customerOrder);
        customerOrderOrderLineItem.setLineItems(orderLineItem);
        customerOrderOrderLineItemRepository.save(customerOrderOrderLineItem);
    }
}