package ge.space.design.ui_components.profile

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutProfileShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.profile.SPProfileHeadingView
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
                profile1.setIconViewData(SPViewData.SPImageUrlData(SPProfileHeadingStyles.profileURL))
                profile1.setOnProfileClickListener { Toast.makeText(context, "First Profile", Toast.LENGTH_SHORT).show() }

                profile2.setIconViewData(SPViewData.SPImageUrlData(SPProfileHeadingStyles.profileURL))
                profile2.setOnProfileClickListener { Toast.makeText(context, "Second Profile", Toast.LENGTH_SHORT).show() }

                profile3.setIconViewData(SPViewData.SPTextData("VS"))
                profile3.setOnProfileClickListener { Toast.makeText(context, "Third Profile", Toast.LENGTH_SHORT).show() }
                profile4.setOnProfileClickListener { Toast.makeText(context, "Fourth Profile", Toast.LENGTH_SHORT).show() }

                SPProfileHeadingStyles.list.onEach { markSample ->
                   profileProgLayout.addView(  SPProfileHeadingView(environment.context).apply {
                        setViewStyle(markSample.resId)
                        markSample.profileUrl?.let { setIconViewData(SPViewData.SPImageUrlData(it)) }
                        markSample.title?.let { setTitle(context.getString(it)) }
                        markSample.description?.let { setDescription(context.getString(it)) }
                        markSample.defaultIcon?.let { defaultMarkImage = it }
                       setOnProfileClickListener { Toast.makeText(context, markSample.resId.toString(), Toast.LENGTH_SHORT).show()  }
                    })

                }
            }
            return layoutBinding.root
        }
    }
}
