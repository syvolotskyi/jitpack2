package ge.space.design.ui_components.text_fields.dropdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import ge.space.ui.util.extension.onClick
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spacedesignsystem.databinding.SpExampleFragmentLayoutBinding
import ge.space.ui.components.bottomsheet.core.SPBottomSheetResultListener

class SPExampleFragment : Fragment(), SPBottomSheetResultListener<String> {

    private var dismiss: (String) -> Unit = {}

    private val binding by lazy {
        SpExampleFragmentLayoutBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.onClick {
            dismiss("Closed")
        }
    }

    override fun setBottomSheetResult(listener: (String) -> Unit) {
        dismiss = listener
    }
}