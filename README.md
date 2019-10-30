# Tabroot Project
#### Hi! We're Team Tabroot and this is an AI software capable of playing to the Tablut board game.
#### This player joined the "2018/19 Board Game Students Challenge" proposed by the AI course of the Master's degree in Computer Engineering at UNIBO.

(Following this link you can find more information about the competition and the game's rule: http://lia.disi.unibo.it/Courses/AI/fundamentalsAI2018-19/challenge/Challenge2019.pdf)

(Here instead you can see the Challenge Results: 
http://ai.unibo.it/games/boardgamecompetition/1819)
#### Our player ranked 4th out of 22 Teams!! 



# Project structure

The project is structured in package as follows:

- it.unibo.ai.didattica.competition.tablut.client:

  This package contains some basic player as the HumanPlayer or the Random Player.

- **it.unibo.ai.didattica.competition.tablut.client.tabrootplayer:**

  This package contains our player partecipating to the competition.

- **it.unibo.ai.didattica.competition.tablut.client.search:**

  This package contains the strategy used for the Black and White Player and the implementation of the search Algorithm.

- **it.unibo.ai.didattica.competition.tablut.optimization:**

  This package contains some optimizations to our current algorithm (e.g with the Transposition Table technique) but actually we      haven't finished yet (it has some unresolved problem) and so they aren't used for the competition. However we're going to finish it in the near future.

- it.unibo.ai.didattica.competition.tablut.server:

  This package contains the server to whom connect with the client players to play.



# How to use:

In the folder "Executable files" are available two executable (jar) files:
- TabrootClient.jar launches our Tabroot player.
- Server.jar launches the Server.

#### **REQUIRED LIBRARIES: The TabrootClient.jar need the following library to be executed:**
- aima-core-3.0.0.jar (/Tabroot/lib/aima-core-3.0.0.jar)
- gson-2.2.2.jar (/Tabroot/lib/gson-2.2.2.jar)


1 Firstly run the server

2 Then run the client you want to use.
Our player is the TabrootClient and these following are the command to launch it.

```
java -jar TabrootClient.jar white|black [-t <timeToMove>] [-n <nameOfTheTeam>]
```

- The parameter white or black is case insensitive and let you to choose which side you want to play.
- -t parameter let you to specified the time in seconds allowed to calculate the next move. (Default is 59 second).
- -n parameter let you to specified the name of the player. (Default is Tabroot)








#### Authors:

Lorenzo Piazza

Simone Cancello

Andrea Fuschino

Alan Dalmonte
