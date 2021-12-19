package com.geekbrains.tests.repository

import com.geekbrains.tests.model.SearchResponse
import io.reactivex.Observable

internal interface RepositoryContract {
    fun searchGithub(
        query: String,
        callback: RepositoryCallback
    )

    fun searchGithub(
        query: String
    ): Observable<SearchResponse>

}