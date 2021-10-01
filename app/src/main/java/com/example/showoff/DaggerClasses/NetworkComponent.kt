package com.example.showoff.DaggerClasses

import com.example.showoff.ShowOffApplication
import com.example.showoff.ViewModel.APIViewModel
import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent {


   fun inject(showOffApplication: ShowOffApplication)
}