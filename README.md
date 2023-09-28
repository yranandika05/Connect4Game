# 4Gewinn

This is the project used as the final requirement to take the exam of the module "Programmierung interaktiver Systeme" or programming an interactive system. 

The project I chose was to create a Connect 4 game, where players alternately choose rows for the ball to enter. The game is declared a winner if the player manages to have 4 balls in a row vertically, horizontally, or diagonally.

The creation of this game uses the MVC (Model-View_Controller) principle where the model can be run independently in a shell to play this game. View is a graphic display of the game that utilizes tools from [Processing](https://processing.org/). The controller functions as a link between the Model and View and manages the View display based on the state of the Model. Connect 4 that I created can also be played using LAN/WLAN where players can play as a server or client. When the server creates a room, the client can enter the game by entering the code of the room. 


## Getting started

Clone this Repository and run MainClient.class to play as a client as well as MainServer.class to play as a server. 

## Test
There are several tests that serve to check whether the game runs as desired.
