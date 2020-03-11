# Planet Defenders

## Peter Bignold, Richard Harder, Aiden Taylor, Alex Tran, and Nick Zhao

Planet Defenders is a 2D arcade-styled defense game. The player must defend their home planet from incoming projectiles.  

###### How to Run
1. Download planetDefender.GUI.jar and planetDefender.TEXT.jar from the releases tab under "Demo 2" (or click links to download)<br/> <https://github.com/30092171/pdef/releases/download/0.2/planetDefenders.GUI.jar><br/>
<https://github.com/30092171/pdef/releases/download/0.2/planetDefenders.TEXT.jar><br/>
2. Place .jar files in desired directory
3. Double-click planetDefenders.GUI.jar to open the GUI version.
4. Otherwise, open command line
5. java -jar \<path\>\planetDefenders.TEXT.jar
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

###### How to Compile Separately
1. Install OpenJDK 13 (<https://adoptopenjdk.net/>)
2. Install a suitable JavaFX SDK (<https://gluonhq.com/products/javafx/>)
3. Download source code (<https://github.com/30092171/pdef/releases/download/0.2/planetDefenders_SourceCode.zip>)

Steps to Compile Java Files<br/>
\<set PATH_TO_FX="path\to\javafx-sdk-14\lib"\> <br/>
\<javac --module-path %PATH_TO_FX% --add-modules javafx.controls *.java\> <br/>
\<javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml *.java\> <br/>

4. Move to pdef directory and compile the following files using the steps above:
ProjectileTemp.java<br/>
Collidable.java<br/>
Main.java<br/>
PlayerInput.java<br/>
PlayerPlanet.java<br/>
PolarCoord.java<br/>
Projectile.java<br/>
RotatingProjectile.java<br/>
SpawnHandler.java<br/>
SpeedUpProjectile.java<br/>

5. Move a copy of the compiled pdef folder into the fxapplication folder.

6. Move to fxapplication directory and compile the following files using the steps above:
Barrier.java<br/>
Controller.java<br/>
Game.java<br/>
GUI.java<br/>

7. Run the following files by moving to one directory outside of where the files are located and type in the following:
Text-Based --> \<java --module-path %PATH_TO_FX% --add-modules javafx.controls pdef.Main \> <br/>
GUI-Based --> \<java --module-path %PATH_TO_FX% --add-modules javafx.controls fxapplication.Game \> <br/>

###### JUnit Testing
Currently our JUnit test for the Projectile class only works within Eclipse. After installing Eclipse, follow these steps to run the JUnit test.
1. Create a new project in Eclipse and ensure the JUnit 5 library is added
2. Import all files except images folder within the game's src folder (including ProjectileTest.java) into the new project's src folder 
3. Right click ProjectileTest.java > Run As > JUnit Test

Written By: Alex Tran
