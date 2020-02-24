# Planet Defenders 

## Peter Bignold, Richard Harder, Aiden Taylor, Alex Tran, and Nick Zhao

Planet Defenders is a 2D arcade-styled defense game. The player must defend their home planet from incoming projectiles.  

###### How to Run
1. Download release.jar from the releases tab under "first demo" (or click link to download) <https://github.com/30092171/pdef/releases/download/0.1/release.jar>
2. Place release.jar in desired directory
3. Open command line
4. java -jar \<path\>\release.jar
5. Enjoy!

###### Game Instructions
1. Increase your score by destroying projectiles, and survive as long as possible
2. If a projectile gets too close, you will get hit and lose 1 of 3 lives
3. Type the character of the projectile you want to destroy (Ex. \<A\>)
4. Type \<Reset\> at any time to start over

###### How to Compile Sperately
Follow these steps if you wish to compile the game files yourself.
1. Download and extract game .zip file into desired directory <https://github.com/30092171/pdef/archive/master.zip>
2. Compile the following files: Main.java PlayerInput.java PlayerPlanet.java Projectile.java SpawnHandler.java
\<javac Main.java PlayerInput.java PlayerPlanet.java Projectile.java SpawnHandler.java>\
3. \<java pdef.Main>\ Enter the following in the command line after entering the subfolder outside of the pdef folder

###### JUnit Testing
Currently our JUnit test for the Projectile class only works within Eclipse. After installing Eclipse, follow these steps to run the JUnit test.
1. Create a new project in Eclipse and ensure the JUnit 5 library is added
2. Import all files within the game's src folder into the new project's src folder
3. Right click ProjectileTest.java > Run As > JUnit Test

Written By: Alex Tran
