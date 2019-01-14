OMDb Search App
===============

### Environment/Libraries
1. Android Studio 3.2.1
	* API version info: `Compile SDK Version: 28`; `Min SDK Version: 21`; `Target SDK Version: 28`
1. [Picasso](http://square.github.io/picasso/) - while there are other alternates like [Glide](https://bumptech.github.io/glide/), I'm most familiar with Picasso and have not seen much difference in these libraries apart from the fact the Glide's footprint is a little higher than Picasso's
1. [Retrofit](http://square.github.io/retrofit/) is the networking library of choice
1. [Mockito](https://site.mockito.org/) - a simple mocking framework that helps create unit tests. In this app, `OMDbPresenter`, `OMDbRepository` and `OMDbViewContract` are mocked
1. [PowerMock](https://github.com/powermock/powermock) - another mocking framework that mocks static, private and final methods of framework or library classes without using Dependency Injection. In this app, both `android.util.Log` and `retrofit2.Response` classes are mocked

### App Notes
1. The OMDb Search app fetches movie title results from [OMDb API](http://www.omdbapi.com/)
2. The app is built using the **Model-View-Presenter (MVP)** architecture where the core idea is a testable architecture by separating the application into various parts thus making the application easier to maintain and test individually
1. The app takes the approach of **package by layer** 
1. In the MVP architecture, the **View** (Activity) communicates with the **Presenter** for handling user interactions and the **Presenter** communicates with the **Model** to fetch the data from the APIs and present it back to the **View**
1. Tested the app on Pixel 2 (running OS 9) apart from an Emulator running API 26


### API Endpoint
1. OMDb endpoint appears to only return **10** results in a single API call and in order to get the next set of 10 results, you will need to add "page=2" as a query parameter, however, for the sake of this demo, I didn't enable pagination in the app 
1. The `Poster` URL may sometimes return **"N/A"** as opposed to a URL string so, I'm loading a placeholder image when the URL is not present
	* I did not look into other scenarios where the URL is not returned as expected

### Unit Tests
1. Ensured unit tests are written for **Presenter** which can be executed on the JVM
1. At this time, the demo doesn't handle Instrumentation tests