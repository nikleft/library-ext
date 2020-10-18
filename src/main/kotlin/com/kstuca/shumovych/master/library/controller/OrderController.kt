package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.service.OrderService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/orders")
class OrderController(val orderService: OrderService) {

    @GetMapping
    fun getOrders(@RequestParam(value = "page", required = false) page: Int?,
                  @RequestParam(value = "size", required = false) size: Int?,
                  @RequestParam(value = "sort", required = false) sort: String?,
                  model: Model): String {
        model.addAttribute("orders", orderService.getOrders(page, size, sort))
        return "orders/orders"
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable(value = "id") id: Int, model: Model): String {
        model.addAttribute("order", orderService.getOrder(id))
        return "orders/orderDetails"
    }
}