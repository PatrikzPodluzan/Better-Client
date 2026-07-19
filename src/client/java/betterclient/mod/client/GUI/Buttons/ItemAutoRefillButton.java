package betterclient.mod.client.GUI.Buttons;

import betterclient.mod.client.Messages.Messages;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ItemAutoRefillButton {
    protected static boolean IS_ACTIVE = false;

    public static Button createButton(int x,int y,int width,int height) {
        return Button.builder(Component.literal("Item Auto Refill"), new Button.OnPress() {
            @Override
            public void onPress(@NotNull Button button) {
                Minecraft minecraft = Minecraft.getInstance();

                if (minecraft.player != null) {
                    minecraft.player.displayClientMessage(Messages.ITEM_AUTO_REFILL_MESSAGE,false);
                    IS_ACTIVE = !IS_ACTIVE;
                }
            }
        }).bounds(x,y,width,height).build();
    }
}
