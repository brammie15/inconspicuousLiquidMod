package com.brammie.inconspicuousliquidmod.objects.blocks;

import com.brammie.inconspicuousliquidmod.init.ModTileEntityTypes;
import com.brammie.inconspicuousliquidmod.tileentity.CumCentrifugeTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class CumCentrifuge extends Block{

	public CumCentrifuge(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.CUMCENTRIFUGE.get().create();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote){
			TileEntity tile = worldIn.getTileEntity(pos);
			if(tile instanceof CumCentrifugeTileEntity){
				NetworkHooks.openGui((ServerPlayerEntity)player, (CumCentrifugeTileEntity)tile, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;
	}

	/*@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if(state.getBlock() != newState.getBlock()){
			TileEntity te = worldIn.getTileEntity(pos);
			if(te instanceof CumCentrifugeTileEntity){
				InventoryHelper.dropItems(worldIn, pos, ((CumCentrifugeTileEntity) te).getItems());
			}
		}
	}
*/
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof CumCentrifugeTileEntity) {
			CumCentrifugeTileEntity choppingBoard = (CumCentrifugeTileEntity) tile;
			for (int slotIndex = 0; slotIndex < choppingBoard.getInventory().getSlots(); slotIndex++) {
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
						choppingBoard.getItemInSlot(slotIndex));
			}
		}

		if (state.hasTileEntity() && (state.getBlock() != newState.getBlock() || !newState.hasTileEntity())) {
			worldIn.removeTileEntity(pos);
		}
	}
}
