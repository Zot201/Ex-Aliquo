package exaliquo;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import exaliquo.bridges.ArsMagica.ArsMagica;
import exaliquo.bridges.Dart.Dartcraft;
import exaliquo.bridges.Growthcraft.Growthcraft;
import exaliquo.bridges.Mariculture.AliquoFish;
import exaliquo.bridges.Mariculture.Mariculture;
import exaliquo.bridges.Metallurgy.Metallurgy;
import exaliquo.bridges.MineFactoryReloaded.MineFactoryReloaded;
import exaliquo.bridges.Natura.Natura;
import exaliquo.bridges.TConstruct.TConstruct;
import exaliquo.bridges.Thaumcraft.ExThaumiquo;
import exaliquo.bridges.Thaumcraft.Thaumcraft;
import exaliquo.bridges.ThermalExpansion.ThermalExpansion;
import exaliquo.bridges.crossmod.Crossmod;
import exaliquo.data.AliquoEvents;
import exaliquo.data.Colors;
import exaliquo.data.Configurations;
import exaliquo.data.ExAOreTab;
import exaliquo.data.ExATab;
import exaliquo.data.AliquoTickHandler;
import exaliquo.data.OreDictDrops;
import exaliquo.proxy.ForestryReflection;
import static exaliquo.data.ModsLoaded.*;

@Mod(modid = "exaliquo", name = "Ex Aliquo", version = "0.11.2", dependencies = "required-after:crowley.skyblock@[1.26b,);after:TConstruct@(1.5.2,];after:Natura@[2.1.14,);after:arsmagica2;after:Thaumcraft@[4.1,);after:Growthcraft|Apples;after:Growthcraft|Bamboo;after:Growthcraft|Bees;after:Growthcraft|Grapes;after:Growthcraft|Hops;after:Growthcraft|Rice;after:Mariculture@[1.2.2,);after:MineFactoryReloaded;after:NetherOres;after:Metallurgy3Base;after:ExtraTiC;after:Forestry;after:ExtraTrees;after:pamharvestcraft;after:ThermalExpansion")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class exaliquo {

	@Instance("exaliquo")
	public static exaliquo instance;
	
	public static final Logger logger = Logger.getLogger("exaliquo");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configurations.Load(event.getModConfigurationDirectory());
		Registries.exatab = new ExATab("ExA");
		Registries.oretab = new ExAOreTab("ExAOres");
		Registries.registerItems();
		Registries.registerBlocks();
		Registries.registerRecipes();
		Registries.exatab.initTab(new ItemStack(Registries.crookGold, 1, 0));
		Registries.oretab.initTab(new ItemStack(Registries.cobaltOreItem, 1, 0));
		
		MinecraftForge.EVENT_BUS.register(new AliquoEvents());
		TickRegistry.registerTickHandler(new AliquoTickHandler(), Side.CLIENT);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		Registries.postInitHammers();
		
		if (Configurations.isOre)
		{
			Registries.registerNihiloOreDict();
		}
		GeneralAliquo.initGeneralStuff();
		Crossmod.initCross();
		OreDictDrops.CheckMetals();
		if (isTConLoaded)
		{
			exaliquo.logger.log(Level.INFO,"Loading Tinker's Construct Compat");
			TConstruct.initTConstruct();
		}
		if (isNaturaLoaded)
		{
			exaliquo.logger.log(Level.INFO,"Loading Natura Compat");
			Natura.initNatura();
		}
		if (isArsMagicaLoaded)
		{
			exaliquo.logger.log(Level.INFO,"Loading Ars Magica 2 Compat");
			ArsMagica.initArsMagica();
		}
		if (isThaumcraftLoaded)
		{
			exaliquo.logger.info("Loading Thaumcraft 4 Compat");
			Thaumcraft.initThaumcraft();
		}
		if(isGrowthcraftLoaded)
		{
			exaliquo.logger.info("Loading Growthcraft Compat");
			Growthcraft.initGrowthcraft();
		}
		if (isMaricultureLoaded)
		{
			exaliquo.logger.info("Loading Mariculture Compat");
			Mariculture.initMariculture();
		}
		if (isMFRLoaded)
		{
			exaliquo.logger.info("Loading MFR Compat");
			MineFactoryReloaded.initMFR();
		}
		if (isMetallurgyLoaded)
		{
			exaliquo.logger.info("Loading Metallurgy Compat");
			Metallurgy.initMetallurgy();
		}
		if (isDartcraftLoaded)
		{
			exaliquo.logger.info("Loading Dartcraft Compat");
			Dartcraft.initDartcraft();
		}
		if (isForestryLoaded)
		{
			ForestryReflection.initProxy();
		}
		if (isThermalExpansionLoaded)
		{
			exaliquo.logger.info("Loading Thermal Expansion Compat");
			ThermalExpansion.initThermalExpansion();
		}
	}
}
