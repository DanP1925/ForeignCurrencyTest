# Android Take Home Test
## Assignment:

Your task is to build an Android Application that can convert Foreign Currency using this free API: [Fixer](https://fixer.io/documentation). The application will contain 2 screens:

### 1st screen: 

Will load the [Supported Symbols Endpoint](https://fixer.io/documentation#supportedsymbols) and display a vertical list of all supported countries along with their 3-letter currency symbol.

```
A list item should look like this 
-------------------------
|  AMD |  Armenian Dram |
-------------------------
```

When the user clicks on a list item (that is selecting a currency symbol), we will open our 2nd screen and use the currency symbol as the "base" currency to convert to multiple other currencies.

> NOTE: FIXER Free Plan will only accept EUR as the selected base currency, so the candidate should use EUR to test for the happy path. When selecting any other currency symbol, the api will send back such an error `{"success":false,"error":{"code":105,"type":"base_currency_access_restricted"}}`. Please parse any error type and display it plainly in an Alert Dialog via DialogFragment.   

> Senior Level: implement a search text box on the top of the list to quickly search for the base currency.


### 2nd screen contains: 
+ 1 EditText will always be fixed at the top that should only accept numbers, in which the user can enter the amount that he/she wants to convert to.
+ 1 RecyclerView to display all converted currency that are returned by the [Latest Rates Endpoint](https://fixer.io/documentation#latestrates). The list should have the 3-letter currency on the left following by the converted amount which should be rounded to the decimal places. 

```
If a user enters 3 in the EditText for EUR as Base Currency
A list item should look like this 
-------------------------
|  USD |  3.59          |
-------------------------
```
> Senior Level: display the currency symbol next to the amount for all currencies. For example: USD would be $3.59


### The application must follow:
- Best Android Development Practice (The cleaner your code is, the easier you can write unit tests)
- Error handling
- MVVM Arch
- All codes must be in KOTLIN
- Use Retrofit with either RxJava or Coroutines Flow.
- *Unit Test to cover your code as much as possible. (Perhaps, using [Robolectric](http://robolectric.org/) to test your Android code)*

> Senior Level: also implementing a simple cache (can just use SharedPreferences) that expired in 30 minutes.

# Evaluation:
+ We will pay particular attention to the way the code is organized, and to the overall readability
+ Unit tests will be GREATLY appreciated. Keep in mind that we grade the project on how well the unit test coverage is.
+ Design will be ignored, however usability and accessibility will be taken into consideration
+ Remember to update this README with instructions on how to install, run and test your application
+ We hope that you can complete the assignment within 2-3 hours but don't set any time constraints
+ Please reach out per email or by opening an issue if anything is unclear or blocking you

> BEST OF LUCK 

# Dev Notes

+ **Coroutines** : A concurrency design pattern included as part of Kotlin. It is flexible enough because you can declare both the scope and the thread where it is running. Also it uses suspend functions, so it is very explicit which methods can run on the coroutine. I decided to use this because the project was all in Kotlin and it is the recommended way by Google.
+ **Flow** : A flow is a type that can emit multiple values sequentially. It lets you transform the values if needed and handle errors. I decided to use flow because it lets you already handle errors and it works with coroutines.
+ **Dagger Hilt** : A dependency injection library included in Jetpack. I decided to use this because it is the recommended library from Google. In addition, I think it is simpler than Dagger so for this small project where I won't need the more complex features of Dagger it is good enough.
+ **Navigation** : A library that helps with the navigation between activities and fragment. It also is useful to define the parameters needed to go to a new fragment so when someone has to maintain the code, it will be easier for him to know what to pass. To use it, it needs some restructuring of the layouts but because this is a new project that wasn't a problem.
+ **Retrofit** : A HTTP client. It is a robust library that has support for suspending functions so is perfect to work with coroutines. It is very flexible, can be configured and the way to declare requests is very explicit through interfaces.
+ **Moshi** : A library for parsing JSON objects. I decided to use it because of its annotation features where I only had to add them to a class  and then it will know how to handle the parsing. I had an issue when trying to parse Map<String,Double> where the app didn't crash but didn't finish the parse either.
+ **Truth** : A library for performing assertions in tests. I decided to use it because I prefer the failure messages that it displays whenever a test fails.
+ **Arch core** : A library included in Jetpack  needed for testing view models on unit tests.
+ **Mockito** : A library for producing mocks for tests. It is useful because you can verify the interactions with the mocks and even create stubs that can replace classes for tests.


## How to run app & test
+ How to run the app:
    + Install the app on the phone
    + On the first screen select EUR
    + On the second screen add a number
    + Go back to the first screen
    + On the first screen select a different currency
    + On the second screen press ok on the Error Message

+ How to test the app:
    + Open the app on Android Studio
    + On the src/test folder do a right click to open other options
    + Select run Tests in foreignCurrency
    + On the src/androidTest folder do a right click to open other options
    + Select run Tests in com.example.foreigncurrency

## Future Improvement
+ Code Structuring: 
    + Divide the AppModule into separates modules. The new modules could be organized into features.
    + Create a package SharedTest to put the files that are both used by the integration tests and the unit tests.
    + Add different product flavors to handle different environments and code that should only run on each one.
+ Refactoring:
    + Change the component scopes of the provides methods for classes that shouldn't be singletons.
    + Change the provides to binds for methods of the AppModule that return an interface so different classes could be returned depending on the context.
    + Create a class that will handle the different states (success, loading, error) of a base response from an endpoint.
    + Find a way to solve the issue with Moshi trying to parse Map<String, Double>. If a soluton can't be find then find a library to replace it.
+ Additional Features:
    + Add a database to store the last exchange rates obtained so the app can work without a connection to the internet.
    + Update the exchange rates if the endpoint updates them while the app is running.
    + Add obfuscation so it will be harder to obtain the API key from the source code. Currently it is stored as a base64 format so it has to be decoded beforehand but I don't think this is enough.