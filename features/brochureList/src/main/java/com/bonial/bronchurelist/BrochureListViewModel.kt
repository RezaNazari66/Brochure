package com.bonial.bronchurelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonial.domain.models.Brochure
import com.bonial.domain.models.BrochureListResponse
import com.bonial.domain.models.ContentTypeEnum
import com.bonial.domain.usecases.BrochureListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrochureListViewModel @Inject constructor(private val brochureListUseCase: BrochureListUseCase) :
    ViewModel() {

    private val _brochureListStateLiveData = MutableLiveData<BrochuresListState>()
    val brochureListStateLiveData: LiveData<BrochuresListState>
        get() = _brochureListStateLiveData

    init {
        getBrochureList()
    }

    private fun getBrochureList() {
        _brochureListStateLiveData.value = LoadingState()
        viewModelScope.launch {
            brochureListUseCase.perform {
                onSuccess = {
                    _brochureListStateLiveData.value =
                        DefaultState(filterListByContentType(it.data))
                }
                onError = {
                    it.printStackTrace()
                    _brochureListStateLiveData.value =
                        ErrorState(it.message.orEmpty())
                }
            }
        }
    }

    private fun filterListByContentType(data: BrochureListResponse): List<Brochure> {
        return data.brochureList?.filter { it.contentType == ContentTypeEnum.BROCHURE || it.contentType == ContentTypeEnum.BROCHURE_PREMIUM }
            ?: emptyList()
    }

}