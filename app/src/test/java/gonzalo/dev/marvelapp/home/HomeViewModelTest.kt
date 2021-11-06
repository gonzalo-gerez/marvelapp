package gonzalo.dev.marvelapp.home

import android.os.Looper.getMainLooper
import androidx.lifecycle.Observer
import gonzalo.dev.core.datasource.dto.CharacterResponse
import gonzalo.dev.core.domain.model.Character
import gonzalo.dev.core.usecase.FetchCharacterUseCase
import gonzalo.dev.marvelapp.AbstractRobolectricTest
import gonzalo.dev.marvelapp.common.mvvm.ViewState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.robolectric.Shadows.shadowOf

class HomeViewModelTest : AbstractRobolectricTest() {

    private val characterResponse = mockk<CharacterResponse>(relaxed = true)
    private val mockFlowSuccess = flow {
        emit(characterResponse)
    }

    @Test
    fun `When fetchCharacters is called, verify flow collect is executed`() {
        runBlocking {
            val observer = mockk<Observer<ArrayList<Character>>>(relaxed = true)
            val dataSet = mockk<ArrayList<Character>>(relaxed = true)
            val useCase = mockk<FetchCharacterUseCase>(relaxed = true)
            val viewModel = HomeViewModel(useCase, getAppContext())
            viewModel.setDataSet(dataSet)

            viewModel.viewState.observeForever {}
            viewModel.characterState.observeForever(observer)

            every { dataSet.isNullOrEmpty() } returns true
            coEvery { useCase.fetchCharacters(0) } returns mockFlowSuccess

            viewModel.fetchCharacters(0)
            shadowOf(getMainLooper()).idle()

            Assert.assertEquals(ViewState.State.LAYOUT, viewModel.viewState.value!!.state)

            verify {
                dataSet.addAll(any())
                observer.onChanged(any())
            }
            viewModel.viewState.removeObserver { }
            viewModel.characterState.removeObserver(observer)
        }
    }

    @Test
    fun `When fetchCharacters is called and dataset null or empty, verify use case is executed`() {
        val dataSet = mockk<ArrayList<Character>>(relaxed = true)
        val useCase = mockk<FetchCharacterUseCase>(relaxed = true)

        val viewModel = HomeViewModel(useCase, getAppContext())

        viewModel.setDataSet(dataSet)

        every { dataSet.isNullOrEmpty() } returns true

        viewModel.fetchCharacters()
        shadowOf(getMainLooper()).idle()

        verify {
            useCase.fetchCharacters(0)
        }
    }

    @Test
    fun `When fetchCharacters is called and dataset not null or empty, verify if Layout state`() {
        val dataSet = mockk<ArrayList<Character>>(relaxed = true)

        val viewModel = HomeViewModel(mockk(), getAppContext())
        viewModel.setDataSet(dataSet)

        every { dataSet.isNullOrEmpty() } returns false

        viewModel.viewState.observeForever {}

        viewModel.fetchCharacters()
        shadowOf(getMainLooper()).idle()

        Assert.assertEquals(ViewState.State.LAYOUT, viewModel.viewState.value!!.state)

        viewModel.viewState.removeObserver { }
    }


}