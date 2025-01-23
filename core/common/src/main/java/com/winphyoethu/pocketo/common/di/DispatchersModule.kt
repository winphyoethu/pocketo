package com.winphyoethu.pocketo.common.di

import com.winphyoethu.pocketo.common.dispatchers.Dispatcher
import com.winphyoethu.pocketo.common.dispatchers.PocketoDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DispatchersModule {

    @Provides
    @Singleton
    @Dispatcher(PocketoDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @Dispatcher(PocketoDispatchers.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

}