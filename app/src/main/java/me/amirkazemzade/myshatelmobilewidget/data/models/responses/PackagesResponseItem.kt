package me.amirkazemzade.myshatelmobilewidget.data.models.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PackagesResponseItem(
    @SerialName("adsl")
    val adsl: Boolean,
    @SerialName("balance_payment_package_id")
    val balancePaymentPackageId: String,
    @SerialName("bank_payment_package_id")
    val bankPaymentPackageId: String,
    @SerialName("cra_package_serial")
    val craPackageSerial: String?,
    @SerialName("cra_validation_link")
    val craValidationLink: String?,
    @SerialName("cut_rate")
    val cutRate: Boolean,
    @SerialName("detail")
    val detail: Detail,
    @SerialName("dsl_directory_id")
    val dslDirectoryId: String?,
    @SerialName("duration")
    val duration: Int,
    @SerialName("duration_title")
    val durationTitle: DurationTitle,
    @SerialName("duration_unit")
    val durationUnit: String,
    @SerialName("duration_value")
    val durationValue: Int,
    @SerialName("features")
    val features: List<Feature>,
    @SerialName("filters")
    val filters: List<String?>,
    @SerialName("gifts")
    val gifts: List<Gift>,
    @SerialName("have_plus_gift")
    val havePlusGift: Boolean,
    @SerialName("high_priority")
    val highPriority: Boolean,
    @SerialName("id")
    val id: Int,
    @SerialName("images")
    val images: List<String?>,
    @SerialName("new_duration_unit")
    val newDurationUnit: String,
    @SerialName("new_duration_value")
    val newDurationValue: Int,
    @SerialName("p_types")
    val pTypes: List<String>,
    @SerialName("price")
    val price: Long,
    @SerialName("qos")
    val qos: Int,
    @SerialName("renewable")
    val renewable: Boolean,
    @SerialName("rollover")
    val rollover: Boolean,
    @SerialName("scope")
    val scope: String,
    @SerialName("test_package")
    val testPackage: Boolean,
    @SerialName("title_ar")
    val titleAr: String,
    @SerialName("title_en")
    val titleEn: String,
    @SerialName("title_fa")
    val titleFa: String,
    @SerialName("type")
    val type: String,
    @SerialName("types")
    val types: List<String>,
    @SerialName("ussd")
    val ussd: String
)