# Tabroot
## HI! We're Team Tabroot and this is an AI software capable of playing to the Tablut board game.

# Project structure

The project is structured in package as follows:

it.unibo.ai.didattica.competition.tablut.client:
This package contains some basic player as the HumanPlayer or the Random Player.

**it.unibo.ai.didattica.competition.tablut.client.tabrootplayer:
This package contains our player partecipating to the competition.**

**it.unibo.ai.didattica.competition.tablut.client.search:
This package contains the strategy used for the Black and White Player and the implementation of the search Algorithm.**

**it.unibo.ai.didattica.competition.tablut.optimization:
This package contains some optimizations to our current algorithm (e.g with the Transposition Table technique) but actually we haven't finished yet (it has some unresolved problem) and so they aren't used for the competition. However we're going to finish it in the near future.**

it.unibo.ai.didattica.competition.tablut.server:
This package contains the server to whom connect with the client players to play.



# How to use:

In the project are available two executable (jar) files. One launch the server and other launches the TabrootClient.

## NOTE: The TabrootClient.jar need the following library to be executed:
/Tabroot/lib/aima-core-3.0.0.jar
/Tabroot/lib/gson-2.2.2.jar


Firstly run the server.

Then run the client you want to use.
Our player is the TabrootClient and these following are the command to launch it.

```
java -jar TabrootClient.jar white|black [-t <timeToMove>] [-n <nameOfTheTeam>]
```

*The parameter white or black is case insensitive and let you to choose which side you want to play.
*-t parameter let you to specified the time in seconds allowed to calculate the next move. (Default is 59 second).
*-n parameter let you to specified the name of the player. (Default is Tabroot)
