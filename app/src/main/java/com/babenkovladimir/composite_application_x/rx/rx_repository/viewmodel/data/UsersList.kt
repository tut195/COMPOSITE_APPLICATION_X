package com.babenkovladimir.composite_application_x.rx.rx_repository.viewmodel.data

import com.babenkovladimir.composite_application_x.rx.rx_repository.repository.data.User

data class UsersList(val users: List<User>, val message: String, val error: Throwable? = null)