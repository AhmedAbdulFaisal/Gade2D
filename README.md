# Gade2D
A 2d game engine which utilizes libGDX, programmed in Java. 


Table of Contents:
>Introduction / History
>Features
>Versions
>How to compile
>Used Applications


Introduction/History
====================

Gade2D is a long-running side project of mine, meant to be a Top-Down 2d game engine which is designed for adventure games. Development was started in 2016 as a small project I undertook in High-School, where the goal was to make an engine which helped me understand programming as a tool to create and develop software. The first iteration was written with the Java2D API, a barebones 2D graphics library intended for web and desktop applications. By the time the project was finished, it had a rudimentary tile-based level system, a working entity system, useable weapons, and a text and menu system. The project itself was fairly complex for a beginner project, as it involved multiple systems and cohesive concepts which would require a deeper knowledge of Object-Oriented programming. For my purposes, this was enough as I was a high schooler who was just getting into AP computer science, so a complete understanding of common knowledge subjects like Abstraction and Inheritance were extremely important.

The second iteration was written with a more in-depth library known as LibGDX (which is well known as a defacto replacement for slick2D). LibGDX made many more things possible and easier, as it didin't require you to write things which were mandatory when using Java2D (for instance, frame-buffers and actually having to implement a form of tile-loading). Furthermore, it was a fully dedicated framework designed for desktop and mobile applications, and wasn't deprecated like slick2D meaning that requesting help on a project was easy to manage as you had a large doccumentation to help you.

LibGDX allows for more complex features like 2D lighting, easy to implement physics, and a robust tilemap system which allows you to bypass making your own. People have made complex applications with the framework. Set to work with this in mind, I made the second version to be larger in scope than what was previously achieved. For instance, I wrote dedicated menu and GUI support, which included things like Inventory, menu screen, and dialogue box support. I took advantage of the built-in box2d lighting library to include a light mechanic which featured lanterns and dynamic lighting sources. This meant that I had to implement Box2D to cooperate with lights.

Eventually, I decided to take all I learned and start over, as the engine was badly programmed and scrappy in the sense that it didin't allow for a easy solution of loading/saving levels. I had wrote diagrams on what I wanted for level saving and loading, but I was never ready to implement them as they were very finnicky and lofty for my intentions. Instead, I decided to focus on re-writing the engine in handling modularity better; for instance, better implementation of generating a "world" of multiple small 50x50 tile-based levels. This meant that I needed to make functionality to allow the easy loading of levels (save states were a pipe-dream). I additionally sought to streamline the inventory system, opting for a more basic "list" style inventory which functioned the same as previously intended.

Lastly, I was more ambitious. I wanted to implement a form of pseudo-3d integration, which would allow for a rudimentary system where the player could jump and dodge projectiles. This portion of the project was scrapped (as making a pseudo-3d game is a massive undertaking on it's own), so instead I managed to complete my other goals. Lighting, due to issues was never completed as a goal with this iteration.

By this time, I was loosing interest and steam on the engine. I might never complete my full realization of the game, but I have decided to show my full progress on the latest iteration, which features a baseplate for what can be considered a functional game engine. The game "programmed" on the engine was internally called "Renegade", which was intended by me to be a metroid-vania style game with an emphasis on exploration and combat. 

Eventually, the project was abandoned due to a lost of interest and can possibly come back as a finished product. 



Features
========

>8-directional top-down movement
>Inventory system with useable items
>Weapon system with a unique magazine-loading system
>Event-Management system which can detect clicking from the player to open interactives
>Health and ammunition
>Menu system with dialogue boxes


Versions
========






