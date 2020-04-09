# Planet Defenders

## Peter Bignold, Richard Harder, Aiden Taylor, Alex Tran, and Nick Zhao

Planet Defenders is a 2D arcade-styled defense game. The player must defend their home planet from incoming projectiles.  

###### How to Run

1. Download planetDefender.GUI.jar and planetDefender.TEXT.jar from the releases tab under "Demo 3" (or click links to download)<br/> <https://github.com/30092171/pdef/releases/download/0.3/planetDefendersGUI.jar><br/>
<https://github.com/30092171/pdef/releases/download/0.3/planetDefendersTEXT.jar><br/>
2. Place .jar files in desired directory
3. Double-click planetDefenders.GUI.jar to open the GUI version.
4. Otherwise for the text-based version, open command line
5. java -jar \<path\>\planetDefendersTEXT.jar
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

1. Install Java JDK 8 (<https://www.oracle.com/java/technologies/javase-jdk8-downloads.html>)

2. Download Planet Defenders source code (<https://github.com/30092171/pdef/releases/download/0.3/planetDefenders_SourceCode.zip>)

3. Set your JAVA_HOME and PATH (<https://javatutorial.net/set-java-home-windows-10>)

4. Move to src directory and compile all the pdef files (<javac pdef/*.java>): <br/>

5. Move to src directory and compile all the fxapplication files (<javac fxapplication/*.java>):

6. Run the following files by moving tosrc directory and type in the following:<br/>
Text-Based --> \<java pdef.Main \> <br/>
GUI-Based --> \<java fxapplication.Game \> <br/>


###### JUnit Testing
Currently our JUnit test for the Projectile class only works within Eclipse. After installing Eclipse, follow these steps to run the JUnit test.
1. Create a new project in Eclipse and ensure the JUnit 5 library is added
2. Import all files within the game's src folder (including ProjectileTest.java) into the new project's src folder 
3. Right click ProjectileTest.java inside pdef.JUnit > Run As > JUnit Test

Written By: Alex Tran
