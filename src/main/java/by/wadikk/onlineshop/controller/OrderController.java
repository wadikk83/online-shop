package by.wadikk.onlineshop.controller;

import by.wadikk.onlineshop.entity.*;
import by.wadikk.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order-detail")
    public String orderDetails(@RequestParam("id") Long id, Model model) {
        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);
        return ("orderDetails");
    }

    @GetMapping("/order-list")
    public String orderListGet(Model model) {
        List<Order> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "orderList";
    }

    @GetMapping("/delete")
    public String deleteOrderById(@RequestParam("id") Long id) {
        orderService.deleteOrderById(id);
        return "redirect:order-list";
    }

    @GetMapping("/edit")
    public String editOrderGet(@RequestParam("id") Long id, Model model) {
        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("orderStatusList", OrderStatus.values());
        return "editOrder";
    }

    @PostMapping("/edit")
    public String editOrderPost(@ModelAttribute("order") Order order, HttpServletRequest request) {
        Order newOrder = orderService.findOrderById(order.getId());
        newOrder.setOrderStatus(order.getOrderStatus());

        newOrder.setId(order.getId());
        orderService.saveOrder(newOrder);

        return "redirect:order-list";
    }


}
