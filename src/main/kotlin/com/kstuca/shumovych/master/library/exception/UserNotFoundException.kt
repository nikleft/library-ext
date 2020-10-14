package com.kstuca.shumovych.master.library.exception

class UserNotFoundException constructor(val error: String = "user.not.found",
                                        val errorDescription: String = "Invalid user id / user not found") : Exception()