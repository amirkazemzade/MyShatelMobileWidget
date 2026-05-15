package me.amirkazemzade.myshatelmobilewidget.domain.models

enum class DataDisplayMode {
    PERCENTAGE,
    TRAFFIC;

    companion object {
        fun valueOfOrNull(value: String): DataDisplayMode? {
            return runCatching {
                valueOf(value = value)
            }.getOrNull()
        }
    }
}