package com.elfeky.weather_app.presentation.ui.weather_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elfeky.weather_app.R
import com.elfeky.weather_app.domain.models.DayForecast
import com.elfeky.weather_app.presentation.navigation.AppScreen
import com.elfeky.weather_app.presentation.ui.theme.SemiTransparentWhite

@Composable
fun WeatherDetailsSection(
    modifier: Modifier = Modifier,
    navController: NavController,
    dayForecast: DayForecast
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = SemiTransparentWhite
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(AppScreen.ForecastScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconWithText(
                    imageVector = ImageVector.vectorResource(R.drawable.calender),
                    text = "Forecast for the coming days"
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            HorizontalDivider(
                Modifier
                    .height(6.dp)
                    .padding(8.dp)
            )
            Spacer(Modifier.height(8.dp))
            DetailsItem(
                icon = ImageVector.vectorResource(R.drawable.humidity),
                text = "Humidity",
                value = "${dayForecast.humidity} %",
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
            DetailsItem(
                icon = ImageVector.vectorResource(R.drawable.air),
                text = "Wind speed",
                value = dayForecast.windspeed.toString()+" Km/h",
                modifier = Modifier.padding(vertical = 4.dp,horizontal = 8.dp)
            )
            DetailsItem(
                icon = ImageVector.vectorResource(R.drawable.thermometer_hot),
                text = "Feels like",
                value = dayForecast.feelslike.toString()+" °C",
                modifier = Modifier.padding(vertical = 4.dp,horizontal = 8.dp)
            )
            DetailsItem(
                icon = ImageVector.vectorResource(R.drawable.uv_index),
                text = "UV",
                value = dayForecast.uvindex.toString(),
                modifier = Modifier.padding(vertical = 4.dp,horizontal = 8.dp)
            )
            DetailsItem(
                icon = ImageVector.vectorResource(R.drawable.sunrise_over_mountains),
                text = "Sunrise",
                value = dayForecast.sunrise,
                modifier = Modifier.padding(vertical = 4.dp,horizontal = 8.dp)
            )
            DetailsItem(
                icon = ImageVector.vectorResource(R.drawable.sunset),
                text = "Sunset",
                value = dayForecast.sunset,
                modifier = Modifier.padding(vertical = 4.dp,horizontal = 8.dp)
            )
            DetailsItem(
                icon = ImageVector.vectorResource(R.drawable.pressure_light),
                text = "Pressure",
                value = dayForecast.pressure.toString()+" mb",
                modifier = Modifier.padding(vertical = 4.dp,horizontal = 8.dp)
            )
            DetailsItem(
                icon = ImageVector.vectorResource(R.drawable.visibility),
                text = "Visibility",
                value = dayForecast.visibility.toString()+" Km",
                modifier = Modifier.padding(vertical = 4.dp,horizontal = 8.dp)
            )
            DetailsItem(
                icon = ImageVector.vectorResource(R.drawable.weather_rain_1_cloud_rain_rainy_meteorology_precipitation),
                text = "precipitation",
                value = dayForecast.precipprob.toString()+" %",
                modifier = Modifier.padding(vertical = 4.dp,horizontal = 8.dp)
            )

        }
    }
}

@Composable
fun IconWithText(modifier: Modifier = Modifier, imageVector: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = text,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DetailsItem(modifier: Modifier = Modifier, icon: ImageVector, text: String, value: String) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconWithText(imageVector = icon, text = text)
        Text(text = value, color = Color.White)

    }
}