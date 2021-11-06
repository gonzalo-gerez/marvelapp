package gonzalo.dev.marvelapp.common.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

open class BaseViewModel(appContext: Application) : AndroidViewModel(appContext) {

    private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
    val viewState: LiveData<ViewState> = _viewState

    val errorState = MutableLiveData<String>()

    fun setViewStateAsError() {
        _viewState.postValue(ViewState.of(ViewState.State.ERROR))
    }

    fun setViewStateAsLoading() {
        _viewState.postValue(ViewState.of(ViewState.State.LOADING))
    }

    fun setViewStateAsLayout() {
        _viewState.postValue(ViewState.of(ViewState.State.LAYOUT))
    }
}
