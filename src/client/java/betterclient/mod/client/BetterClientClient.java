/*
 * Copyright 2026 PatrikzPodluzan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package betterclient.mod.client;

import betterclient.mod.client.Features.ArmorDurabilityCheck;
import betterclient.mod.client.Features.ItemAutoRefill;
import betterclient.mod.client.GUI.ClientMenuScreen;
import betterclient.mod.client.Messages.Messages;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;

public class BetterClientClient implements ClientModInitializer {
    private static final ClientPlayConnectionEvents.Join PLAYER_WORLD_JOIN_EVENT = new ClientPlayConnectionEvents.Join() {
        @Override
        public void onPlayReady(
                @NotNull ClientPacketListener clientPacketListener,
                @NotNull PacketSender packetSender,
                @NotNull Minecraft minecraft
            ) {
            if (minecraft.player != null) {
                minecraft.player.displayClientMessage(Messages.PLAYER_JOIN_MESSAGE,false);
            }
        }
    };
    private static final KeyMapping menuOpenKey = KeyBindingHelper.registerKeyBinding(
            new KeyMapping(
                    "key.better-client.open_cheat_menu",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_M,
                    KeyMapping.Category.MISC
            )
    );
    private static final ClientTickEvents.StartTick[] FEATURES_FIELD = {
        ArmorDurabilityCheck.getEvent(),
            ItemAutoRefill.getEvent(),
    };

	@Override
	public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register(PLAYER_WORLD_JOIN_EVENT);
        Arrays.stream(FEATURES_FIELD).forEach(ClientTickEvents.START_CLIENT_TICK::register);
        ClientTickEvents.END_CLIENT_TICK.register(new ClientTickEvents.EndTick() {
            @Override
            public void onEndTick(@NotNull Minecraft minecraft) {
                while (menuOpenKey.consumeClick()) {
                    if (minecraft.screen == null) {
                        minecraft.setScreen(new ClientMenuScreen());
                    }
                }
            }
        });
    }
}