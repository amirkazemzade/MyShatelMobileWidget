package me.amirkazemzade.myshatelmobilewidget.widgets.remained

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class RemainedGlanceWidgetReceiver1x4 : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = RemainedGlanceWidget1x4()
}