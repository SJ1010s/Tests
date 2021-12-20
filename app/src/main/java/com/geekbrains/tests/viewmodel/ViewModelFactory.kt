package com.geekbrains.tests.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.tests.SchedulerProvider
import com.geekbrains.tests.repository.RepositoryContract

class ViewModelFactory constructor(
    private val repository: RepositoryContract,
    private val appSchedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SearchViewModel(repository, appSchedulerProvider) as T
}
