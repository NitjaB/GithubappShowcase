package com.example.githubapp.domain.profile.usecase

import com.example.githubapp.domain.profile.ProfileRepository
import javax.inject.Inject

class GetAuthenticatedUsersProfile @Inject constructor(
    private val repository: ProfileRepository,
) {

    suspend operator fun invoke() =
        repository.getAuthenticatedUserProfile()
}