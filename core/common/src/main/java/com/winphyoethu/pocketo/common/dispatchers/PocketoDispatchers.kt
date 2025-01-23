package com.winphyoethu.pocketo.common.dispatchers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val pocketoDispatchers: PocketoDispatchers)

enum class PocketoDispatchers {
    Default,
    IO
}