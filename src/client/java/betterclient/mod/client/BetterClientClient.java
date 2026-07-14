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

import betterclient.mod.client.Events.PlayerWorldJoinEvent;
import betterclient.mod.client.Events.ClientTickEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class BetterClientClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register(PlayerWorldJoinEvent.getEvent());
        ClientTickEvents.START_CLIENT_TICK.register(ClientTickEvent.getEvent());
    }
}