package com.example.ecommerce.integration.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderOrderLineItemPK implements Serializable {

    private Long customerOrder;
    private Long lineItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrderOrderLineItemPK that = (CustomerOrderOrderLineItemPK) o;
        return customerOrder.equals(that.customerOrder) && lineItems.equals(that.lineItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerOrder, lineItems);
    }
}
