package com.manta.kurly_work.network

import com.manta.kurly_work.model.SectionUiModel
import com.manta.kurly_work.model.ViewType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchSectionUiModelUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun fetchSectionUiModel(page: Int, onError: (Throwable) -> Unit): Flow<List<SectionUiModel>> =
        flow {
            val sections = mainRepository.fetchSections(page, onError)
            sections.map { section ->
                val products = mainRepository.fetchProducts(section.id, onError)
                SectionUiModel(
                    section.title,
                    products,
                    ViewType.fromString(section.type) ?: ViewType.Horizontal
                )
            }.also { emit(it) }
        }.flowOn(ioDispatcher)
}