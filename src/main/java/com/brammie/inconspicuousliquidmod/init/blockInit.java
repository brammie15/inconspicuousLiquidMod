package com.brammie.inconspicuousliquidmod.init;

import com.brammie.inconspicuousliquidmod.inconspicuousLiquidMod;
import com.brammie.inconspicuousliquidmod.objects.blocks.CumCentrifuge;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class blockInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
			inconspicuousLiquidMod.MOD_ID);
	
	public static final RegistryObject<com.brammie.inconspicuousliquidmod.objects.blocks.CumCentrifuge> CUMCENTRIFUGE = BLOCKS.register("cumcentrifuge", () -> new CumCentrifuge(Block.Properties.from(Blocks.IRON_BLOCK)));
}
