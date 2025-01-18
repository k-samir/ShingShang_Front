
# ShingShang FRONT
![](https://github.com/k-samir/ShingShang/blob/main/image/board.png?raw=true)

----------------
# Presentation
Welcome to the FRONT repository of the ShingShang project!

The objective of this project is to implement a board game called SHING SHANG.
It is a two-player game.
Each player has an army of 12 Bushis (Pieces). This army is composed of 3 groups: 2 dragons, 4 lions, and 6 monkeys.

[Documentation of the projet](https://docs.google.com/document/d/1ZtqhFwuuNymzyjvvDptcIIbRV_NNXb4CPYg6uDdT0ng/edit#)

[Projet Structure](https://github.com/k-samir/ShingShang_Front/blob/master/image/struct.PNG?raw=true)

----------------
# Educational Objectives

Understand the project and learn how to identify its goals.

The focus is more on software design aspects (in terms of documentation, reusability, etc.) and project tracking rather than on the technical difficulty of the subject.

----------------
# Game Basics

Players take turns performing one of the following two actions:

A player can move one of their pieces on the board to a different square.

A player can jump over another piece if the piece being jumped over is smaller or of the same size as the jumping piece

----------------

# Moves

To move a piece on the board, the destination square must be free.

Movement can be made horizontally, vertically, or diagonally, both forward and backward.

To jump, the jumping piece must be on a square adjacent to a square occupied by one of its own pieces or by the opponent's piece.

The jump can be made vertically, horizontally, or diagonally, as long as the next square is empty.

----------------

# Shing Shang Sequence

Multiple jumps can be chained during the same turn. This chain of jumps is called a SHING SHANG.
If, during a SHING SHANG, a player jumps over an opponent's piece, they must stop, and the opponent's piece is removed from the board. However, the player gains an additional turn with another piece.

----------------


# Specific Rule

Monkeys can move one or two squares in any direction—horizontally, vertically, or diagonally—but cannot change direction during the turn.
Lions can move one square in any direction—horizontally, vertically, or diagonally.
Dragons can only move by jumping.

----------------

# End of the Game
A player wins the game when they manage to move one of their dragons to one of their opponent's portals (special squares) or when they capture both of their opponent's dragons.


----------------


# Technologies Used
The game was fully developed on [Eclipse](https://www.eclipse.org/) using `Java` JDK 14.0.2.


