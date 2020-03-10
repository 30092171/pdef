# Planet Defenders 

## Peter Bignold, Richard Harder, Aiden Taylor, Alex Tran, and Nick Zhao

Planet Defenders is a 2D arcade-styled defense game. The player must defend their home planet from incoming projectiles.  

###### How to Run
1. Download planetDefender.GUI.jar and planetDefender.TEXT.jar from the releases tab under "Demo 2" (or click link to download) <https://github.com/30092171/pdef/releases/download/0.2/planetDefenders.GUI.jar>
<https://github.com/30092171/pdef/releases/download/0.2/planetDefenders.TEXT.jar>
2. Place .jar files in desired directory
3. Double-click planetDefenders(GUI).jar to open the GUI version.
4. Otherwise, open command line
5. java -jar \<path\>\planetDefenders(TEXT).jar
6. Enjoy!

###### Game Instructions (Text-Based)
1. Increase your score by destroying projectiles, and survive as long as possible
2. If a projectile gets too close, you will get hit and lose 1 of 3 lives
3. Type the character of the projectile you want to destroy (Ex. \<A\>)
4. Type \<Reset\> at any time to start over

###### Game Instructions (GUI-Based)
1. Increase your score by destroying projectiles, and survive as long as possible
2. If a projectile hits the planet, you will get hit and lose 1 of 3 lives
3. Point and click on desired position to block projectiles from hitting the planet

###### JUnit Testing
Currently our JUnit test for the Projectile class only works within Eclipse. After installing Eclipse, follow these steps to run the JUnit test.
1. Create a new project in Eclipse and ensure the JUnit 5 library is added
2. Import all files within the game's src folder into the new project's src folder
3. Right click ProjectileTest.java > Run As > JUnit Test

Written By: Alex Tran
