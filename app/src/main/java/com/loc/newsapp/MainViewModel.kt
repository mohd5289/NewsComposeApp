package com.loc.newsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loc.newsapp.domain.usecases.AppEntryUseCases
import com.loc.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.milliseconds

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
):ViewModel() {



     var splashCondition by mutableStateOf(true)
         private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
       private  set


    init {

            appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen->
            if (shouldStartFromHomeScreen){
                startDestination = Route.NewsNavigation.route
            }else{
                startDestination = Route.AppStartNavigation.route
            }
            delay(300 )
            splashCondition = false
        }.launchIn(viewModelScope)

    }
}