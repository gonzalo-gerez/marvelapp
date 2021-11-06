package gonzalo.dev.marvelapp.common.mvvm

class ViewState private constructor(viewState: State, statusCode: Int?) {

    val state: State = viewState

    companion object {

        fun of(state: State): ViewState {
            return ViewState(state, null)
        }
    }

    enum class State {
        LAYOUT, ERROR, LOADING;
    }

    fun getViewState(): State {
        return state
    }

}