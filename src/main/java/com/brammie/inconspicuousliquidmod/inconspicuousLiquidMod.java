package com.brammie.inconspicuousliquidmod;

import com.brammie.inconspicuousliquidmod.init.ModContainerTypes;
import com.brammie.inconspicuousliquidmod.init.ModTileEntityTypes;
import com.brammie.inconspicuousliquidmod.init.blockInit;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.brammie.inconspicuousliquidmod.init.itemInit;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.rmi.registry.Registry;

@Mod("cm")
@Mod.EventBusSubscriber(modid = inconspicuousLiquidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class inconspicuousLiquidMod
{
	public static final String MOD_ID = "cm"; 
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();

    public inconspicuousLiquidMod() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        itemInit.ITEMS.register(modEventBus);
        blockInit.BLOCKS.register(modEventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        ModContainerTypes.CONTAINER_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event){
        final IForgeRegistry<Item> registry = event.getRegistry();
        blockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
                final Item.Properties properties = new Item.Properties().group(CumItemGroup.instance);
                final BlockItem blockItem = new BlockItem(block, properties);
                blockItem.setRegistryName(block.getRegistryName());
                registry.register(blockItem);
        });
    }
    private void setup(final FMLCommonSetupEvent event){
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    
    }
    
    public static class CumItemGroup extends ItemGroup{
    	public static final CumItemGroup instance = new CumItemGroup(ItemGroup.GROUPS.length, "cumtab");
		public CumItemGroup(int index, String label) {
			super(index, label);
		}
		@Override
		public ItemStack createIcon() {
			return new ItemStack(itemInit.CUM_COLLECTOR.get());
		}
    	
    }

}
