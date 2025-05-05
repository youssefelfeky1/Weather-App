package com.elfeky.weather_app.presentation.ui.weather_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elfeky.weather_app.domain.models.DayForecast
import com.elfeky.weather_app.presentation.WeatherViewModel

@Composable
fun CurrentWeatherSection(
    modifier: Modifier = Modifier,
    dayForecast: DayForecast,
    viewModel: WeatherViewModel
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = viewModel.parseIcon(context,dayForecast.icon)),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(104.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "${dayForecast.temp} °C",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = dayForecast.icon.replace("-", " ").uppercase(),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "H:${dayForecast.tempmax} °C / L:${dayForecast.tempmin} °C",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = dayForecast.description,
                color = Color.White,
                fontSize = 14.sp,
            )
        }

    }
}