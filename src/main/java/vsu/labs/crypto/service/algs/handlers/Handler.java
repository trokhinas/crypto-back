package vsu.labs.crypto.service.algs.handlers;

import vsu.labs.crypto.algs.common.ControlPanelBlock;

@FunctionalInterface
public interface Handler {
    void handle(ControlPanelBlock block);
}
