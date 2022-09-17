# TinyTownsAI
This is my final release for my dissertation work any further work after this will be uploaded to a new repository (I will add a link here) as I want my dissertation work to be viewable in the future.

Problems 
A very messy pathing algorithm that I will be looking to correct in a future version. 
The miniMax algorithm does not account for opponents' board
There is only one type of turn meaning minimax needs to be run twice when you chose a resource and then place a piece which is inefficient

Future work checklist
Fix pathing algorithm 
restructure some of the player functions to be a part of the board functions
restructure some functions in the main application to player structure
add a new type of turn called Pick and place resource.
Add an algorithm component that would quantify how good a factory is as it currently has no value in minimax
need a better way to quantify a farm and cottage as if the depth is below 4 then they will never be built.
