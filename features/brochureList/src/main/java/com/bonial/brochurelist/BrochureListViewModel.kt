package com.bonial.brochurelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonial.domain.models.Brochure
import com.bonial.domain.models.BrochureListResponse
import com.bonial.domain.models.ContentTypeEnum
import com.bonial.domain.usecases.BrochureListUseCase
import com.bonial.shared.sharedmodel.executeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrochureListViewModel @Inject constructor(private val brochureListUseCase: BrochureListUseCase) :
    ViewModel() {

    private val _brochureListStateLiveData = MutableLiveData<BrochuresListState>()
    val brochureListStateLiveData: LiveData<BrochuresListState>
        get() = _brochureListStateLiveData

    private var backupBrochureList: List<Brochure>? = null

    private val _isNearByFilterActive = MutableLiveData<Boolean>(false)
    val isNearByFilterActive: LiveData<Boolean>
        get() = _isNearByFilterActive

    var isDataLoadedBefore = false

    fun getBrochureList() {
        _brochureListStateLiveData.value = LoadingState()
        viewModelScope.launch {
            _brochureListStateLiveData.value =
                brochureListUseCase.executeAsync().executeUseCase(
                    ifSuccess = { DefaultState(filterListByContentType(it)) },
                    ifError = { ErrorState(errorMessage = it.message) }
                )
            isDataLoadedBefore = true
        }
    }

    private fun filterListByContentType(data: BrochureListResponse): List<Brochure> {
        return data.brochureList?.filter { it.contentType == ContentTypeEnum.BROCHURE || it.contentType == ContentTypeEnum.BROCHURE_PREMIUM }
            ?: emptyList()
    }

    fun toggleFilterItem() {
        if (isNearByFilterActive.value == true) {
            disableFilterNearItems()
        } else {
            enableFilterNearItems()
        }
    }

    private fun enableFilterNearItems() {
        backupBrochureList = brochureListStateLiveData.value?.data
        val filteredItemList = brochureListStateLiveData.value?.data?.filter {
            it.distance != null && it.distance!! < 5
        }
        setBrochureListStateLiveData(filteredItemList ?: emptyList())
        _isNearByFilterActive.value = true
    }

    private fun disableFilterNearItems() {
        setBrochureListStateLiveData(backupBrochureList)
        _isNearByFilterActive.value = false
    }

    fun setBrochureListStateLiveData(brochureList: List<Brochure>?) {
        _brochureListStateLiveData.value = DefaultState(brochureList.orEmpty())
    }

}