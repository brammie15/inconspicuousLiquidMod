package com.brammie.inconspicuousliquidmod.tileentity;

import com.brammie.inconspicuousliquidmod.init.ModTileEntityTypes;
import com.brammie.inconspicuousliquidmod.util.InventoryTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class CumCentrifugeTileEntity extends InventoryTile implements INamedContainerProvider {


    public CumCentrifugeTileEntity(TileEntityType<?> tileEntityTypeIn, int size) {
        super(tileEntityTypeIn, size);
    }
    public CumCentrifugeTileEntity(){
        super(ModTileEntityTypes.CUMCENTRIFUGE.get(),39);
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }
    /*public IItemHandlerModifiable getInventory() {
        return this.items;
    }
    public ItemStack getItemInSlot(int slot) {
        return this.itemHandler.map(inventory -> inventory.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
    }*/
}
