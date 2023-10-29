package dev.hyuwah.silk.feature.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.hyuwah.silk.feature.home.data.HomeRepositoryImpl
import dev.hyuwah.silk.feature.home.domain.repository.HomeRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class HomeModule {

    @Binds
    abstract fun bindsHomeRepository(impl: HomeRepositoryImpl): HomeRepository

}