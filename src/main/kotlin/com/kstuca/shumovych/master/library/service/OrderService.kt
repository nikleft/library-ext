package com.kstuca.shumovych.master.library.service

import com.kstuca.shumovych.master.library.exception.BookNotFoundException
import com.kstuca.shumovych.master.library.model.OrderModel
import com.kstuca.shumovych.master.library.repository.OrderRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(val orderRepository: OrderRepository) {

    fun getOrders(page: Int?, size: Int?, sort: String?): List<OrderModel> {
        return when {
            page != null && size != null && sort != null -> orderRepository.findAll(PageRequest.of(page, size, Sort.by(sort))).content
            page != null && size != null -> orderRepository.findAll(PageRequest.of(page, size)).content
            else -> orderRepository.findAll(PageRequest.of(1, 10)).content
        }
    }

    fun getOrder(id: Int): OrderModel {
        val order: Optional<OrderModel> = orderRepository.findById(id)
        if (order.isPresent) return order.get() else throw BookNotFoundException()
    }
}