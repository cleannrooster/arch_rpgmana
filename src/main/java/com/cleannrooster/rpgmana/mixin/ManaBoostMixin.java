package com.cleannrooster.rpgmana.mixin;

import com.cleannrooster.rpgmana.Rpgmana;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.archon.api.ManaAttributes;
import safro.archon.api.ManaComponent;
import safro.archon.effect.ManaBoostStatusEffect;
import safro.archon.util.ArchonUtil;

import java.util.Objects;
import java.util.UUID;

@Mixin(ManaBoostStatusEffect.class)
public class ManaBoostMixin {
    @Inject(method = "onApplied", at = @At("TAIL"))
    public void onAppliedRPGMana(LivingEntity entity, AttributeContainer attributes, int amplifier, CallbackInfo info) {
        if (entity instanceof PlayerEntity player) {
            double amount = 50D * (amplifier + 1);
            EntityAttributeModifier modifier = new EntityAttributeModifier(UUID.fromString("16c4dfda-c9ad-40a1-97af-623e00437440"), "manaboost", amount, EntityAttributeModifier.Operation.ADDITION);
            EntityAttributeInstance instance = player.getAttributeInstance(Rpgmana.MANA);
            if (instance.hasModifier(modifier)) return;
            instance.addPersistentModifier(modifier);
        }
    }
    @Inject(method = "onRemoved", at = @At("TAIL"))

    public void onRemovedRPG(LivingEntity entity, AttributeContainer attributes, int amplifier, CallbackInfo info) {
        if (entity instanceof PlayerEntity player && Objects.nonNull(player.getAttributeInstance(Rpgmana.MANA))) {
            player.getAttributeInstance(Rpgmana.MANA).removeModifier(UUID.fromString("16c4dfda-c9ad-40a1-97af-623e00437440"));
        }

    }
}
