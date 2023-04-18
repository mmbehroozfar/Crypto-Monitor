# Crypto Monitor 

<img src="https://github.com/mmbehroozfar/Crypto-Monitor/blob/master/pictures/preview.gif?raw=true" alt="Logo" align="right" height="600">

This app allows users to track crypto currency assets. It provides the following features:

- Showcasing all crypto currency assets: Users can view a list of all available crypto currency assets.
- Adding assets to favorites: Users can mark assets as favorites for quick access.
- Search assets by name: Users can search for assets by name to easily find specific assets.
- Showing latest price and details: Users can view the latest price and other details of each asset.
- Offline first: The app is designed to work offline, allowing users to access their favorite assets even without internet connectivity.

# Android development

I used the most latest technologies for developing this application.

Here is a list of technologies and tools that is used in this project.

- Kotlin: The app is written entirely in Kotlin, a modern and expressive programming language for Android development.
- Kotlin Coroutines: Coroutines are used throughout the app for efficient and asynchronous handling of tasks.
- Clean MVVM Architecture: The app follows the Clean MVVM architecture pattern, which separates concerns and improves maintainability.
- Architecture Components: The app uses various components from the Android Architecture Components library, such as Room, Lifecycle, Navigation, ViewModel, and Paging.
- Hilt for Dependency Injection: Hilt is used for dependency injection, making the app modular and easier to manage.
- Gradle Kotlin DSL: The project uses Gradle Kotlin DSL for build configuration, which provides a more concise and powerful way to configure the build.
- Unit Testing: Some classes in the app are unit tested using the Mockk library for mocking dependencies.
- Coil: Used Coil for loading images.

# Solution

1- Design:

The UI design of the app is kept simple and minimalistic, focusing on usability and functionality.

2- Modular Clean MVVM:

The Clean MVVM architecture is followed, with a clear separation of concerns between different layers of the app, making it easy to maintain and test.

3- Testing:

Some classes in the app are unit tested to ensure their functionality and reliability.

4- Git History:

Due to limited time, the best practices for git history, such as using git-flow for feature and bugfix branches, were not followed. Only the master branch was used, which is not recommended in a production environment.

5- Overall:

The best architecture and tools were chosen to implement the project, with a clean and simple codebase following the required guidelines.

# Run 

To build and run the project, you will need to add an encrypted API token in your local.properties file for authentication with the crypto currency API.

For doing that, you need a RSA key pair to encrypt the Api key. Also you have to put public key into the app. (Data -> src -> main -> cpp  -> native-lib.cpp)
