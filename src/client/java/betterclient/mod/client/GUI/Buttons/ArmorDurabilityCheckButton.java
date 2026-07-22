package betterclient.mod.client.GUI.Buttons;

import betterclient.mod.client.Messages.Messages;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ArmorDurabilityCheckButton {
    public static boolean IS_ACTIVE = false;

    public static Button createButton(int x, int y, int width, int height) {
        return Button.builder(Component.literal("Armor Durability Check:" + (IS_ACTIVE ? "ON" : "OFF")), new Button.OnPress() {
            @Override
            public void onPress(@NotNull Button button) {
                Minecraft minecraft = Minecraft.getInstance();
                if (minecraft.player != null) {

                    IS_ACTIVE = !IS_ACTIVE;
                    button.setMessage(Component.literal("Armor Durability Check: " + (IS_ACTIVE ? "ON" : "OFF")));

                    if (!IS_ACTIVE) {
                        minecraft.player.displayClientMessage(Messages.ARMOR_DURABILITY_CHECK_DISABLE_MESSAGE,false);
                    }
                    else {
                        minecraft.player.displayClientMessage(Messages.ARMOR_DURABILITY_CHECK_ENABLE_MESSAGE,false);
                    }
                }
            }
        }).bounds(x,y,width,height).build();
    }
}
