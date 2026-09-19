# JavaFX Platformer

A two-dimensional platform game built with Java 21 and JavaFX. The codebase
separates gameplay state, input handling, and rendering with MVC, then uses
small behavioral patterns to keep new entities and interactions extensible.

## Engineering highlights

- MVC architecture with dedicated model, controller, and view packages.
- Command objects for player movement and jump/crouch actions.
- State objects for menu, active game, pause, level-complete, game-over, and
  game-end transitions.
- Factory-based creation for players, monsters, platforms, projectiles,
  collectibles, hazards, and goals.
- Strategy-based collision handling for coins, hearts, spikes, bullets, and
  level goals.
- Three data-driven levels, animated sprites, sound effects, score/health
  tracking, pause/restart controls, and a countdown timer.
- JUnit 5 and Mockito regression tests for movement, collisions, and view-state
  behavior.

## Requirements

- JDK 21
- Maven 3.9+, or the included Maven wrapper

## Run

```bash
./mvnw clean javafx:run
```

## Test

```bash
./mvnw test
```

The test suite exercises command execution, damage and collectible collision
strategies, and the level-complete view. JavaFX can emit a platform warning on
some headless or non-modular development environments; it does not indicate a
test failure.

## Structure

```text
src/main/java/com/dms/platformer/
├── mvc/controller/          # input, commands, state transitions
├── mvc/model/               # world state, entities, factories, strategies
└── mvc/view/                # JavaFX scenes and reusable UI components
src/main/resources/          # sprites, audio, level data, fonts
src/test/java/               # JUnit regression tests
```

## Controls

- `A` / `D`: move left or right
- `W` / `Space`: jump
- `S`: crouch or move down where supported
- `Esc`: pause or resume

The project intentionally keeps game rules separate from JavaFX presentation,
so additional levels, entities, and collision behaviors can be added without
rewriting the main application loop.
