# ElectriSim

February 9, 2024

1 Introduction	3
1.1 Importance	3
1.2 Objectives	4
2 Design	5
2.1 Problem Analysis	5
2.1.1 Breadboard Algorithm	5
2.1.2 Pseudocode #1	6
2.1.3 Pseudocode #2	6
2.2 Theory	7
2.2.1 Units	7
2.2.2 Laws	8
2.2.3 Components	9
2.2.4 Circuits	10
2.3 UML Diagrams	11
2.3.1 Operator Classes	11
2.3.2 Component Classes	12
2.3.3 GUI Classes	13
2.4 GUI	14
2.4.1 Title Screen	14
2.4.2 Settings Screen	15
2.4.3 Level Selection	16
2.4.4 Achievements Screen	17
2.4.5 Main Screen	18
2.5 Timeline	19
2.6 Software	20
3 Features	20
3.1 Necessary Features	20
3.2 Optional Features	21
4 Conclusion	22


1	Introduction

After three semesters of learning different aspects of Java, starting from the basics and ending with visual effects, we have learned to design programs with visual clarity to help us solve challenges related to science and mathematics. Many of us have trouble with physics as it is a difficult subject so we have decided to create a tool to help us learn about building circuits with resistors, capacitors, and more.

As such, we aim to generate visual representations of circuits that allow users to determine optimal builds for maximum functionality, depending on their goals. This would make it easier to develop circuits for both the professional & personal spheres of work by providing a means to test out ideas and offering a projected budget. With a low entry-skill requirement coupled with a limitless skill ceiling, this software conforms to the needs of anyone who uses it. This project is made to facilitate understanding of circuits for any who need it by providing an easy building tool with a clear visual output. To add user retention, we will implement different challenges similar to levels to give an objective to the player and encourage them to think outside the box. We will also implement a sandbox to provide a way for the user to freely build the circuit of their choice. 

Not only are we developing a software to help with learning circuit building, this project will be the biggest challenge to us during our stay at cégep. It will help us improve in areas that we have been working in for these three semesters as well as helping us learn various new techniques such as dragging a screen to extend the possible range of operations. We will also get better at working on a large project with a team which will be useful for actual work in the industry.

1.1	Importance

Creativity
Working with a wide variety of components to build circuits encourages the user to search ways to make the circuit work and sparks the curiosity of the user. Having the option to build simple or complicated electrical path provides the user with an astounding amount of options to play with that depend only on how creative they can get. Creativity is very important when it comes to problem-solving, especially when the solution requires thinking outside the box, which is why we will have problems that require some out of the box thinking.

Learning
Not everybody is knowledgeable about electrical circuits or wants to go through textbooks and exercises to learn about circuits. This tool will help establish a good foundation for people of any age who want to learn or remember concepts about electricity. The game will guide the user through electricity problems and improve their knowledge about physics and their problem-solving skills.

Entertainment
Learning about physics from school isn’t always ideal when the information arrives through listening to a lecture. This software offers an entertaining alternative to help anybody who doesn’t understand well enough from lectures. Learning is always easier when we are having fun which is the reason why our software is similar to a game.

Wide-Targeted Audience
A wide targeted audience is important for both the users and the developers. As developers, we are encouraged to create a software that can be understood by a large amount of people so we have to consider aspects that we didn’t have to consider when previously writing code. As an audience, playing a game that is too complicated isn’t attractive to everyone but at the same time, having a game that’s too easy is also an issue, since the game wouldn’t be teaching much. To target this wide audience, the game needs to have hard aspects and easy aspects, which is done by making progressively harder levels so that everyone can reach a level they are satisfied with.

1.2	Objectives

Intuitive GUI
For this software, one of our goals is to create a GUI that is very easy to use and navigate. Both keyboard and mouse can be used to navigate and simple buttons will display the features available. 

Performance
The finished game should be able to understand the input of the user and adapt in real-time. The delay should be small and the program should easily understand the intentions of the user. 

Reliability
The program needs to be able to understand and show that the user has completed the puzzle no matter which path they took whether it be complicated or simple.

Usefulness
The software will provide users with complex problems to challenge their intellect and give them useful knowledge for circuit creation. Users will also be able to save their current loadout when quitting the game, quickly come back from any action with ctrl + Z, and reset the current loadout to start fresh.

2	Design
2.1	Problem Analysis

There are two main issues with creating software that simulates the experience of building circuits. First, after the user drags an object onto the circuit, the program will have to efficiently actualize the current state of the circuit. In other words, it will have to change the budget, check the condition for the goal, and update the visual image of the circuit in the most efficient way possible.
2.1.1	Breadboard Algorithm
The first thing that needs to be established is a grid that makes up the possible space for constructing the circuit. Then, for efficiency, let every empty space on the grid be 0 and every filled space be a digit depending on the component’s ID that is on that space. For example, if a wire has an ID of 1, then if a spot on the matrix is 1, that means a wire sits there. We will use IDs instead of string labels to save memory. This way, it is possible to use the innate function of GridPane to find the location of the space changed by any new component added by the user and then actualize all adjacent matrix boxes to connect the component to the spaces around it. Additionally, it is possible to determine the path of the circuit by continuously scouting the connected components after every change. This second way is simply a recursive way of using the first way so the efficiency is the same even if the time taken is longer since the growth rate is the same. 

The algorithm’s first step would be to analyse the input of the user. This step first uses the function OnMouseReleased in combination with getColumnIndex(Node a).intValue() and getRowIndex(Node a).intValue() to get the location on the grid and identify the node chosen in the same action. Then, inside the handler, the indexes of the components around the space are determined by using the previously obtained index and adding or subtracting one from them. Doing so allows the program to quickly link the different nodes together and create the path. In the case that the user puts an object on a corner or a wall grid space, a try-and-catch is there to catch the out-of-bounds exception and prevent the software from having issues. 



2.1.2	Pseudocode #1

OnMouseReleased(Mouseevent e)
	Set collumnIndex to gridpane column index value
	Set rowIndex to gridpane row index value
	Call refresh method with parameter of collumnIndex ∓1 and rowIndex ∓1 

Void Refresh(collumnIndex and rowIndex)
try(
	Look at the value stored inside the matrix  at columnIndex and rowIndex
	If (value != 0)
		Call a listener
)catch (outofboundsexception ex)
	

Because this first step is constantly doing a constant number of verifications no matter how big the circuit has gotten, the efficiency of this algorithm is constant (O(5)) which is a better approach than the linear approach in time with respect to the circuit’s length (O(n))
 
The second step of this algorithm starts when the algorithm detects that the battery is connected from both poles. It starts from the positive side of the battery by creating a main thread and uses the updates from the first step to detect where the next component is in the path. In the case of a parallel circuit, the algorithm creates a new thread for every new path. Then, the algorithm calculates the voltage and current for the resistors or the capacitance of the capacitors by invoking the methods involving the Capacitance law and Ohm’s law (2.2.2). These laws are invoked for every resistor or capacitor that is encountered and calls them with the current that the wire is transporting at the respective point in the grid. It also displays the necessary values in the respective GUI grid to help the user (figure 7). 

2.1.3	Pseudocode #2

If the battery has 2 connections
	Call the method recurve with columnIndex and rowIndex of battery

Recurve
	Call the method recursiveMethod with parameter null, columnIndex and rowIndex

Recursive method

	If (bottom)
(call path with top, left and right so 3 different ifs)
		If (path == 1)
			Call the recursive method with the new collumnindex or rowindex, and the opposite of the way a component was found. Ex, calling recursive method with top would make it not check the top since you came from there and found a component on the bottom. (top, bottom, left, right)
		If(path == 2)
			break;
	If(top)
		if(path)
		if(path)
		if(path)
		If(path == 2)
			break;
	If(left)
		…
	If(right)
		…
Else break;
				
Path(collumn index, row index)
Make an int = 1
try(
	Look at the value stored inside the matrix @ column index & row index 
	If (value == 0)
		Int becomes 0
	If (value == battery value)
		Int becomes 2
	If(value == !wire value)
		Store matrix position index of component
		Int becomes 0
Return int;
)catch (outofboundsexception ex)
Return return 0;


This second step has to check grid by grid to find out if calculations need to be made or if it simply goes to the next grid occupied by a wire and repeats the process. Since it checks all three grid spaces other than the one it came from, this step is linear in time with respect to the number of components. (O(n)) where n = 3c+1 // c represents the number of components in the closed circuit.
2.2	Theory

Electrical physics is a branch that explores the fundamental properties, behaviours, and interactions of electric charges and fields. Circuits, being the transmission path of electric current, is at the core of the domain. In the real world, these concepts pave the way for technological advancements in fields such as electronics, power generation and telecommunication.

2.2.1	Units

Potential (Volts)
Electric potential, or voltage, is the difference in potential energy per unit charge between two locations. In the context of circuits, the voltage is the quantity of work required to displace a unit charge from one point to another. Whilst the potential difference of a circuit supposes that the displacement occurs from cathode to anode, one can identify the voltage between any two arbitrary points on the circuit using a voltmeter.

Current (Amps)
Current is the rate at which electrons flow at a precise point in a closed circuit. One ampere of current is equivalent to one coulomb of charge passing through any arbitrary point in one second.

Resistance (Ohms)
Resistance is the quantitative measure of the opposition to current flow. The concept is somewhat analogous to friction in mechanical physics. Moreover, the resistance of any given material at a given moment depends on physical dimensions, temperature and other material properties. A conductive material is one that offers very little resistance, leading electrons to move freely. Conversely, an insulator presents high resistance and heavily restricts the electron flow.

Power (Watts)
Electric power is the rate of transfer of energy in a circuit. One watt is defined as one joule of energy per second at any given arbitrary point. As illustrated by the Power law, power is directly proportional to both voltage and current.

Charge (Coulombs)
The electric charge refers to both a property possessed by positive or negatively charged particles, and a quantity of said property associated with those particles. A coulomb is defined as the charge carried by 6.242 x 1018 electrons. 

Capacitance (Farads)
Capacitance is the ability of a component to store charge per unit voltage. This measurement is typically used to describe the efficiency of a capacitor.

2.2.2	Laws

Ohm's Law
Ohm’s law states that the resistance of a conductor (R) is equivalent to the ratio of voltage across the conductor (V) to the current flowing through it (I).
R = VI

Power law
The power law illustrates that the power dissipated or consumed in a circuit (P) is equal to the product of voltage (V) and current (I).
P=VI

Capacitance law
The Capacitance law indicates that the capacitance of a capacitor (C) is equal to the ratio of the charge stored on its plates (Q) to the voltage applied across it (V).
C=QV

2.2.3	Components

Wire
Wires are conductive materials used as pathways to transmit electrical signals or power throughout an electrical circuit. Essentially, they connect the various components, allowing them to perform their intended functions. These wires are typically made with low resistance to minimise the influence of circuit length and complexity as a resistance to current. 

Power source
A power source is a system or device that supplies electrical energy (A) to a circuit. The power source is composed of 2 nodes, positive and negative, allowing current to flow of charge within a circuit. While the proper convention is to assume current flows from positive to negative, the electrons navigate the circuit in the opposite direction.

Resistor
A resistor is a passive electrical component that impedes the flow of current by transforming the electric energy into heat. The strength at which this is completed is measured in ohms.

Capacitor
Capacitors are passive electronic components that store electric energy in an electric field, consisting of two metal plates separated by a non-conductive material known as dielectric. The choice of dielectric substance dictates the purpose of the capacitor as certain dielectrics are better at higher frequency uses while others excel in higher voltage applications. When connecting a capacitor to a power source, the plate attached to the negative terminal accepts and stores electrons, consequently emptying the plate attached to the positive node.This charging process continues until the capacitor has the same voltage as its power source. The capacitor’s storage potential is measured in farads (F), where a 1 farad capacitor can store one coulomb of charge at 1 volt.

Diode
Diodes are two-terminal semiconductor devices that allow current to flow in a single direction. The basic structure of these components consists of a PN junction formed by two charged semiconductor materials; a positively charged P-type and negatively charged N-type. When the positive voltage is applied to the P-type material, the depletion region becomes narrower, allowing current to flow with low resistance through the diode. Conversely, when the positive voltage is applied to the N-type material, the depletion region widens, increasing the resistance of the diode and blocking the flow of current.

Transistor
Transistors are semiconductor devices that are used to amplify or switch electronic signals and electrical power. A Bipolar Junction Transistor (BJT) is formed by the junction of three prawns, the base, collector, and emitter. When the base receives a small current, usually around 0.7V, the transistor allows a larger current to flow from the collector to the emitter. 
2.2.4	Circuits

Direct Current and Alternating Current
Electric current can be either direct (DC) or alternating (AC). On one hand, DC circuits have a continuous flow of electric charge headed in one direction. On the other hand, AC circuits periodically reverse the direction of electric charge due to electromagnetism principles. Since the flow of electric charge alternates back and forth, electrons suffer little to no resulting displacement along the wire. Nonetheless, energy transfer remains continuous. Note that only DC circuits are featured in the final product as electromagnetism remains out of scope for this project.

Serie and Parallel Circuits
Two components are said to be in series if the same current flows through them one after the other. In this case, the total resistance is the sum of individual resistances and the voltage across each component accumulates to the total voltage of the system. On the contrary, parallel components are separately connected across common points (nodes), subsequently providing multiple paths for the current to flow. In these scenarios, voltage remains constant across all components as current is divided among the branches according to their resistances.

Open and closed circuits 
A closed circuit presents a continuous path for the flow of current, allowing electrons to move from the source and through the various components only to then return to the source. On the other hand, an open circuit is characterised by a gap or break in the path, preventing the flow of current from looping from the positive to negative poles of the power source. Since this circuit state prevents power from being distributed to the components, opening and closing a circuit can be done by design and used as a switch.

Short circuits 
A short circuit is a consequence of the tendency of current to take the path with least resistance. If a direct connection is given between two nodes of a power supply, the current will bypass other parallel paths and risk shorting out the power supply, leading to overheating, potential damage to components and even electrical fires.

2.3	UML Diagrams
2.3.1	Operator Classes


Figure 1: Operator & Operation Classes


Above are the operator(unit of measure) and formula classes in our program. The dark blue classes serve as units of measurement in electrical physics. Each object holds a double to represent its value. The 3 lighter blue classes use objects from their respective operator class, so they are related by aggregation. The three formula classes implement the maxims interface, which contains the values for all physics constants necessary in this project.
2.3.2	Component Classes
Figure 2: Component, Budget & FileSaving Classes


Above lie the component classes. The component classes constitute the building blocks of any electrical circuit. The budget class takes these components and calculates the price they cost. The budget class calculates the cost of each component by multiplying the base cost by the length, capacitance, resistance, or voltage a given component produces. For example, a 3V battery will cost 12$, whereas a 9V battery costs 36$. Lastly, the FileSaving class creates a new file when you exit the sandbox mode and the game itself, for the first time. Afterward, when you boot up the game, your achievements and sandbox progression remain as it last was. Upon closing the game again, the already-existent files will merely be updated, as opposed to creating a new file each time.


2.3.3	GUI Classes
Figure 3: GUI Classes
(Note that the GUI for levels 4-10 have been omitted from figure 3)

We will have many GUI classes. The stage for each level will additionally include a component bar and the breadboard, The sandbox mode will also include those two scenes. Upon opening the project, users will be greeted with the main screen GUI, where they can then opt to enter the achievements screen, level select screen, sandbox mode, or to go to the settings, where they can exit the game. More information on the graphical user interface can be found below.



2.4	GUI

The direction taken for creating the graphical user interface (GUI) of this program is to make the interface appear like a game. This is done to appeal to a wider audience who might be uninterested by anything to do with physics as well as to make it very intuitive to use due to simple controls. The game will have 4 main screens with various functions each. The first screen is the title screen which appears with 4 simple buttons, one for the settings, one for the levels, one for the achievements and one for the Sandbox. All of these screens are made in plain colours so as to not aggravate the user.
2.4.1	Title Screen

Figure 4: Title Screen

This title screen is the first screen the user comes across and leads to almost every other screen possible. 
2.4.2	Settings Screen

Figure 5: Settings

To exit the program, the user can find the exit button in the settings or use alt+f4 to close the game. Additionally, in the settings, the user can find functions such as volume and disabling hints.



2.4.3	Level Selection

Figure 6: Level selection screen


By clicking the levels button, the user is brought to the level selection screen, where the first 5 levels need to have the previous level completed in order to be played. The last 5 levels are more challenging and require certain achievements to be completed in order to be played. For the exception of the very last level, in which case the user needs to have completed every other level. For a locked level, the user will be able to hover over the level and a small window will appear to give the requirements.




2.4.4	Achievements Screen
Figure 7: Achievement screen
The achievements screen incites the user to explore more of the options when creating circuits and achieve certain challenges that are not related to the level itself but related to learning more about circuits. These achievements are locked safely and the requirements are written very clearly under the safe. The user can use the slider provided to see all of the various achievements.

2.4.5	Main Screen
Figure 8: Game Screen

Finally the main screen of the game is the creation of a circuit. For all of the levels, there is a goal at the top left of the screen, a budget underneath, a grid giving any information needed to the user, a components bar with the cost at the bottom and a small hint button in case the goal is too complicated for the user. Finally there is a space to work with in the middle of the screen.  Keep in mind that for a level, there isn’t a single right solution but rather two conditions needed to be done. First the budget and then the goal. This is done because we did not want to restrict the user’s creativity and freedom by making it mandatory to clear a level in a specific way.


In the case of the sand box, the budget starts at 0 and increases depending on the components chosen by the user and there is no goal and no hint, this mode is implemented to help the user explore more freely without a restriction like the budget and to help them figure out a solution to a problem if they are having trouble with a level budget.  
Since this is simply an example of the tutorial, the grid on the right shows only the results of Ohm’s law which is needed for this goal but in other levels, there will also be a grid for the capacitance and the power.

2.6	Software

IntelliJ IDEA
IntelliJ will be the Integrated Development Environment (IDE) used to program our circuit simulator.

Maven
Maven will be used for managing project dependencies, building, packaging and deploying our Java-based application.

Code With Me
Code with me is a new real-time collaborative feature added to IntelliJ IDEA which will allow us to simultaneously work on the project with ease.

JavaFx
JavaFx will be used to create and handle the user interface of our project.

Paint
Paint has been used to create images that will serve as a base for the GUI development.
3	Features
3.1 Necessary Features

Wiring System
We will develop a wiring system to connect different nodes, complete with forks and junctions. 

Calculating System
We will make an algorithm that determines the voltage, current, power use & resistance of every single component in real-time, in a graph off to the side of the GUI.

Achievement System
We will implement an achievement system to promote creative solutions to problems. We will reward the user with exclusive levels (levels 6-9) upon receiving {1,3,5,8} achievements. The achievements are as follows:

Finish a level only using half the given budget.
Finish a level in less than 3 minutes.
Short-circuit yourself 5 times.
Beat a level with no hints.
Blow a capacitor.
Achieve breakdown voltage.
Fail the same level 10 times.
Complete all other achievements.


Sandbox Mode
This mode has unlimited resources, to give an unrestricted experience where the user can entertain all the different possible circuits that they need to test.

Hints
The first 3 levels will be learning-oriented levels that walk the user through the physics behind the game. All levels will have hints, to help guide the user in the right direction.

Level System
The main progression of this project is in the form of a level system. These levels provide clearly defined goals to the user. The levels will increase in difficulty as the user progresses, making this project a learning experience. At the start, the user will work on parallel circuits. By the end, the user will be multiplying voltage through capacitors and diodes. Levels 1-5 can be unlocked through natural progression. Levels 6-9 are unlocked through achievements. Level 10 is only unlocked after completing all other levels

3.2 Optional Features

Budget System
To introduce a new layer of depth to our project and challenge our users, we will add prices to our components, limiting the amount of resources they have for each given problem.

File Saving System
To make sure the users can pick up immediately where they left off, we will have a save system, which locally back-ups the user’s progress.

Sound System
We will add sound effects on button clicks, and when the components are dropped onto the board. We will also add a fanfare upon level completion, and we will include a buzzer sound if the user has a short circuit.

4	Conclusion

We are excited to start working on this project and we are happy with the progress we have made throughout the creation of this document. We will continue to refine it as further observations are made during coding. This design is very rough but includes many of the features we hope to implement into our program. We will work hard to find a solution for every challenge we will face implementing our physics knowledge into a usable circuit simulation. This project will certainly be successful as we are all eager to start working on a software developed on our own.

