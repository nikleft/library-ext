package com.kstuca.shumovych.master.library.repository

import com.kstuca.shumovych.master.library.model.OrderModel
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : PagingAndSortingRepository<OrderModel, Int>