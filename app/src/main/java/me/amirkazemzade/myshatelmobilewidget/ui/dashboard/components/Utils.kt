package me.amirkazemzade.myshatelmobilewidget.ui.dashboard.components

import androidx.compose.ui.Modifier
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
@JvmSynthetic
inline fun Modifier.whatIfMap(
    given: Boolean?,
    whatIf: (Modifier) -> Modifier,
): Modifier {
    contract {
        callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    }
    if (given == true) {
        return whatIf(this)
    }
    return this
}