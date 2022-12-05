package ge.space.design.ui_components.text_fields.dropdown.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spacedesignsystem.databinding.SpTextExampleFragmentLayoutBinding
import ge.space.ui.components.bottomsheet.navigation.SPBottomSheetScreen
import ge.space.ui.components.bottomsheet.navigation.router.SPBottomSheetRouter
import ge.space.ui.components.bottomsheet.strategy.SPFragmentSheetStrategy
import org.koin.android.ext.android.inject
import ge.space.ui.util.extension.onClick
import ge.space.ui.util.extension.*

class SPTextExampleFragment : Fragment() {

    private val binding by lazy {
        SpTextExampleFragmentLayoutBinding.inflate(LayoutInflater.from(context))
    }

    private val router: SPBottomSheetRouter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hideKeyboardButton.onClick { binding.inputTextView.hideKeyboard() }

        binding.nextButton.onClick {
            router.openScreen(SPBottomSheetScreen { SPSucceessExampleFragment() })
        }
        binding.backButton.onClick {
            router.exit()
        }
    }
}