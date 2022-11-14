package ge.space.ui.components.stepper

import androidx.viewpager2.widget.ViewPager2

class SPInternalPageChangeCallback(private val stepper: SPStepper) :
    ViewPager2.OnPageChangeCallback() {

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        stepper.onPageScrolled(
            position,
            positionOffset,
            positionOffsetPixels
        )
    }

    override fun onPageSelected(position: Int) {
        stepper.onPageSelected(position)
    }
}
