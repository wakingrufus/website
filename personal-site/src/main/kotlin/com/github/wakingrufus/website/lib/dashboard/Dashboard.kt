package com.github.wakingrufus.website.lib.dashboard

import com.github.wakingrufus.website.WebsiteDsl
import kotlinx.html.DIV

@WebsiteDsl
class Dashboard {
    private val panels = mutableListOf<DashboardPanel>()

    fun panel(title: String?, content: DIV.() -> Unit) {
        panels.add(DashboardPanel().apply {
            title?.let { title(it) }
            content(content)
        })
    }

    fun topicPanel(title: String?, content: DashboardPanel.() -> Unit) {
        panels.add(DashboardPanel().apply {
            title?.also {
                this.title(it) }
           this.content()
        })
    }

    operator fun invoke(div: DIV) {
        panels.forEach { it(div) }
    }
}

