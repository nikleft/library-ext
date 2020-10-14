package com.kstuca.shumovych.master.library.exception

class OrderNotFoundException constructor(val error: String = "order.not.found",
                                         val errorDescription: String = "Invalid order id / order not found") : Exception()