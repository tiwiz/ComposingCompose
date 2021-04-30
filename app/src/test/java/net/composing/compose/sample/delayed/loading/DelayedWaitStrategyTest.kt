package net.composing.compose.sample.delayed.loading

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isInstanceOf
import assertk.assertions.isTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import net.composing.compose.sample.common.Lce
import net.composing.compose.sample.delayed.loading.DelayedWaitStrategy.Repository
import org.junit.Test


class DelayedWaitStrategyTest {

    private val repository = object : Repository{
        override suspend fun makeNetworkRequest(): Boolean = true
    }

    private val crashingRepository = object : Repository{
        override suspend fun makeNetworkRequest(): Boolean =
            throw IllegalArgumentException("")
    }

    private val strategy = DelayedWaitStrategy(repository)
    private val crashingStrategy = DelayedWaitStrategy(crashingRepository)

    @Test
    fun `verify success emits before loading in case of fast success`() = runBlockingTest {
        val elements = strategy.flow.toList()

        assertThat(elements).hasSize(1)
        assertThat(elements.all { it is Lce.Success }).isTrue()
    }

    @Test
    fun `verify loading emits before success in case of slow success`() = runBlockingTest {
        val elements = strategy.flowWithDelays(loadingDelay = 1, successDelay = 1000).toList()

        assertThat(elements).hasSize(2)
        assertThat(elements[0]).isInstanceOf(Lce.Loading::class)
        assertThat(elements[1]).isInstanceOf(Lce.Success::class)
    }

    @Test
    fun `verify error emits before loading in case of fast success`() = runBlockingTest {
        val elements = crashingStrategy.flow.toList()

        assertThat(elements).hasSize(1)
        assertThat(elements.all { it is Lce.Error }).isTrue()
    }

    @Test
    fun `verify loading emits before error in case of slow error`() = runBlockingTest {
        val elements = crashingStrategy.flowWithDelays(loadingDelay = 1, successDelay = 1000).toList()

        assertThat(elements).hasSize(2)
        assertThat(elements[0]).isInstanceOf(Lce.Loading::class)
        assertThat(elements[1]).isInstanceOf(Lce.Error::class)
    }
}