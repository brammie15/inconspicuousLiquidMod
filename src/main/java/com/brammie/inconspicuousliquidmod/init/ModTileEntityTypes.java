package com.brammie.inconspicuousliquidmod.init;

import com.brammie.inconspicuousliquidmod.inconspicuousLiquidMod;
import com.brammie.inconspicuousliquidmod.tileentity.CumCentrifugeTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

       public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, inconspicuousLiquidMod.MOD_ID);

        public static final RegistryObject<TileEntityType<CumCentrifugeTileEntity>> CUMCENTRIFUGE = TILE_ENTITY_TYPES.register("cumcentrifuge", () -> TileEntityType.Builder.create(CumCentrifugeTileEntity::new, blockInit.CUMCENTRIFUGE.get()).build(null));
}
