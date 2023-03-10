package fi.danielz.hslbussin.presentation.routeselection.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import com.apollographql.apollo3.api.Error
import fi.danielz.hslbussin.compose.ErrorBanner
import fi.danielz.hslbussin.compose.IconRow
import fi.danielz.hslbussin.compose.SelectionHeaderWithLoadingIndicator
import fi.danielz.hslbussin.presentation.routeselection.model.RouteData
import fi.danielz.hslbussin.presentation.theme.HSLBussinTheme

typealias RouteClick = (RouteData) -> Unit

@ExperimentalAnimationApi
@Composable
fun RouteSelectionScreen(
    routesState: State<List<RouteData>>,
    errorsState: State<List<Error>?>,
    onRouteSelectedClick: RouteClick,
) {
    HSLBussinTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            ErrorBanner(errorState = errorsState)
            ScalingLazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // add extra item for header
                items(routesState.value.size + 1) {
                    if (it == 0) {
                        SelectionHeaderWithLoadingIndicator(
                            routesState,
                            errorsState,
                            "Select bus route",
                            "Loading routes..."
                        )
                    } else {
                        val adjustedIndex = it - 1
                        IconRow(
                            item = routesState.value[adjustedIndex],
                            onClick = onRouteSelectedClick,
                            imageVector = Icons.Default.DirectionsBus,
                            text = { it.name }
                        )
                    }
                }
            }
        }
    }
}
