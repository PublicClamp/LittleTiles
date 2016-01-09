package com.creativemd.littletiles.common.utils;

import java.util.ArrayList;

import com.creativemd.creativecore.common.utils.ColorUtils;
import com.creativemd.creativecore.common.utils.CubeObject;
import com.creativemd.littletiles.LittleTiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.Vec3;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;

public class LittleTileBlockColored extends LittleTileBlock{
	
	public Vec3 color;
	
	public LittleTileBlockColored(Block block, int meta, Vec3 color)
	{
		super(block, meta);
		this.color = color;
	}
	
	public LittleTileBlockColored()
	{
		super();
	}
	
	@Override
	public void updatePacket(NBTTagCompound nbt)
	{
		super.updatePacket(nbt);
		nbt.setInteger("color", ColorUtils.RGBToInt(color));
	}
	
	@Override
	public void receivePacket(NBTTagCompound nbt, NetworkManager net)
	{
		super.receivePacket(nbt, net);
		color = ColorUtils.IntToRGB(nbt.getInteger("color"));
	}
	
	@Override
	public ArrayList<CubeObject> getRenderingCubes() {
		ArrayList<CubeObject> cubes = super.getRenderingCubes();
		int color = ColorUtils.RGBToInt(this.color);
		for (int i = 0; i < cubes.size(); i++) {
			cubes.get(i).color = color;
		}
		return cubes;
	}
	
	@Override
	public void copyExtra(LittleTile tile) {
		super.copyExtra(tile);
		if(tile instanceof LittleTileBlockColored)
		{
			LittleTileBlockColored thisTile = (LittleTileBlockColored) tile;
			thisTile.color = Vec3.createVectorHelper(color.xCoord, color.yCoord, color.zCoord);
		}
	}
	
	@Override
	public void saveTileExtra(NBTTagCompound nbt) {
		super.saveTileExtra(nbt);
		nbt.setInteger("color", ColorUtils.RGBToInt(color));
	}

	@Override
	public void loadTileExtra(NBTTagCompound nbt) {
		super.loadTileExtra(nbt);
		color = ColorUtils.IntToRGB(nbt.getInteger("color"));
	}
	
	@Override
	public boolean canBeCombined(LittleTile tile) {
		if(tile instanceof LittleTileBlockColored && super.canBeCombined(tile))
		{
			int color1 = ColorUtils.RGBToInt(((LittleTileBlockColored) tile).color);
			int color2 = ColorUtils.RGBToInt(this.color);
			return color1 == color2;
		}
		return false;
	}
	
}
