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

package betterclient.mod.client.Features;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.jetbrains.annotations.NotNull;
import betterclient.mod.client.Messages.Messages;

public class PlayerWorldJoinEvent {
    public static ClientPlayConnectionEvents.Join getEvent() {
        return new ClientPlayConnectionEvents.Join() {
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
    }
}
