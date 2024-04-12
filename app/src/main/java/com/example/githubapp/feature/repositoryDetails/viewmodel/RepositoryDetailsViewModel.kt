package com.example.githubapp.feature.repositoryDetails.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.base.BaseViewModel
import com.example.githubapp.core.base.TIMEOUT_DELAY
import com.example.githubapp.core.navigation.NavigationEvent
import com.example.githubapp.core.navigation.Navigator
import com.example.githubapp.core.system.SystemCall
import com.example.githubapp.domain.repository.usecase.GetRepository
import com.example.githubapp.domain.search.models.Repository
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenEvent
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsScreenState
import com.example.githubapp.feature.repositoryDetails.models.RepositoryDetailsVMParam
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel(assistedFactory = RepositoryDetailsViewModel.RepositoryDetailsViewModelFactory::class)
class RepositoryDetailsViewModel @AssistedInject constructor(
    @Assisted private val params: RepositoryDetailsVMParam,
    private val getRepository: GetRepository,
    private val navigator: Navigator,
    private val systemCall: SystemCall,
) : BaseViewModel<RepositoryDetailsScreenEvent>() {

    private val details = MutableStateFlow<Repository?>(null)
    private val isStarredByUser = MutableStateFlow(false)
    private val isLoading = MutableStateFlow(true)
    private val isError = MutableStateFlow(false)

    val viewState = combine(
        details,
        isStarredByUser,
        isLoading,
        isError,
        ::RepositoryDetailsScreenState
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_DELAY),
        initialValue = RepositoryDetailsScreenState()
    )

    init {
        loadRepositoryDetails()
    }

    private fun loadRepositoryDetails() {
        isLoading.update { true }
        isError.update { false }
        viewModelScope.launch {
            val repo = async {
                getRepository.invoke(
                    owner = params.owner,
                    repoName = params.repoName,
                )
            }
            val isStarred = async {
                getRepository.isStarred(params.owner, params.repoName)
            }
            repo.join()
            isStarred.join()
            Timber.d("Repo is ${repo.await()}")
            Timber.d("Repo is liked is ${isStarred.await()}")
            if (repo.await().isSuccess && isStarred.await().isSuccess) {
                isLoading.update { false }
                this@RepositoryDetailsViewModel.details.update { repo.await().getOrThrow() }
                isStarredByUser.update { isStarred.await().getOrThrow() }
            } else {
                isLoading.update { false }
                isError.update { true }
            }
        }
    }

    private fun starRepository(){
        viewModelScope.launch {
            getRepository.star(params.owner, params.repoName)
        }
    }

    override fun onEvent(event: RepositoryDetailsScreenEvent) {
        when (event) {
            is RepositoryDetailsScreenEvent.NavigateBack -> viewModelScope.launch {
                navigator.emitDestination(NavigationEvent.NavigateBack)
            }

            is RepositoryDetailsScreenEvent.OnShareClicked -> details.value.let { notNullDetails ->
                systemCall.share(notNullDetails?.url ?: "")
            }
        }
    }

    @AssistedFactory
    interface RepositoryDetailsViewModelFactory {
        fun create(params: RepositoryDetailsVMParam): RepositoryDetailsViewModel
    }

}


