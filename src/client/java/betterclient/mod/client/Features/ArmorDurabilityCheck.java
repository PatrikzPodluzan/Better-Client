package betterclient.mod.client.Features;

import betterclient.mod.client.Messages.Messages;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ArmorDurabilityCheck {
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

                }
            }
        };
    }
}
