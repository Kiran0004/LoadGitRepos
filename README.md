# LoadGitRepos

This project will list the most starred Github repos that were created in the last 30 days. 
You'll be fetching the sorted JSON data directly from the Github API

 This application has following components
 
A web service API.
A View that provides data specific for the UI.
Unit Test cases for API service, View.

App Packages
api - contains the api classes to make api calls to FreshlyPressed server, using Retrofit.
model - contains dependency injection classes, using Dagger2.
adapter,ui - contains classes needed to display Activity.
util - contains classes needed for activity redirection, ui/ux animations.

App Specs

Minimum SDK 15
Java8 (in master branch) & Kotlin (in kotlin_support branch)
Android Architecture Components 
RxJava2 for implementing Observable pattern.
Retrofit 2 for API integration.
Gson for serialisation.
Okhhtp3 for implementing interceptor, logging and mocking web server.
Mockito for implementing unit test cases
Picasso for image loading.
