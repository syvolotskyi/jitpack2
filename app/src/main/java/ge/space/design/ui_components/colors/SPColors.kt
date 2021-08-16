package ge.space.design.ui_components.colors

import com.example.spacedesignsystem.R
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.isAccessible

typealias ColorRes = Pair<String, Int>

object Colors {

   val list: List<ColorRes> = R.color::class.members
        .filter { it is KProperty }
        .map { member ->
            val property = member as KProperty<Int>
            property.isAccessible = true
            val name = property.name
            val value = property.call()
            return@map ColorRes(name, value)
        }

}