package com.bestofgithub.dagger.components

import com.bestofgithub.dagger.modules.EndpointModule
import com.bestofgithub.dagger.modules.SearchModule
import com.bestofgithub.presentation.search.SearchActivity
import dagger.Component

@Component(
    modules = [SearchModule::class, EndpointModule::class],
    dependencies = [AppComponent::class])
interface SearchComponent {
    fun inject(app: SearchActivity)
}