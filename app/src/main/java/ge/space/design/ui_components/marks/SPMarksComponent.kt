package ge.space.design.ui_components.marks

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemMarkLayoutBinding
import com.example.spacedesignsystem.databinding.SpLayoutMarksShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.marks.SPMark
import ge.space.ui.util.view_factory.SPViewData

class SPMarksComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.marks

    override fun getDescriptionResId(): Int = R.string.marks_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutMarksShowcaseBinding.inflate(environment.requireLayoutInflater())

            SPMarkStyles.list.onEach { markSample ->
                val itemBinding = SpItemMarkLayoutBinding.inflate(
                    environment.requireLayoutInflater(),
                    binding.fieldsLayout,
                    true
                )
                itemBinding.apply {
                    setOf(
                        mark1,
                        mark2,
                        mark3,
                        mark4,
                    ).forEach { it.setViewStyle(markSample) }
                    mark1.setViewData(
                        SPViewData.SPImageUrlData("https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6db62996-9069-488a-b7db-0f279ae84346/BrandBank_of_Georgia_Size20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20211013%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211013T071446Z&X-Amz-Expires=86400&X-Amz-Signature=9c6e7bf88fc79d61cf30f8686562edd53c40ba1aba1c4394273228eb5e522224&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22BrandBank_of_Georgia_Size20.png%22")
                    )
                    mark2.setViewData(
                        SPViewData.SPTextInitialsData("VS")
                    )
                    mark3.setViewData(
                        SPViewData.SPImageUrlData("https://s3.us-west-2.amazonaws.com/secure.notion-static.com/8ceb4424-b0fc-4cde-8963-4dbc4226e0ba/Size20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20211013%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211013T071300Z&X-Amz-Expires=86400&X-Amz-Signature=497f6609881cdf3fe8b2421670d28cf50324b82081291eea039d2bcae55ce240&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Size20.png%22")
                    )
                    mark4.setViewData( SPViewData.SPImageResourcesData(R.drawable.ic_plus_16_regular))
                }

            }
            return binding.root
        }

    }

}