package ge.space.design.ui_components.profile

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemMarkLayoutBinding
import com.example.spacedesignsystem.databinding.SpLayoutProfileShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.marks.SPMarkStyles
import ge.space.ui.components.profile.SPProfileHeadingView
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.view_factory.SPViewData

class SPProfileComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.profile_showcase

    override fun getDescriptionResId(): Int = R.string.profile_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutProfileShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            ).apply {
                val context = environment.context
                profile1.setViewData(SPViewData.SPImageUrlData(SPProfileHeadingStyles.profileURL))
                profile2.setViewData(SPViewData.SPImageUrlData(SPProfileHeadingStyles.profileURL))
                profile3.setViewData(SPViewData.SPTextData("VS"))

                SPProfileHeadingStyles.list.onEach { markSample ->
                   profileProgLayout.addView(  SPProfileHeadingView(environment.context).apply {
                        setViewStyle(markSample.resId)
                        markSample.profileUrl?.let { setViewData(SPViewData.SPImageUrlData(it)) }
                        markSample.title?.let { titleText = context.getString(it) }
                        markSample.description?.let { descText = context.getString(it) }
                        markSample.defaultIcon?.let { defaultMarkImage = it }
                    })

                }
            }
            return layoutBinding.root
        }
    }
}
