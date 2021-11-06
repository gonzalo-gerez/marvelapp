package gonzalo.dev.marvelapp.home

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gonzalo.dev.core.datasource.dto.CharacterResponse
import gonzalo.dev.core.domain.model.Character
import gonzalo.dev.core.usecase.FetchCharacterUseCase
import gonzalo.dev.marvelapp.common.mvvm.BaseViewModel
import gonzalo.dev.marvelapp.common.util.ErrorUtils
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: FetchCharacterUseCase,
    private val app: Application,
) : BaseViewModel(app) {

    private var charactersDataSet = arrayListOf<Character>()
    var isDualPane = false

    private val _characterState = MutableLiveData<ArrayList<Character>>()
    val characterState: LiveData<ArrayList<Character>> = _characterState

    private val _characterDetailState = MutableLiveData<Character>()
    val characterDetailState: LiveData<Character> = _characterDetailState

    private val _paginationState = MutableLiveData<Unit>()
    val paginationState: LiveData<Unit> = _paginationState

    private var characterResponse: CharacterResponse? = null

    fun fetchCharacters(offset: Int = 0) {
        if (charactersDataSet.isNullOrEmpty()) {
            fetchCharactersInternal(offset)
        } else {
            setViewStateAsLayout()
        }
    }

    private fun fetchCharactersInternal(offset: Int) {
        viewModelScope.launch {
            useCase.fetchCharacters(offset)
                .catch {
                    // TODO: 3/11/21 handle error in order to show a friendly message to the user.
                    setViewStateAsLayout()
                    errorState.postValue(ErrorUtils.errorMessage(app, it))
                }
                .collect {
                    setViewStateAsLayout()
                    characterResponse = it
                    val list = it.data.results.map { data ->
                        Character(
                            data.id, data.name, data.description,
                            data.thumbnail, data.resourceURI
                        )
                    }

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
        val offset = characterResponse?.data?.offset ?: 0
        val count = characterResponse?.data?.count ?: 0
        fetchCharactersInternal(offset + count)
    }

    fun refreshList() {
        fetchCharactersInternal(0)
    }

    fun showDetailFragment(item: Character) {
        _characterDetailState.postValue(item)
    }


    @VisibleForTesting
    fun setDataSet(dataSet: ArrayList<Character>) {
        this.charactersDataSet = dataSet
    }

    @VisibleForTesting
    fun setDataSet(isDualPane: Boolean) {
        this.isDualPane = isDualPane
    }
}