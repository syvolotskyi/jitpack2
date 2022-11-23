package ge.space.design.ui_components.text_fields.dropdown.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spacedesignsystem.databinding.SpSucceessExampleFragmentLayoutBinding
import androidx.fragment.app.Fragment
import ge.space.ui.components.bottomsheet.navigation.router.SPBottomSheetRouter
import ge.space.ui.components.bottomsheet.strategy.SPFragmentSheetStrategy
import org.koin.android.ext.android.inject
import ge.space.ui.util.extension.onClick

class SPSucceessExampleFragment : Fragment() {

    private val binding by lazy {
        SpSucceessExampleFragmentLayoutBinding.inflate(LayoutInflater.from(context))
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

        binding.backButton.onClick {
            router.exit()
        }
    }
}


