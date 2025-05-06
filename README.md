# Weather Application 🌦️

A modern Android weather app built with Kotlin, Jetpack Compose, and Clean Architecture. The app provides real-time weather data and forecasts using the [Visual Crossing Weather API](https://www.visualcrossing.com/).

## Features ✨

- **Current Weather**: Displays real-time weather for user's current location
- **Weather Forecast**: Shows detailed forecast for upcoming days
- **Location Tracking**: Automatically updates location every:
  - 15 minutes **OR**
  - 100 meters distance change
- **Swipe to Refresh**: Pull-down gesture to update weather data
- **Configuration Change Handling**: Properly handles screen rotation and other config changes
- **Multi-flavor Support**:
  - **Free Version**: 5-day forecast
  - **Paid Version**: 14-day forecast
- **Icons**: uses the official icons set from api documentation

## Tech Stack 🛠️

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture + MVVM
- **API Client**: `HttpURLConnection`
- **JSON Parsing**: `JSONObject`
- **Location**: Android Location
- **Threading**:
  - Executor for background operations
  - Handler for main-thread communication
  
## API Integration 🌐

The app uses [Visual Crossing Weather API](https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/) with the following endpoints:

- weather: `/timeline/[latitude],[longitude]?unitGroup=metric&include=current`

## Screenshots 📸

| Current Weather | Weather Forecast |
|-----------------|------------------|
| ![Current Weather](https://github.com/user-attachments/assets/816e6616-d877-4f5f-97a7-5dc6a48310d8) | ![Forecast](https://github.com/user-attachments/assets/1b64abab-6a2e-4d19-9582-ebe1509755cb) |
| ![Current Weather Landscape](https://github.com/user-attachments/assets/5cb96cbe-e7a1-4dc9-9e5b-37983c61cf8a) | ![Forecast](https://github.com/user-attachments/assets/1614ab09-f13d-4c43-b686-fa4c8146eacb) |


## Getting Started 🚀

### Prerequisites

- Android Studio (latest version)
- Visual Crossing Weather API key

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/youssefelfeky1/Weather-App.git

