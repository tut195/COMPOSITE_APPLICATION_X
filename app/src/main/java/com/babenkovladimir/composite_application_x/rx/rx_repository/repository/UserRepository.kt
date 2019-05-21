package com.babenkovladimir.composite_application_x.rx.rx_repository.repository

import com.babenkovladimir.composite_application_x.rx.rx_repository.repository.api.UserApi
import com.babenkovladimir.composite_application_x.rx.rx_repository.repository.data.User
import com.babenkovladimir.composite_application_x.rx.rx_repository.repository.db.UserDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserRepository(val userApi: UserApi, val userDao: UserDao) {

    fun getUsers(): Observable<List<User>> {
        return Observable.concatArray(
            getUsersFromDb(),
            getUsersFromApi()
        )
    }

    fun getUsersFromDb(): Observable<List<User>> {
        return userDao.getUsers().filter { it.isNotEmpty() }
            .toObservable()
            .doOnNext {
                Timber.d("Dispatching ${it.size} users from DB...")
            }
    }

    fun getUsersFromApi(): Observable<List<User>> {
        return userApi.getUsers()
            .doOnNext {
                Timber.d("Dispatching ${it.size} users from API...")
                storeUsersInDb(it)
            }
    }

    fun storeUsersInDb(users: List<User>) {
        val a = Observable.fromCallable { userDao.insertAll(users) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Timber.d("Inserted ${users.size} users from API in DB...")
            }
    }

}