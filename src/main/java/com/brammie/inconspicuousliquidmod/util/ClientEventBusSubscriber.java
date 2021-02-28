package com.brammie.inconspicuousliquidmod.util;

import com.brammie.inconspicuousliquidmod.client.gui.CumCentrifugeScreen;
import com.brammie.inconspicuousliquidmod.inconspicuousLiquidMod;
import com.brammie.inconspicuousliquidmod.init.ModContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = inconspicuousLiquidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainerTypes.CUM_CENTRIFUGE.get(), CumCentrifugeScreen::new);
    }
}
