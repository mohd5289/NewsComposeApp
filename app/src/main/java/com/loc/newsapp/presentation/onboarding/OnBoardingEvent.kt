package com.loc.newsapp.presentation.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel


sealed class OnBoardingEvent {
    object SaveAppEntry:OnBoardingEvent()
}