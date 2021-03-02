package com.brammie.inconspicuousliquidmod.tileentity;

import com.brammie.inconspicuousliquidmod.container.CumCentrifugeCotainer;
import com.brammie.inconspicuousliquidmod.init.ModTileEntityTypes;
import com.brammie.inconspicuousliquidmod.init.itemInit;
import com.brammie.inconspicuousliquidmod.util.InventoryTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;

public class CumCentrifugeTileEntity extends InventoryTile implements INamedContainerProvider, ITickableTileEntity {


    public CumCentrifugeTileEntity(TileEntityType<?> tileEntityTypeIn, int size) {
        super(tileEntityTypeIn, size);
    }
    public CumCentrifugeTileEntity(){
        super(ModTileEntityTypes.CUMCENTRIFUGE.get(),3);
    }
    private boolean isSmelting = false;
    private float smeltTime = 5 * 20;
    private float currentSmeltTime = 0.0f;
    private int burnTime = 0;
    private int recipesUsed;
    private int cookTime;
    private int cookTimeTotal = 20;
    private IItemHandlerModifiable items;
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.cumcentrifuge");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new CumCentrifugeCotainer(id,playerInventory,this);
    }
    protected boolean canSmelt() {
        if (!items.getStackInSlot(0).isEmpty() ) {
            ItemStack itemstack = new ItemStack(itemInit.CUM.get(),1);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = items.getStackInSlot(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.isItemEqual(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= 64 && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }
    protected int getBurnTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(fuel);
        }
    }

    @Override
    public void tick() {
        super.tick();
        items  = this.getInventory();

        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }


        if (!this.world.isRemote) {
            ItemStack itemstack = items.getStackInSlot(1);
            if (this.isBurning() || !itemstack.isEmpty() && !items.getStackInSlot(0).isEmpty()) {
                if (!this.isBurning() && this.canSmelt()) {
                    this.burnTime = this.getBurnTime(itemstack);
                    if (this.isBurning()) {
                        flag1 = true;
                        if (itemstack.hasContainerItem())
                            this.items.setStackInSlot(1, itemstack.getContainerItem());
                        else
                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                this.items.setStackInSlot(1, itemstack.getContainerItem());
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt()) {
                    ++this.cookTime;
                    if (this.cookTime == this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = (int)this.smeltTime;
                        this.smelt();
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                //this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, Boolean.valueOf(this.isBurning())), 3);
            }
        }

        if (flag1) {
            this.markDirty();
        }

        //        if(ForgeHooks.getBurnTime(temp.getStackInSlot(1)) != -1){
//
//        }

    }

    protected int getCookTime() {
        return 20;
    }
    //(final int windowId, final PlayerInventory playerInventory, final CumCentrifugeTileEntity tileEntity
    /*public IItemHandlerModifiable getInventory() {
        return this.items;
    }

    public ItemStack getItemInSlot(int slot) {
        return this.itemHandler.map(inventory -> inventory.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
    }*/

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    private void smelt() {
        if (this.canSmelt()) {
            ItemStack itemstack = items.getStackInSlot(0);
            ItemStack itemstack1 = new ItemStack(itemInit.CUM.get(),1);
            ItemStack itemstack2 = items.getStackInSlot(2);
            if (itemstack2.isEmpty()) {
                this.items.setStackInSlot(2, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (!this.world.isRemote) {
                //this.setRecipeUsed(recipe);
            }



            itemstack.shrink(1);
        }
    }


}
