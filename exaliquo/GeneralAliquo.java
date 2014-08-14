package exaliquo;

import static exaliquo.data.ModIDs.getIDs;
import static exnihilo.registries.HammerRegistry.register;

import java.util.ArrayList;

import exaliquo.bridges.crossmod.Whenk;
import exaliquo.data.Configurations;
import exaliquo.data.ModsLoaded;
import exaliquo.data.ModIDs.Info;
import exnihilo.registries.ColorRegistry;
import exnihilo.registries.CompostRegistry;
import exnihilo.registries.HammerRegistry;
import exnihilo.registries.SieveRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GeneralAliquo
{
	
	public static void initGeneralStuff()
	{
		registerGeneralCompost();
		registerGeneralSieving();
		registerGeneralHammering();
	}
	
	public static void registerGeneralCompost()
	{
		ArrayList<ItemStack> treeSapling = OreDictionary.getOres("treeSapling");
		ArrayList<ItemStack> treeLeaves = OreDictionary.getOres("treeLeaves");
		
		
		for (ItemStack sapling : treeSapling)
		{
			if (sapling.itemID != 6)
			{
					CompostRegistry.register(sapling.itemID, sapling.getItemDamage(), 0.125F, ColorRegistry.color("oak"));
			}
		}
		for (ItemStack leaves : treeLeaves)
		{
			if (leaves.itemID != 18)
			{
				CompostRegistry.register(leaves.itemID, leaves.getItemDamage(), 0.125F, ColorRegistry.color("oak"));
			}
		}
	}

	public static void registerGeneralSieving()
	{
		SieveRegistry.register(Block.lavaStill.blockID, 0, Item.fishCooked.itemID, 0, 1);
		SieveRegistry.register(Block.waterStill.blockID, 0, Item.fishRaw.itemID, 0, 1);
		SieveRegistry.register(Block.dragonEgg.blockID, 0, Registries.dragonEgg.itemID, 0, 1);
	}
	
	public static void registerGeneralHammering()
	{
		if (Configurations.ninjaFeesh)
		{
			HammerRegistry.register(Block.silverfish.blockID, 0, 0, 0, 0.0F, 0.0F);
			HammerRegistry.register(Block.silverfish.blockID, 1, 0, 0, 0.0F, 0.0F);
			HammerRegistry.register(Block.silverfish.blockID, 2, 0, 0, 0.0F, 0.0F);
			if (ModsLoaded.isNetherOresLoaded)
			{
				Whenk.NinjaFeesh();
			}
		}
	}	
}
