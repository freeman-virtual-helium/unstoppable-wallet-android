package io.horizontalsystems.bankwallet.lib.chartview.models

import android.content.Context
import io.horizontalsystems.bankwallet.R
import io.horizontalsystems.bankwallet.lib.chartview.ViewHelper

class ChartConfig(context: Context, viewHelper: ViewHelper) {
    //  colors
    var curveColor = context.getColor(R.color.red_warning)
    var touchColor = context.getColor(R.color.bars_color)
    var gridColor = context.getColor(R.color.steel_20)
    var textColor = context.getColor(R.color.grey)
    var growColor = context.getColor(R.color.green_crypto)
    var fallColor = context.getColor(R.color.red_warning)
    var indicatorColor = context.getColor(R.color.bars_color)

    //  dimens
    var textSize = viewHelper.dp2px(12f)
    var textPadding = viewHelper.dp2px(4f)
    var strokeWidth = viewHelper.dp2px(1f)
    var offsetRight = 0f
    var offsetBottom = viewHelper.dp2px(20f)

    //  Animation
    var animatedFraction = 0f
}