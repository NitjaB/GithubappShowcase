package com.example.githubapp.domain.profile.di

import com.example.githubapp.domain.profile.usecase.GetAuthenticatedUsersProfile
import com.example.githubapp.domain.profile.usecase.GetAuthenticatedUsersProfileImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface ProfileModule {

    @Binds
    @ViewModelScoped
    fun bindGetAuthenticatedUserProfile(
        getAuthenticatedUsersProfileImpl: GetAuthenticatedUsersProfileImpl
    ): GetAuthenticatedUsersProfile
}
