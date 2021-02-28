package com.brammie.inconspicuousliquidmod.init;

import com.brammie.inconspicuousliquidmod.container.CumCentrifugeCotainer;
import com.brammie.inconspicuousliquidmod.inconspicuousLiquidMod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, inconspicuousLiquidMod.MOD_ID);
    public static final RegistryObject<ContainerType<CumCentrifugeCotainer>> CUM_CENTRIFUGE = CONTAINER_TYPES.register("cum_centrifuge", () -> IForgeContainerType.create(CumCentrifugeCotainer::new));

}
