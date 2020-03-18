# KoinMVVMRetrofit
Koin is new lightweight dependency injections(DI) framework for kotlin.It perform lazy injections which means when we use any module then module inject in our activity or fragment.
In this repo, i defined how to use Koin DI framework in kotlin with MVVM and retrofit for API calls.

First of all, We create our project structure which you can see by using https://github.com/sky-flutter/KoinMVVMRetrofit/tree/master/app/src/main/java/com/example/koinmvvmretrofit this link.
For all end point, we created a interface where we defined all our end points and required parameters.
Create a ViewModel from where we can execute our network calls.
Then all module we created inject using dependency injections.

# For dependency injections, we need to add below dependency in our app level gradle.
implementation 'org.koin:koin-android-viewmodel:2.0.1'

# In di folder,all our dependency injections are apply.<br/>
apiModule : In this file, create a instance of our api end points interface.<br/>
appModule : Where we add shared preference manager for storing data locally.<br/>
networkModule : This file used for setting up the retrofit, OkHttp client, logging interceptor etc..<br/>
viewModelModule : It injects all viewmodel we have used in our projects.<br/>

Add all module in our application class where we start koin to add this module for dependency injection.
In our activity class we are using viewmodel to get fetched repos and display to recyclerview.

Thank You.
