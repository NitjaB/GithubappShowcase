package com.example.githubapp.feature.profile.viewmodel

import app.cash.turbine.test
import com.example.githubapp.feature.profile.models.ProfileScreenEvent.*
import com.example.githubapp.fixtures.SystemCallFixture
import com.example.githubapp.fixtures.profile.FakeFactoryGetAuthenticatedUserProfile
import com.example.githubapp.fixtures.profile.FakeFailureGetAuthenticatedUserProfile
import com.example.githubapp.fixtures.profile.FakeSuccessGetAuthenticatedUserProfile
import com.example.githubapp.fixtures.profile.buildProfile
import com.example.githubapp.rules.MainCoroutineRule
import io.kotest.matchers.shouldBe
import junit.framework.TestCase.*
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProfileViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `should update state with profile when fetching of profile is successful`() =
        runTest {
            val sut = ProfileViewModel(
                getProfile = FakeSuccessGetAuthenticatedUserProfile(),
                systemCall = SystemCallFixture(),
            )

            sut.viewState.test {
                val initState = awaitItem()
                initState.isLoading shouldBe true
                initState.isError shouldBe false
                initState.profile shouldBe null
                val successState = awaitItem()
                successState.profile shouldBe buildProfile()
                successState.isLoading shouldBe false
                successState.isError shouldBe false
            }
        }

    @Test
    fun `should update state with error when fetching of profile is unsuccessful`() =
        runTest {
            val sut = ProfileViewModel(
                getProfile = FakeFailureGetAuthenticatedUserProfile(),
                systemCall = SystemCallFixture(),
            )

            sut.viewState.test {
                val initState = awaitItem()
                initState.isLoading shouldBe true
                initState.isError shouldBe false
                initState.profile shouldBe null
                val errorState = awaitItem()
                errorState.isError shouldBe true
                errorState.isLoading shouldBe false
                errorState.profile shouldBe null
            }
        }

    @Test
    fun `should reinitialize screen when event is given`() = runTest {
        val getProfile = FakeFactoryGetAuthenticatedUserProfile()
        val sut = ProfileViewModel(
            getProfile = getProfile,
            systemCall = SystemCallFixture(),
        )
        getProfile.shouldCallFail(false)

        sut.onEvent(OnReloadClicked)
        advanceUntilIdle()

        sut.viewState.test {
            val initState = awaitItem()
            initState.isLoading shouldBe true
            initState.isError shouldBe false
            initState.profile shouldBe null
            val successState = awaitItem()
            successState.profile shouldBe buildProfile()
            successState.isLoading shouldBe false
            successState.isError shouldBe false
        }
    }

    @Test
    fun `should call system call share when event is given`() = runTest {
        val systemCall = SystemCallFixture()
        val sut = ProfileViewModel(
            getProfile = FakeSuccessGetAuthenticatedUserProfile(),
            systemCall = systemCall,
        )
        advanceUntilIdle()

        sut.onEvent(OnShareClicked)

        assertEquals(true, systemCall.wasSharedCalled)
    }
}
