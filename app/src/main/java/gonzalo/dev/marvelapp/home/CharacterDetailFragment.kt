package gonzalo.dev.marvelapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import gonzalo.dev.core.domain.model.CharacterDataModel
import gonzalo.dev.marvelapp.common.util.FrescoUtils
import gonzalo.dev.marvelapp.databinding.CharactersDetailFragmentBinding

class CharacterDetailFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private val binding by lazy { CharactersDetailFragmentBinding.inflate(layoutInflater) }

    private var detail: CharacterDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            detail = it.getSerializable(EXTRA) as CharacterDataModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.characterDetailState.observe(viewLifecycleOwner) {
            if (viewModel.isDualPane) {
                detail = it
                updateUi(detail)
            }
        }

        updateUi(detail)
    }

    private fun updateUi(currentCharacter: CharacterDataModel?) {
        currentCharacter?.let {
            binding.detailPrimaryText.text = currentCharacter.name
            FrescoUtils.setImage(
                binding.detailThumb,
                "${currentCharacter.thumbnail.path}.${currentCharacter.thumbnail.extension}"
            )
            binding.detailBody.text = currentCharacter.description
        }
    }

    companion object {
        private const val EXTRA = "df_extra"

        fun newInstance(post: CharacterDataModel? = null) = CharacterDetailFragment().apply {
            post?.let {
                arguments = Bundle().apply { putSerializable(EXTRA, it) }
            }
        }
    }
}