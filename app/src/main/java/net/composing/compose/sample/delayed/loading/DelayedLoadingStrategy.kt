package net.composing.compose.sample.delayed.loading

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.selects.select
import net.composing.compose.sample.common.Lce

class DelayedWaitStrategy(private val repo: Repository) {

    interface Repository {
        suspend fun makeNetworkRequest() : Boolean
    }

    val flow : Flow<Lce<Boolean>> = flow { load() }

    fun flowWithDelays(loadingDelay: Int = 1000,
                       successDelay: Int = 200) = flow {
                           load(loadingDelay = loadingDelay, successDelay = successDelay)
    }

    private suspend fun delayedLoading(delayInMs: Int) : Lce<Boolean>{
        delay(delayInMs.toLong())
        return Lce.Loading
    }

    private suspend fun delayedSuccess(delayInMs: Int) : Lce<Boolean>{
        delay(delayInMs.toLong())
        return try {
            Lce.Success(repo.makeNetworkRequest())
        } catch(e: Exception) {
            Lce.Error(e)
        }
    }

    private suspend fun FlowCollector<Lce<Boolean>>.load(
        loadingDelay: Int = 1000,
        successDelay: Int = 200
    ) = coroutineScope {
        val loading = async {
            delayedLoading(loadingDelay)
        }
        val success = async {
            delayedSuccess(successDelay)
        }

        val result = select<Lce<Boolean>> {
            loading.onAwait { it }
            success.onAwait { it }
        }

        emit(result)

        when(result) {
            is Lce.Loading -> {
                emit(success.await())
            }
            else -> {
                loading.cancel()
            }
        }
    }
}