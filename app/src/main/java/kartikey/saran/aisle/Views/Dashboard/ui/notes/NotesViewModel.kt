package kartikey.saran.aisle.Views.Dashboard.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kartikey.saran.aisle.API.ResultData
import kartikey.saran.aisle.API.UseCase
import kartikey.saran.aisle.Model.Note
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {
    private val _profileListData: MutableLiveData<ResultData<Note>> = MutableLiveData()
    val profileListData: LiveData<ResultData<Note>>
        get() = _profileListData


    fun getTestProfiles(token: String) {
        viewModelScope.launch {
            useCase.getProfileList(token).onEach {
                _profileListData.postValue(it)
            }.collect()
        }
    }

}