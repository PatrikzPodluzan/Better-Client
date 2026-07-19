package betterclient.mod.client.GUI.Screen;

import betterclient.mod.client.GUI.Buttons.ItemAutoRefillButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ClientMenuScreen extends Screen {
    public ClientMenuScreen() {
        super(Component.literal("Client Menu Screen"));
    }

    @Override
    protected void init() {
        super.init();

        Button[] BUTTONS = {
                ItemAutoRefillButton.createButton(0,0,0,0),
        };

        Arrays.stream(BUTTONS).forEach(this::addRenderableWidget);
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float patrialTick) {
        super.render(graphics,mouseX,mouseY,patrialTick);

        this.renderBackground(graphics,mouseX,mouseY,patrialTick);
    }
}
