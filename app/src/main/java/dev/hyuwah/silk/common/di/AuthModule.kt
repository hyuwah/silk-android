package dev.hyuwah.silk.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.hyuwah.silk.common.data.repository.AuthRepositoryImpl
import dev.hyuwah.silk.common.domain.repository.AuthRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class AuthModule {

    @Binds
    abstract fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository

}