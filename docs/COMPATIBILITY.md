# Compatibility Rule

The final API is intentionally generic:

```java
SystemFactory<F extends FamilyTag>
RenderPipeline<F>
AudioEngine<F>
InputController<F>
GameSession<F>
```

A Windows factory has type `SystemFactory<WindowsFamily>`. Therefore:

```java
GameSession<WindowsFamily> session =
    new GameSession<>(new WindowsDirectXFactory());
```

is valid.

A Linux audio product has type `AudioEngine<LinuxFamily>`. It cannot be supplied where
`AudioEngine<WindowsFamily>` is required without an unsafe cast. `GameSession` does not
accept three independent product parameters; it accepts one `SystemFactory<F>`, and the
factory creates all three products using that exact `F`.

This is the architectural compatibility guarantee required by Part D, rather than a
runtime `if (wrongFamily) throw` check.
