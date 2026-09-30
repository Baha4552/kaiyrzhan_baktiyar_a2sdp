package kz.se2519.gameengine.product;

public interface InputController<F extends FamilyTag> {
    String family();
    InputState poll();
    InputAction map(InputState state);
}
