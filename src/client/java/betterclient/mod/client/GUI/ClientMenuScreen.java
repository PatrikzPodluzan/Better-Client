package betterclient.mod.client.GUI;

import betterclient.mod.client.GUI.Buttons.ArmorDurabilityCheckButton;
import betterclient.mod.client.GUI.Buttons.ItemAutoRefillButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ClientMenuScreen extends Screen {
    public ClientMenuScreen() {
        super(Component.literal("Better Client Menu"));
    }

    @Override
    protected void init() {
        super.init();

        Button[] BUTTONS = {
                ItemAutoRefillButton.createButton(0,0,100,20),
                ArmorDurabilityCheckButton.createButton(0,25,100,20)
        };

        Arrays.stream(BUTTONS).forEach(this::addRenderableWidget);
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float patrialTick) {
        super.render(graphics,mouseX,mouseY,patrialTick);
    }
}
