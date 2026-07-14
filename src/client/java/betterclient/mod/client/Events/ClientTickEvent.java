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

package betterclient.mod.client.Events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.HashedStack;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import betterclient.mod.client.Messages.Messages;

public class ClientTickEvent {
    private static int HelmetWarnUsageCount = 0;
    private static int ChestplateWarnUsageCount = 0;
    private static int LeggingsWarningUsageCount = 0;
    private static int BootsWarningUsageCount = 0;

    public static ClientTickEvents.StartTick getEvent() {
        return new ClientTickEvents.StartTick() {
            @Override
            public void onStartTick(@NotNull Minecraft minecraft) {
                if (minecraft.player != null) {
                    EquipmentSlot[] PlayerArmorSlots = {
                            EquipmentSlot.HEAD,
                            EquipmentSlot.CHEST,
                            EquipmentSlot.LEGS,
                            EquipmentSlot.FEET
                    };
                    Item SelectedItem = (minecraft.player.getMainHandItem()).getItem();
                    int ContainerID = minecraft.player.containerMenu.containerId;

                    for (EquipmentSlot PlayerArmorSlot:PlayerArmorSlots) {
                        ItemStack ArmorPiece = minecraft.player.getItemBySlot(PlayerArmorSlot);
                        int MaximalDamage = ArmorPiece.getMaxDamage() / 100 * 80;

                        if (!ArmorPiece.isEmpty() && ArmorPiece.isDamageableItem()) {
                            if (PlayerArmorSlot == EquipmentSlot.HEAD) {
                                if (ArmorPiece.getDamageValue() > MaximalDamage) {
                                    HelmetWarnUsageCount ++;
                                }
                                if (HelmetWarnUsageCount >= 112 ) {
                                    minecraft.player.displayClientMessage(Messages.HELMET_DURABILITY_WARN_MESSAGE,false);
                                    HelmetWarnUsageCount = -112;
                                }
                            }
                            if (PlayerArmorSlot == EquipmentSlot.CHEST) {
                                if (ArmorPiece.getDamageValue() > MaximalDamage) {
                                    ChestplateWarnUsageCount ++;
                                }
                                if (ChestplateWarnUsageCount >= 112) {
                                    minecraft.player.displayClientMessage(Messages.CHESTPLATE_DURABILITY_WARN_MESSAGE,false);
                                    ChestplateWarnUsageCount = -112;
                                }
                            }
                            if (PlayerArmorSlot == EquipmentSlot.LEGS) {
                                if (ArmorPiece.getDamageValue() > MaximalDamage) {
                                    LeggingsWarningUsageCount ++;
                                }
                                if (LeggingsWarningUsageCount >= 112) {
                                    minecraft.player.displayClientMessage(Messages.LEGGINGS_DURABILITY_WARN_MESSAGE,false);
                                    LeggingsWarningUsageCount = -122;
                                }
                            }
                            if (PlayerArmorSlot == EquipmentSlot.FEET) {
                                if (ArmorPiece.getDamageValue() > MaximalDamage) {
                                    BootsWarningUsageCount ++;
                                }
                                if (BootsWarningUsageCount  >= 112) {
                                    minecraft.player.displayClientMessage(Messages.BOOTS_DURABILITY_WARN_MESSAGE,false);
                                    BootsWarningUsageCount = -122;
                                }
                            }
                        }
                    }

                    if (SelectedItem != Items.AIR && minecraft.options.keyUse.isDown() ) {
                        for (int i = 9; i <= 35; i++) {
                            ItemStack INVSlotItem = minecraft.player.getInventory().getItem(i);

                            if (!INVSlotItem.isEmpty() && INVSlotItem.getItem() == SelectedItem &&  minecraft.player.getMainHandItem().getCount() < SelectedItem.getDefaultMaxStackSize()) {
                                ServerboundContainerClickPacket packet =
                                        new ServerboundContainerClickPacket(
                                                ContainerID,
                                                minecraft.player.inventoryMenu.getStateId(),
                                                (short) i,
                                                (byte) 0,
                                                ClickType.QUICK_MOVE,
                                                Int2ObjectMaps.emptyMap(),
                                                HashedStack.EMPTY
                                        );

                                minecraft.player.connection.send(packet);
                                break;
                            }
                        }
                    }
                }
            }
        };
    }
}