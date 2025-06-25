package gonzalo.dev.marvelapp.home.presentation

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gonzalo.dev.marvelapp.BuildConfig
import gonzalo.dev.marvelapp.common.mvvm.BaseViewModel
import gonzalo.dev.marvelapp.common.util.ErrorUtils
import gonzalo.dev.marvelapp.home.domain.model.CharacterDataModel
import gonzalo.dev.marvelapp.home.domain.model.CharacterModel
import gonzalo.dev.marvelapp.home.domain.usecase.FetchCharacterUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: FetchCharacterUseCase,
    private val app: Application,
) : BaseViewModel(app) {

    private var charactersDataSet = arrayListOf<CharacterDataModel>()
    var isDualPane = false

    private val _characterState = MutableLiveData<ArrayList<CharacterDataModel>>()
    val characterState: LiveData<ArrayList<CharacterDataModel>> = _characterState

    private val _characterDetailState = MutableLiveData<CharacterDataModel>()
    val characterDetailState: LiveData<CharacterDataModel> = _characterDetailState

    private val _paginationState = MutableLiveData<Unit>()
    val paginationState: LiveData<Unit> = _paginationState

    private var characterModel: CharacterModel? = null

    fun fetchCharacters(offset: Int = 0) {
        if (charactersDataSet.isEmpty()) {
            fetchCharactersInternal(offset)
        } else {
            setViewStateAsLayout()
        }
    }

    private fun fetchCharactersInternal(offset: Int) {
        viewModelScope.launch {
            useCase.fetchCharacters(offset, BuildConfig.PUBLIC_API_KEY, BuildConfig.PRIVATE_API_KEY)
                .catch {
                    // TODO: 3/11/21 handle error in order to show a friendly message to the user.
                    setViewStateAsLayout()
                    errorState.postValue(ErrorUtils.errorMessage(app, it))
                }
                .collect {
                    setViewStateAsLayout()
                    characterModel = it
                    val list = it.data

                    if (offset == 0) {
                        charactersDataSet.apply {
                            clear()
                            addAll(list)
                        }
                        _characterState.postValue(charactersDataSet)
                        return@collect
                    }

                    charactersDataSet.addAll(list)
                    _paginationState.postValue(Unit)
                }
        }
    }

    fun loadMore() {
        val offset = characterModel?.offset ?: 0
        val count = characterModel?.count ?: 0
        fetchCharactersInternal(offset + count)
    }

    fun refreshList() {
        fetchCharactersInternal(0)
    }

    fun showDetailFragment(item: CharacterDataModel) {
        _characterDetailState.postValue(item)
    }


    @VisibleForTesting
    fun setDataSet(dataSet: ArrayList<CharacterDataModel>) {
        this.charactersDataSet = dataSet
    }

    @VisibleForTesting
    fun setDataSet(isDualPane: Boolean) {
        this.isDualPane = isDualPane
    }
}