package com.github.wakingrufus.website.lib.slides

import kotlinx.html.DIV
import kotlinx.html.HTML

class Slide(val title: String? = null,
            val subTitle: String? = null,
            val template: (mainCssPath: String,
                           title: String,
                           subTitle: String?,
                           slideNumber: Int,
                           totalSlides: Int,
                           content: DIV.() -> Unit) -> HTML.() -> Unit,
            val content: DIV.() -> Unit)

class SLIDE{
    var title: String? = null
    var subTitle: String? = null
  private  var template: (mainCssPath: String,
                   title: String,
                   subTitle: String?,
                   slideNumber: Int,
                   totalSlides: Int,
                   content: DIV.() -> Unit) -> HTML.() -> Unit = ::slideTemplate
  private  var content: DIV.() -> Unit = {}

    fun content(builder: DIV.() -> Unit){
        content = builder
    }

    operator fun invoke(config: (SLIDE.() -> Unit)? = {}) : Slide{
        return Slide(title, subTitle,template,content)
    }
}
