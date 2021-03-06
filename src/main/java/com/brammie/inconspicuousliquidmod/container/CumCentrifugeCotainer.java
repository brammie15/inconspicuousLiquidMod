package com.brammie.inconspicuousliquidmod.container;



import com.brammie.inconspicuousliquidmod.init.ModContainerTypes;
import com.brammie.inconspicuousliquidmod.init.blockInit;
import com.brammie.inconspicuousliquidmod.tileentity.CumCentrifugeTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraftforge.items.SlotItemHandler;
import org.jline.utils.NonBlockingInputStream;

import java.awt.*;
import java.util.Objects;

public class CumCentrifugeCotainer extends Container {
    public final CumCentrifugeTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    //11 11
    public CumCentrifugeCotainer(final int windowId, final PlayerInventory playerInventory, final CumCentrifugeTileEntity tileEntity) {
        super(ModContainerTypes.CUM_CENTRIFUGE.get(),windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(),tileEntity.getPos());

        int startX = 8;
        int startY = 18;
        int slotSizePlus2 = 18;

        /*this.addSlot(new Slot(playerInventory,37,6,23));
        //22 42
        this.addSlot(new Slot(playerInventory, 38 , 40,56));
        //140 11
        this.addSlot(new Slot(playerInventory, 39, 140, 11));*/

        // Furnace Slots
        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 6,23));
        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 1, 40,56));
        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 2, 140, 11));

        // Main Player Inventory
        int startPlayerInvY = startY * 5 + 12;
        startPlayerInvY = 84;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * slotSizePlus2),
                        startPlayerInvY + (row * slotSizePlus2)));
            }
        }

        // Hotbar
        int hotbarY = startPlayerInvY + (startPlayerInvY / 2) + 7;
        hotbarY = 142;
        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizePlus2), hotbarY));
        }
    }//8 142


    private static CumCentrifugeTileEntity getTileEntity(final PlayerInventory playerInventory,
                                                        final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof CumCentrifugeTileEntity) {
            return (CumCentrifugeTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }
    public CumCentrifugeCotainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }
    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, blockInit.CUMCENTRIFUGE.get());
    }
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 36) {
                if (!this.mergeItemStack(itemstack1, 36, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
