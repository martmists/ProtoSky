package pt.skyblock.mixins;

import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pt.skyblock.PTSkyblockSettings;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin
{
    @Shadow
    private BlockPos exitPortalLocation;
    
    @Inject(method = "generateEndPortal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/ConfiguredFeature;generate(Lnet/minecraft/world/IWorld;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;)Z", shift = At.Shift.BEFORE))
    private void adjustExitPortalLocation(boolean open, CallbackInfo ci)
    {
        if (PTSkyblockSettings.endPortalFix && this.exitPortalLocation.getY() < 2)
            exitPortalLocation = exitPortalLocation.up(2 - exitPortalLocation.getY());
    }
}
