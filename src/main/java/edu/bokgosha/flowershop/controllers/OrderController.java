package edu.bokgosha.flowershop.controllers;

import edu.bokgosha.flowershop.entities.Flower;
import edu.bokgosha.flowershop.entities.NumberOfItems;
import edu.bokgosha.flowershop.entities.Order;
import edu.bokgosha.flowershop.entities.OrderItem;
import edu.bokgosha.flowershop.security.CustomUserDetails;
import edu.bokgosha.flowershop.services.EmailService;
import edu.bokgosha.flowershop.services.FlowerService;
import edu.bokgosha.flowershop.services.OrderService;
import edu.bokgosha.flowershop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final FlowerService flowerService;
    private final UserService userServiceImpl;
    private final EmailService emailService;

    private NumberOfItems numberOfItems = new NumberOfItems(1);

    @PostMapping("/shoppingCartPost")
    public String getNumberOfItems(@ModelAttribute NumberOfItems numberOfItems) {
        this.numberOfItems = numberOfItems;

        return "redirect:/shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String getShoppingCart(Model model) {
        Order order = new Order();

        List<OrderItem> orderItems = new ArrayList<>();

        order.setOrderItems(orderItems);
        for (int i = 0; i < numberOfItems.getNumber(); i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFlower(new Flower());
            order.addOrderItem(orderItem);
        }

        model.addAttribute("numberOfItems", numberOfItems);
        model.addAttribute("order", order);

        return "shoppingCart";
    }

    @PostMapping("/add-order")
    public String addNewOrder(@ModelAttribute Order order, @ModelAttribute OrderItem orderItems,
                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        order.setOrderDate(currentDate.format(formatter));
        order.setUser(customUserDetails.getUser());
        order.setState("оформлен");

        for (OrderItem orderItem : order.getOrderItems()) {
            orderService.saveOrderItem(orderItem);
        }

        StringBuilder message = new StringBuilder("Заказ " + order.getId() + " оформлен" + "\nВ заказ входит:\n");
        for (OrderItem orderItem : order.getOrderItems()) {
            message.append(orderItem.getFlower().getName());
        }

        emailService.sendEmail(customUserDetails.getUser().getUsername(), message.toString());

        orderService.saveOrder(order);

        return "redirect:account";
    }

    @GetMapping("/")
    public String getStart() {
        return "redirect:catalog";
    }

    @GetMapping("/catalog")
    public String getCatalog(Model model) {
        model.addAttribute("numberOfItems", numberOfItems);
        model.addAttribute("flowerList", flowerService.getAllFlowers());

        return "catalog";
    }

    @GetMapping("/account")
    public String getAccount(Model model , @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long id = customUserDetails.getUser().getId();

        model.addAttribute("numberOfItems", numberOfItems);
        model.addAttribute("user", userServiceImpl.getUserById(id));
        model.addAttribute("orders", orderService.getOrdersByUserId(id));

        return "account";
    }
}
