package com.babenkovladimir.composite_application_x.application

import android.app.Application
import androidx.room.Room
import com.babenkovladimir.composite_application_x.rx.rx_repository.repository.UserRepository
import com.babenkovladimir.composite_application_x.rx.rx_repository.repository.api.UserApi
import com.babenkovladimir.composite_application_x.rx.rx_repository.repository.db.AppDatabase
import com.babenkovladimir.composite_application_x.rx.rx_repository.viewmodel.UserListViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class App: Application() {

    //For the sake of simplicity, for now we use this instead of Dagger
    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var userApi: UserApi
        private lateinit var userRepository: UserRepository
        private lateinit var userListViewModel: UserListViewModel
        private lateinit var appDatabase: AppDatabase

        fun injectUserApi() = userApi

        fun injectUserListViewModel() = userListViewModel

        fun injectUserDao() = appDatabase.userDao()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())

        // This code is for Repository pattern
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://randomapi.com/api/")
            .build()

        userApi = retrofit.create(UserApi::class.java)
        appDatabase = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java, "mvvm-database").build()

        userRepository = UserRepository(userApi, appDatabase.userDao())
        userListViewModel = UserListViewModel(userRepository)
        // This code is for repository pattern
    }
}