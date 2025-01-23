package com.winphyoethu.pocketo.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.winphyoethu.pocketo.AccountInfo
import com.winphyoethu.pocketo.common.di.ApplicationScope
import com.winphyoethu.pocketo.common.dispatchers.Dispatcher
import com.winphyoethu.pocketo.common.dispatchers.PocketoDispatchers
import com.winphyoethu.pocketo.datastore.AccountInfoSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesAccountInfoDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(PocketoDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        accountInfoSerializer: AccountInfoSerializer
    ): DataStore<AccountInfo> {
        return DataStoreFactory.create(
            serializer = accountInfoSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher)
        ) {
            context.dataStoreFile("pocketo_account_info.pb")
        }
    }

}