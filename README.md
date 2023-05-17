# Ataxx Game

Ataxx is a two-person game played on a 7-by-7 board, with red and blue pieces. It offers an exciting strategy experience with a simple concept: players can either extend from a piece of their own color or jump to a distant square, replacing all opposing pieces next to the destination square.

![Ataxx Board](./images/ataxx_board.png)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Ensure you have Java installed on your system. To check, use the following command:

\```bash
java -version
\```

If Java is not installed, you can download it from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

### Installing

Clone the repository to your local machine:

\```bash
git clone https://github.com/yourusername/ataxx.git
\```

Navigate to the project directory:

\```bash
cd ataxx
\```

Compile the project:

\```bash
javac -ea ataxx.Main.java
\```

## Running the Game

To start the game, use the following command:

\```bash
java -ea ataxx.Main
\```

For a GUI-based game, use:

\```bash
java -ea ataxx.Main --display
\```

## How to Play

Ataxx has several commands to facilitate smooth gameplay:

- **clear**: Abandons the current game, clears the board to its initial configuration.
- **start**: Enters playing state. Red and Blue alternate moves.
- **quit**: Abandons any current game and exits the program.
- **auto [C]**: Sets up the program so that player C (Red or Blue) is an AI.
- **manual [C]**: Sets up the program so that player C (Red or Blue) is a manual player.
- **block [CR]**: Sets a block at square CR.
- **seed [N]**: Sets the random seed to N for AI's moves.
- **help**: Print a brief summary of the commands.
- **dump**: Prints the board out.
- **load [file]**: Reads the given file and in effect substitutes its contents for the load command itself.

## Testing

The project includes tests to ensure everything is working correctly. To run the tests:

\```bash
# add instructions to run tests
\```

## Contribution

If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

## Licensing

This project is licensed under MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Contact

Feel free to reach out if you have any questions or suggestions.

