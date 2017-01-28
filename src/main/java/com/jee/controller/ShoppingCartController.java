package com.jee.controller;

import com.jee.additional.Authenticated;
import com.jee.ejb.interfaces.InterfaceOrder;
import com.jee.entity.*;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "shoppingCartController")
@ConversationScoped
public class ShoppingCartController implements Serializable {

    private static final long serialVersionUID = 3313992336071349028L;

    @Inject
    Conversation conversation;

    @EJB
    private InterfaceOrder<CustomerOrder> oEJB;

    @Inject @Authenticated
    Person user;

    private static final Logger LOGGER = Logger.getLogger(ShoppingCartController.class.getCanonicalName());

    private List<Product> cartItems;

    public void init() {
        cartItems = new ArrayList<Product>();
    }

    public String addItem(final Product p) {

        if (cartItems == null) {
            cartItems = new ArrayList<Product>();
            if (conversation.isTransient()) {
                conversation.begin();
            }
        }

        LOGGER.log(Level.FINEST, "Adding product {0}", p.getName());
        LOGGER.log(Level.FINEST, "Cart Size: {0}", cartItems.size());

        if (!cartItems.contains(p)) {
            cartItems.add(p);
        }

        return "";
    }

    public boolean removeItem(Product p) {
        if (cartItems.contains(p)) {
            LOGGER.log(Level.FINEST, "Deleting product {0}", p.getName());
            return cartItems.remove(p);
        } else {
            return false;
        }
    }

    public double getTotal() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0f;
        }

        double total = 0.0;
        for (Product item : cartItems) {
            total += item.getPrice();
        }

        LOGGER.log(Level.FINEST, "Actual Total:{0}", total);
        return total;
    }

    public String checkout() {

        if (user != null) {
            for (Group g : user.getGroupList()) {
                if (g.getName().equals("ADMINS")) {
                    clear();
                    return "/error.xhtml";
                }
            }

            CustomerOrder order = new CustomerOrder();
            List<OrderDetail> details = new ArrayList<OrderDetail>();

            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setId(1);
            order.setDateCreated(Calendar.getInstance().getTime());
            order.setOrderStatus(orderStatus);
            order.setAmount(getTotal());
            order.setCustomer(user);

            oEJB.add(order);

            for (Product p : getCartItems()) {
                OrderDetail detail = new OrderDetail();

                EmbeddedPK pk = new EmbeddedPK(order.getId(), p.getId());
                detail.setQty(1);
                detail.setProduct(p);
                detail.setPk(pk);

                details.add(detail);
            }

            order.setOrderDetailList(details);
            oEJB.update(order);
            clear();
        } else {
            clear();
            return "/error.xhtml";
        }

        return "/MyOrders.xhtml";
    }

    public void clear() {
        if (cartItems != null) cartItems.clear();
        if (!conversation.isTransient()) conversation.end();
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public Conversation getConversation() {
        return conversation;
    }



}
