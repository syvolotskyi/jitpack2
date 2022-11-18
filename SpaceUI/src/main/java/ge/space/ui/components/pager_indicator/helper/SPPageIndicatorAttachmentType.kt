package ge.space.ui.components.pager_indicator.helper

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

/**
 * [SPPageIndicatorAttachmentType] is a sealed class which denotes what attachment type we have for our [SPPageIndicatorAttachmentType]
 *
 * We support three types:
 * - [SPViewPager] for ViewPager 1
 * - [SPViewPager2] for ViewPager 2
 * - [SPRecyclerView] for ViewPager 2
 */
sealed class SPPageIndicatorAttachmentType {

    data class SPViewPager(val viewPager: ViewPager): SPPageIndicatorAttachmentType()

    data class SPViewPager2(val viewPager2: ViewPager2): SPPageIndicatorAttachmentType()

    data class SPRecyclerView(val recyclerView: RecyclerView): SPPageIndicatorAttachmentType()

}