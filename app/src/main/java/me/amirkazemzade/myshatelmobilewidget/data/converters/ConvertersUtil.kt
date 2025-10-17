package me.amirkazemzade.myshatelmobilewidget.data.converters

import me.amirkazemzade.myshatelmobilewidget.data.models.responses.CaptchaResponse
import me.amirkazemzade.myshatelmobilewidget.data.models.responses.PackagesResponse
import me.amirkazemzade.myshatelmobilewidget.data.models.responses.PackagesResponseItem
import me.amirkazemzade.myshatelmobilewidget.data.models.responses.RemainedResponse
import me.amirkazemzade.myshatelmobilewidget.domain.models.CaptchaBase64
import me.amirkazemzade.myshatelmobilewidget.domain.models.Cookie
import me.amirkazemzade.myshatelmobilewidget.domain.models.InternetPackage
import me.amirkazemzade.myshatelmobilewidget.domain.models.Remained
import me.amirkazemzade.myshatelmobilewidget.domain.models.RialPrice
import me.amirkazemzade.myshatelmobilewidget.domain.models.Traffic
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun CaptchaResponse.toCaptchaBase64() =
    CaptchaBase64(value = captcha)

fun Long.toTraffic() = Traffic(amountInMb = this)

fun String.toDurationUnit() = when (this) {
    "hour", "hours", "h" -> DurationUnit.HOURS
    "day", "days", "d" -> DurationUnit.DAYS
    "month", "months", "m" -> DurationUnit.DAYS
    "year", "years", "y" -> DurationUnit.DAYS
    else -> throw Exception("Unknown duration unit of `$this`")
}

fun Long.toRialPrice() = RialPrice(value = this)

fun PackagesResponseItem.toPackage() = InternetPackage(
    traffic = detail.internetTraffic.toTraffic(),
    duration = durationValue.toDuration(durationUnit.toDurationUnit()),
    price = price.toRialPrice(),
)

fun PackagesResponse.toPackagesArray(): Array<InternetPackage> =
    map { it.toPackage() }.toTypedArray()

fun String.toTraffic(unit: String) = Traffic(
    amountInMb = when (unit) {
        "ترابایت" -> toDouble() * 1024 * 1024
        "گیگابایت" -> toDouble() * 1024
        "مگابایت" -> toLong()
        else -> throw Exception("Unknown unit of `$unit`")
    }.toLong()
)

fun RemainedResponse.toRemained() = Remained(
    traffic = internetUsed.toTraffic(unit = internetUsedUnit)
)

fun String.toCookie(): Cookie {
    val split = trim().split(";")
    val map = split
        .associate {
            val parts = it.trim().split("=")
            parts[0].trim() to parts[1].trim()
        }

    return Cookie(
        session = map["session"]!!,
        path = map["Path"]!!,
        expires = map["Expires"]!!
    )
}

fun Cookie.toHeaderValue(): String = "session=$session"