package exaliquo.bridges.Metallurgy;

import cpw.mods.fml.common.Loader;

public class Metallurgy
{

	public static void initMetallurgy()
	{
		//Hammering.HammerMetallurgy();
		//OreDict.registerExMetallurgyOreDict();
		if (Loader.isModLoaded("ExtraTiC")) 
		{
			System.out.println("Smelting Metallurgy metals");
			Smelting.SmeltMetallurgy();
		}
	}
}
