package com.cleannrooster.rpgmana.mixin;

import com.cleannrooster.rpgmana.api.ManaInterface;
import com.spellbladenext.items.MonkeyStaff;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import safro.archon.api.ManaAttributes;
import safro.archon.api.ManaComponent;
import safro.archon.item.UndeadStaffItem;
import safro.archon.registry.ComponentsRegistry;
import safro.archon.registry.EffectRegistry;

import javax.security.auth.callback.Callback;

@Mixin(ManaComponent.class)
public class ManaComponentMixin {
    @Shadow
    PlayerEntity player;
    @Overwrite(remap = false)
    public void serverTick(){

    }
    @Inject(at = @At("HEAD"), method = "canUseMana", remap = false,cancellable = true)
    public void canUseMana(int amount, CallbackInfoReturnable<Boolean> returnable) {
        if (this.player.hasStatusEffect(EffectRegistry.OBSTRUCTED)) {
            returnable.setReturnValue(false);
        } else {
            returnable.setReturnValue (((ManaInterface)player).getMana() > 0);
        }
    }
    @Inject(at = @At("HEAD"), method = "getMana", remap = false,cancellable = true)
    private void getManaRPG(CallbackInfoReturnable<Integer> info) {
        info.setReturnValue((int) ((ManaInterface)player).getMana());
    }
    @Inject(at = @At("HEAD"), method = "getMaxMana",cancellable = true, remap = false)
    private void getMaxManaRPG(CallbackInfoReturnable<Integer> info) {
        info.setReturnValue((int) ((ManaInterface)player).getMaxMana());
    }
    @Inject(at = @At("HEAD"), method = "getRegenSpeed", remap = false,cancellable = true)
    private void getRegenSpeed( CallbackInfoReturnable<Integer> info) {
        info.setReturnValue(0);
    }
    @Overwrite(remap = false)
    public void removeMana(int amount) {
        ((ManaInterface)player).spendMana(-amount);
    }
    @Overwrite(remap = false)
    public void addMana(int mana) {
        ManaInterface player1 = ((ManaInterface)player);
        player1.addManaRPG(mana);
    }
    @Overwrite(remap = false)
    public void setMana(int amount) {
        ((ManaInterface)player).setMana(amount);
    }
}
