package com.example.githubapp.domain.profile.mappers

import com.example.githubapp.data.models.ProfileResponse
import com.example.githubapp.domain.models.Image
import com.example.githubapp.domain.models.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun map(response: ProfileResponse) = Profile(
        login = response.login,
        avatarUrl = Image.RemoteImage(response.avatarUrl),
        name = response.name,
        bio = response.bio,
        url = response.htmlUrl,
    )
}
