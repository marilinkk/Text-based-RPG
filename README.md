# Text-based-RPG
Final project for object-oriented programming course. This project was done together with Robin Pau
1. General description and purpose of the project
The goal of the project was to create a text-based role-playing game. The user ventures into the caves, meets various characters and must survive. 
The layout of the cave itself is easy to modify as it is saved in 2D array from which the method generates the dungeon for the player.

When starting the game, the user must enter the name of his character. The cave has several levels and in each level
the player can choose between 1-4 rooms to go to. By choosing 0, it is possible to end the game prematurely. In each
room there is some kind of character waiting them (monster, wizard, helper, trap, boss) with whom the player interacts
and depending on the room type, the interaction is either automatic (monster, helper, trap, boss) or can be played by the player
do something yourself to survive (wizard). The strength of the mobs is otherwise random, except for the boss, who is also
created based on the "Koll" (monster) class, but who is much stronger than the normal monster.

2. Classes
Main
In this class, caves are created for the player based on the map assigned to him (method koopaLoomine) and
the main class is run. In the basic class, the player's name is asked, his basic data is issued and
he is asked which room he wants to go to next. The corresponding one is called using a loop in the main class
room activity and checking if the player is still alive.

Koll
When creating an instance of a monster using the constructor, an arbitrary description from the given list is generated for the monster. The class contains
in itself the methods of ruumiTeegevus where the battle is carried out.
General idea of the combat method: At the beginning of each cycle of combat, strength is generated for both the monster and the player
and protection from a range of 1...theirMaxDefense/Strength. In other words, if the player's strength is, for example, 10, then
the impact strength can be any value from 1...10. It is similarly generated arbitrarily for the monster. Then, from the player's impact strength the monster's defence is substracted
and this difference determines how much damage is done to either the monster or the player. If a player's attack strength is 7, but the monster's defense is 6, the player
actually does 1 life point worth of damage. This principle goes both ways - both for the monster and the player. Monster's maxStrength and maxProtection are
randomly generated when creating a cave. The fight will always last until the death of the monster or the player. The class also has Getters and Setters just in case.

Mängija
The Player class contains only constructors and various Getters and Setters. One of
the instance fields is the map on which a cave is created for the player.

ToaTegevus
ToaTegevus is an Interface. It contains two methods:
public boolean ruumiTegevus(Mängija mängija);
public void getKirjeldus();

Võlur
An arbitrary description is also generated for the wizard, but also an arbitrary riddle that he asks the player.
The ruumiTegevus method includes asking the same question and checking the correctness.
The player can answer 3 times, uppercase and lowercase letters are not considered different, but there is only one correct answer.
If the player answers correctly the first time, he gets 1 extra life, but if he gets it wrong 3 times, he loses 2 lives.

Abistaja
The helper class is designed to provide the player with additional support while traversing the dungeon. There is a helper's room
in almost every level (except Boss and End). There are four different types of aids: sword, shield, map and healing potion
The sword adds 2 strength to the player, the shield adds 2 defense, the potion adds 1 life, and the map tells the player which rooms are in the next level,
so the player can make an informed choice. Aid generation is random.

Lõks
An arbitrary description is generated for the trap. The ruumiTegevus method checks whether the damage generated (from 0 to 2) for the trap
causes any real damage to the player and whether the player survived it. The player's own defensive strength does not help him here.

