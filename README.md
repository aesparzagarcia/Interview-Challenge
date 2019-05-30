# Rappi Interview

## Package Name

> com.ares.rappiinterview

## Build atifacts

### RELEASE flavor

This flavor points to debug environments

* com.ares.rappiinterview.debug
* com.ares.rappiinterview.release

## Development Tools

* Gradle Plugin: Gradle 3.4.1
* IDE: Android Studio 3.4.1

## Android Versions

* Min SDK: 19 (Android 4.4)
* Target SDK: 28 (Android 9.0)

## Third party libraries

### Networking

* **Retrofit** | REST API call
	> http://square.github.io/retrofit/

* **OkHTTP** | HTTP Client
    > http://square.github.io/okhttp/

* **Glide** | Image loader
    > https://github.com/bumptech/glide/    

### Injection

* **Dagger2** | Dependency Injection
    > http://google.github.io/dagger/

### Utils
* **Tray** | Application preferences
    > https://github.com/grandcentrix/tray
    
* **Logger** | Custom logger with styles
    > https://github.com/orhanobut/logger

* **Room** | SQLite object mapping
	> https://developer.android.com/topic/libraries/architecture/room  

### Class descrption

* **Package core**
	> SessionManager: Class that handles SharedPreferences
	> TokenInterceptor: Class that validates the request_token and access_token to get a valid session. (Couldn`t apply this point due to is immposible to give permisions to token and get an access_token from the application thats why I have to use v3 of the API).

* **Package data**
	> db
		· MovieDao: Data Access Object
		· MovieDataBase: Maps the info into Database  
		· MovieRepository: Receives the info and send it to the Database
	> model
		· BaseResponse: The request token response, maps the info into this model
		·Details: Model that handles the Movie details info.
		·GrantType: Post body to get access_token
		·Movie: Model that handles the movie info.
		·TokenResponse: Model that handles the response info when request token.
	> DataRepository: Class that handles API calls info
	> Repository: Interface class that has the body of the RepositoryData class.
	> RestApiservice: Class that has the endpoints with body to call API.

* **Package Injection**		
	> activity
		·ActivityComponent: Enables ActivityModule that performs dependency injection.
		·ActivityModule: Defines classes that will be provided to ActivityComponent.
		·ActivityScope: Injects.
	> application
		·ApplicationComponent: Enables ApplicationModule that performs dependency injection.
		·ApplicationModule: Define classes that will be provided to ApplicationComponent.

* **Package section**
	> details
		·DetailsFragment: View of the screen Details of the movie
		·DetailsViewModel: Manage DetailsFragment UI-related Movie details data in a lifeCycle concius way.
	> main
		·iMovieClickListener: Listener that notify to adapter what Movie was clicked.
		·MainActivity: Handles Data binding whit in layout(layaout has the navigation between fragments).
		·MainFragment: View of the Movie list
		·MainViewModel: Manage MainFragment UI-related Movie data in a lifeCycle concius way.
		·MoviesAdapter: Class that handles info into in item of the list.

* **Package utils**
	> Constants: Class that handles all application constants.
	> Utils: Class that has many utility that the app will need like the method that verify if the app has internet.

### Questions
* **Unique responsability**
     > Determines how we should modularise code in OOP.
	 > Purpose: Develop our app with objects, separeted classes, use polimorfism, abstraction, encapsulation a heritage.
* **Clean code** 
	> Has clean architecture, good practices like well name defined to classes, methods, functions and variables. Independence of the classes, comments in classes, classes with not to much responsability, short methods, uses design patterns.



