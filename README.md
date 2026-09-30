<!--
Student: Kaiyrzhan Baktiyar
Group: SE-2519
Assignment: Assignment 2 — Factory Method & Abstract Factory
Domain: Cross-Platform Game Engine Framework
-->

# Assignment 2 — Cross-Platform Game Engine Framework

## 1. Domain

This project models a small cross-platform game engine. A platform family supplies three related products:

- `RenderPipeline`
- `AudioEngine`
- `InputController`

Families:

1. Windows DirectX
2. Linux Vulkan
3. macOS Metal
4. Android OpenGL ES

Each family has different rendering, audio, and input behavior. The client depends on abstractions rather than concrete platform classes.

The assignment requires at least three product types and at least three compatible product families; the final system contains four families and twelve concrete products. fileciteturn0file0L13-L27

## 2. Part A — Without Factories

`anti/LegacyGameClient.java` intentionally uses `if/else` and direct `new` calls.

Three concrete problems are documented in the source:

1. The client is coupled to every concrete platform class.
2. Platform-selection/creation logic grows into a large conditional and must be edited when a family is added.
3. Creation is duplicated and incompatible products can accidentally be combined.

This initial implementation is intentionally kept as a historical stage and is not used by the final client.

## 3. Part B — Factory Method

The Factory Method is isolated around `RenderPipeline`:

- `RenderWorkflowCreator` declares `factoryMethod()`.
- `processWorkflow(...)` contains real rendering workflow logic and calls the product returned by `factoryMethod()`.
- `WindowsRenderCreator`, `LinuxRenderCreator`, `MacRenderCreator`, and `AndroidRenderCreator` override the factory method.

This is Factory Method rather than a static factory because object creation is deferred to an overridable instance method of a Creator hierarchy, while the base Creator owns the business workflow.

## 4. Parts C/D — Abstract Factory and Compatibility

`SystemFactory<F extends FamilyTag>` creates all three related products:

- `createRenderPipeline()`
- `createAudioEngine()`
- `createInputController()`

The family marker types (`WindowsFamily`, `LinuxFamily`, `MacOSFamily`, `AndroidFamily`) are compile-time type parameters.

Every product is parameterized by the same family marker:

`RenderPipeline<F>`, `AudioEngine<F>`, `InputController<F>`.

`GameSession<F>` receives a `SystemFactory<F>` and therefore obtains all three products with exactly the same `F`. There is no runtime `if (wrongFamily)` check used to enforce compatibility. A mixed-family combination such as `GameSession<WindowsFamily>` with `LinuxAudioEngine` is rejected by Java's type system.

The assignment explicitly requires architecture-level prevention of incompatible combinations rather than an exception-based check. fileciteturn0file0L77-L94

## 5. Part E — Runtime Factory Selection

`FactoryProvider` reads:

- `--family=windows|linux|mac|android`
- `GAME_FAMILY` environment variable
- `config.properties`

Precedence is CLI > environment > properties > `windows` default.

After selection, the application uses `SystemFactory<?>`; concrete family classes are not instantiated by the main business client.

## 6. Part F — Three Business Scenarios

`GameSession` implements three real multi-product workflows:

1. **Interactive frame cycle** — input is polled, mapped to an action, the action influences rendering, and audio reacts to the same input event.
2. **Adaptive quality decision** — rendering metrics are collected, input state affects the workload, and audio latency is included in a platform-specific quality decision.
3. **Checkpoint/replay synchronization** — input events are captured, the renderer produces a frame signature, and the audio engine creates a synchronized checkpoint containing data from all three products.

These are collaborations, not isolated getter/constructor tests.

## 7. Part G — Fourth Family / OCP

Android was added after the original three families.

### Files added for Android

- `family/AndroidFamily.java`
- `product/android/AndroidOpenGLESRenderPipeline.java`
- `product/android/AndroidOpenGLESAudioEngine.java`
- `product/android/AndroidOpenGLESInputController.java`
- `factory/AndroidOpenGLESFactory.java`
- `factory/AndroidRenderCreator.java`
- `test/.../AndroidFamilyTest.java`
- Android entries in `FactoryProvider` and configuration documentation

### Existing files intentionally untouched

The following core business abstractions and workflow classes remain unchanged:

- `product/RenderPipeline.java`
- `product/AudioEngine.java`
- `product/InputController.java`
- `product/FamilyTag.java`
- `business/GameSession.java`
- `business/FrameReport.java`
- `business/AdaptiveQualityReport.java`
- `business/Checkpoint.java`
- `factory/SystemFactory.java`
- `factory/RenderWorkflowCreator.java`
- all original Windows/Linux/macOS concrete products

The only central extension point that needs to know the new family is runtime configuration/factory registration (`FactoryProvider`). The business layer remains closed for modification.

## 8. Part H — UML

See:

- `uml/architecture.puml`
- `uml/architecture.mmd`

Both explicitly mark the Factory Method and Abstract Factory regions.

## 9. Part I — Tests

The suite contains 20 JUnit 5 tests. It covers all requested categories: original families, concrete products, compatibility, runtime selection, three business scenarios, two negative cases, Android, and abstraction-only client usage. The assignment minimum is 15. fileciteturn0file0L158-L170

## 10. Part J — Git History

`setup_project.sh` creates exactly these eight meaningful commits:

1. `feat: initial domain model with direct creation (anti-pattern)`
2. `refactor: add product abstractions and interfaces`
3. `feat: introduce Factory Method pattern for core module`
4. `feat: introduce Abstract Factory for platform families (A, B, C)`
5. `feat: add compatibility rules and runtime factory selection`
6. `feat: implement complex business collaboration scenarios`
7. `test: add comprehensive JUnit5 test suite (15 tests)`
8. `feat: extend system with 4th product family (Android/OpenGL)`

No meaningless `update`, `fix`, `final`, or numeric commits are used, matching the assignment requirement. fileciteturn0file0L171-L188

## 11. Run

Requirements: JDK 17+, Maven 3.9+, Git.

```bash
mvn clean test
mvn -q exec:java -Dexec.mainClass=kz.se2519.gameengine.Main
```

The second command requires the Maven Exec plugin; alternatively run `kz.se2519.gameengine.Main` from an IDE.

Runtime examples:

```bash
java -cp target/classes kz.se2519.gameengine.Main --family=linux
GAME_FAMILY=android java -cp target/classes kz.se2519.gameengine.Main
```

For the configuration-file route, edit `src/main/resources/config.properties`.

## 12. Defense points

- Factory Method owns a workflow; the concrete Creator decides which RenderPipeline is created.
- Abstract Factory guarantees that the three products come from one family parameter `F`.
- Runtime selection is separated from business logic.
- Adding Android requires new family/product/factory classes, while the business workflow remains unchanged.
- Direct creation remains only as the documented historical anti-pattern.
