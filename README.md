#safetyzone-app

#Intro
A project designed and developed for a university subject I was studying, Software Engineering Processes.

This project targets Android 4.0 ICS and up.

This is the client application that connects to an Azure App Service instance to get data. 



#What is Safety Zone?

From the submission docs:
"Our product, SafetyZone, is a safety app aimed to help people feel safer when walking alone, especially at night."

Basically, it gets the user's location (longitude and latitude) and submits it to the API we built; this API works out the local government area (NSW only) the user is in, and then gets the associated assault statistics for the local government area.
The app also has a bunch of safety tips, and allows the user to set a designated contact to notify if they enter an unsafe local government area. 
We initially planned to use more specific and granular statistics, but BOCSAR denied our requests for more in depth datasets than at the LGA level. :(



