package com.brammie.inconspicuousliquidmod.objects.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CumCollector extends Item{

	public CumCollector(Properties properties) {
		super(properties);
	}
	
		
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		// TODO Auto-generated method stub
		//return super.onItemRightClick(worldIn, playerIn, handIn);
		
		if(worldIn.isRemote) {
			
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}else {
			
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
	}
}
