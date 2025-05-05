package com.elfeky.weather_app.presentation.ui.forecast_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.elfeky.weather_app.domain.models.DayForecast
import com.elfeky.weather_app.presentation.WeatherViewModel
import com.elfeky.weather_app.presentation.ui.theme.SemiTransparentWhite

@Composable
fun ForecastItem(
    modifier: Modifier = Modifier,
    dayForecast: DayForecast,
    viewModel: WeatherViewModel,
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(SemiTransparentWhite)
            .padding(8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = viewModel.parseIcon(context,dayForecast.icon)),
            contentDescription = dayForecast.description,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = viewModel.getDayOfWeek(dayForecast.datetime),
                color = Color.White
            )
            Row {
                Text(text = "${dayForecast.tempmin}°C / ",color = Color.White)
                Text(text = "${dayForecast.tempmax}°C",color = Color.White)
            }
            Text(text = dayForecast.description,color = Color.White)
        }
    }
}