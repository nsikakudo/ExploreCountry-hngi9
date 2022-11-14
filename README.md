<h1> Explore Country App</h1>

The Explore country app is an application that shows the number of countries in the world, with their logos, country name, and country capital name. The application is well structured in order for potential users to have a good user experience(UX).
<p>This project is developed for HNG 9 Mobile Track Stage 3 Task</p>

## App Architectural Features

In this project, I applied some application architectural principles in order to create a roadmap and best practicies to in follow so as to create a well structured and interactive application. I also used a combination of XML and Jetpack Compose for this project.

*  I developed data models to store the information that would be shown on the user interface(UI).
*  I also created a domain package, which houses the app's data models.
*  I went further to create a data package, which contains the data that would be displayed on  the UI and is the only source from which the data would come.
* I also constructed the UI package, which houses all UI components and screens.
<p>The project was designed in a way to make it easier to introduce new features in the event that they are added.</p>

## Application Design

For this project a Figma design was provide to aid ease the job.

*  [Design](https://www.figma.com/proto/v9AXj4VZNnx26fTthrPbhX/Explore?node-id=33%3A1390&scaling=scale-down&page-id=0%3A1&starting-point-node-id=33%3A1390&show-proto-sidebar=1)


##  App Libraries

The project leverages the following components and libraries :
*  Jetpack Compose - used to create a well structured UI.
*  Flow Layout - used to layout ui items on the screen.
*  ViewModel - UI related data holder, lifecycle aware.

##  Features to add if time permits
* Setting up the button to switch between the dark and light screen interface.
* A live location map that shows all the continents and its tourist center.


## Download Application

You can try out the application on your device through the link below;
*  [Apk link]()

## Challenges 
*  Setting up the Dark and Light Theme using Material Theme. 
*  I was also face with the challenge of creating an interactive filter layout.

## Application Demo
Check out the demo app on Appetize.io
*  [Demo link]()

## More Details
You need the following requirements to run this project

*   Android Studio IDE Dolphin 2021.3.1 Patch 1
*   Compose 1.2.1
*   Android SDK v33
*   Gradle 7.2.2
