package com.zebra.nilac.dwconfigurator.configuration.params.barcode

import android.os.Bundle
import android.util.Log
import com.zebra.nilac.dwconfigurator.models.barcode.upcean.UPCEANBookLandFormat
import com.zebra.nilac.dwconfigurator.models.barcode.upcean.UPCEANCouponReportMode
import com.zebra.nilac.dwconfigurator.models.barcode.upcean.UPCEANSecurityLevel
import com.zebra.nilac.dwconfigurator.models.barcode.upcean.UPCEANSupplementalMode
import java.util.Locale

class UPCEANParams private constructor(builder: Builder) {

    private val params = Bundle()

    init {
        params.apply {
            putString(
                DATABAR_TO_UPC_EAN_KEY,
                if (builder.dataBarToUpcEan) "true" else "false"
            )
            putString(
                UPC_ENABLE_MARGINLESS_DECODE_KEY,
                if (builder.enableMarginlessDecode) "true" else "false"
            )
            putString(
                UPC_EAN_SECURITY_LEVEL_KEY,
                String.format(
                    Locale.getDefault(),
                    "%d",
                    builder.securityLevel.ordinal
                )
            )
            putString(
                UPC_EAN_SUPPLEMENTAL2_KEY,
                if (builder.enableSupplemental2) "true" else "false"
            )
            putString(
                UPC_EAN_SUPPLEMENTAL5_KEY,
                if (builder.enableSupplemental5) "true" else "false"
            )
            putString(
                UPC_EAN_SUPPLEMENTAL_MODE_KEY,
                String.format(
                    Locale.getDefault(),
                    "%d",
                    builder.supplementalMode.ordinal
                )
            )
            putString(
                UPC_EAN_RETRY_COUNT_KEY,
                builder.retryCount.toString()
            )
            putString(
                UPC_EAN_LINEAR_DECODE_KEY,
                if (builder.enableLinearDecode) "true" else "false"
            )
            putString(
                UPC_EAN_ZERO_EXTEND_KEY,
                if (builder.enableEanZeroExtend) "true" else "false"
            )
            putString(
                UPC_EAN_BOOKLAND_KEY,
                if (builder.enableBookLand) "true" else "false"
            )
            putString(
                UPC_EAN_BOOKLAND_FORMAT_KEY,
                String.format(
                    Locale.getDefault(),
                    "%d",
                    builder.bookLandFormat.ordinal
                )
            )
            putString(
                UPC_EAN_COUPON_KEY,
                if (builder.enableCoupon) "true" else "false"
            )
            putString(
                UPC_EAN_COUPON_REPORT_KEY,
                String.format(
                    Locale.getDefault(),
                    "%d",
                    builder.couponReportMode.ordinal
                )
            )
        }
    }

    class Builder {
        internal var dataBarToUpcEan = false
        internal var enableMarginlessDecode = false
        internal var securityLevel = UPCEANSecurityLevel.LEVEL_1
        internal var enableSupplemental2 = true
        internal var enableSupplemental5 = true
        internal var supplementalMode = UPCEANSupplementalMode.NO_SUPPLEMENTAL
        internal var retryCount = 10
        internal var enableLinearDecode = true
        internal var enableEanZeroExtend = false
        internal var enableBookLand = false
        internal var bookLandFormat = UPCEANBookLandFormat.ISBN_10
        internal var enableCoupon = false
        internal var couponReportMode = UPCEANCouponReportMode.BOTH_COUPON_MODES

        fun enableDatabarToUpcEan(state: Boolean): Builder =
            apply { this.dataBarToUpcEan = state }

        fun enableMarginlessDecode(state: Boolean): Builder =
            apply { this.enableMarginlessDecode = false }

        fun setSecurityLevel(securityLevel: UPCEANSecurityLevel): Builder =
            apply { this.securityLevel = securityLevel }

        fun enableSupplemental2(state: Boolean): Builder =
            apply { this.enableSupplemental2 = state }

        fun enableSupplemental5(state: Boolean): Builder =
            apply { this.enableSupplemental5 = state }

        fun setSupplementalMode(supplementalMode: UPCEANSupplementalMode): Builder =
            apply { this.supplementalMode = supplementalMode }

        fun setRetryCount(retryCount: Int): Builder =
            apply {
                this.retryCount = if (retryCount > 20) {
                    Log.w(TAG, "Warning invalid retry count number, setting to 20")
                    20
                } else {
                    retryCount
                }
            }

        fun enableLinearDecode(state: Boolean): Builder =
            apply { this.enableLinearDecode = state }

        fun enableZeroEanExtend(state: Boolean): Builder =
            apply { this.enableEanZeroExtend = state }

        fun enableBookLand(state: Boolean): Builder =
            apply { this.enableBookLand = state }

        fun setBookLandFormat(format: UPCEANBookLandFormat): Builder =
            apply { this.bookLandFormat = format }

        fun enableCoupon(state: Boolean): Builder =
            apply { this.enableCoupon = state }

        fun enableCouponReportMode(reportMode: UPCEANCouponReportMode): Builder =
            apply { this.couponReportMode = reportMode }

        fun create(): Bundle {
            return UPCEANParams(this).params
        }
    }

    companion object {
        private const val TAG = "UPCEANParams"

        internal const val DATABAR_TO_UPC_EAN_KEY = "databar_to_upc_ean"
        internal const val UPC_ENABLE_MARGINLESS_DECODE_KEY = "upc_enable_marginless_decode"
        internal const val UPC_EAN_SECURITY_LEVEL_KEY = "upcean_security_level"
        internal const val UPC_EAN_SUPPLEMENTAL2_KEY = "upcean_supplemental2"
        internal const val UPC_EAN_SUPPLEMENTAL5_KEY = "upcean_supplemental5"
        internal const val UPC_EAN_SUPPLEMENTAL_MODE_KEY = "upcean_supplemental_mode"
        internal const val UPC_EAN_RETRY_COUNT_KEY = "upcean_retry_count"
        internal const val UPC_EAN_LINEAR_DECODE_KEY = "upcean_linear_decode"
        internal const val UPC_EAN_ZERO_EXTEND_KEY = "upcean_ean_zero_extend"
        internal const val UPC_EAN_BOOKLAND_KEY = "upcean_bookland"
        internal const val UPC_EAN_BOOKLAND_FORMAT_KEY = "upcean_bookland_format"
        internal const val UPC_EAN_COUPON_KEY = "upcean_coupon"
        internal const val UPC_EAN_COUPON_REPORT_KEY = "upcean_coupon_report"
    }
}