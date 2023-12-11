## Brief
This Kotlin app needs to display the top 100 songs based on the iTunes API. The JSON feed is here: https://itunes.apple.com/us/rss/topalbums/limit=100/json

## Features
- Can retrieve top 100 list
- Display as list with thumbnail from API
- Can switch between GridView & ListView

## Screenshots

<img src="markdown_images/knockoffspotify.gif" width="200"> &emsp;&emsp;&emsp;&emsp; <img src="markdown_images/Screenshot_20231211_144744.png" width="200">&emsp;&emsp;&emsp;&emsp;<img src="markdown_images/Screenshot_20231211_144824.png" width="200"> 

## Tech stack
- Jetpack Compose
- Dagger Hilt
- Material Design 3
- Retrofit
- Kotlin Coroutines
- Turbine
- Paging Library
- Target SDK: 32

## Testing
- Testing is done following the testing pyramid with a given, when, then style 
- Unit testing is done in JUnit
- Kotlin Turbine

## Future features
- View more details when clicked
- Allow list to be searchable
- A Favorite option
- Can sort list items
- Menu option to access other API endpoints