package dev.mauritzen.norsecraft.util;

import dev.mauritzen.norsecraft.Norsecraft;
import dev.mauritzen.norsecraft.blocks.BlockItemBase;
import dev.mauritzen.norsecraft.blocks.NorseGemBlock;
import dev.mauritzen.norsecraft.blocks.NorseGemOreBlock;
import dev.mauritzen.norsecraft.items.IdunApple;
import dev.mauritzen.norsecraft.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Norsecraft.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Norsecraft.MOD_ID);

	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	// Items
	public static final RegistryObject<Item> NORSE_GEM = ITEMS.register("norsegem", ItemBase::new);

	// Food
	public static final RegistryObject<Item> IDUN_APPLE = ITEMS.register("idun_apple", IdunApple::new);

	// Blocks
	public static final RegistryObject<Block> NORSE_GEM_BLOCK = BLOCKS.register("norsegem_block", NorseGemBlock::new);
	public static final RegistryObject<Block> NORSE_GEM_ORE_BLOCK = BLOCKS.register("norsegem_ore_block", NorseGemOreBlock::new);
	
	// Block Items
	public static final RegistryObject<Item> NORSE_GEM_BLOCK_ITEM = ITEMS.register("norsegem_block", () -> new BlockItemBase(NORSE_GEM_BLOCK.get()));
	public static final RegistryObject<Item> NORSE_GEM_ORE_BLOCK_ITEM = ITEMS.register("norsegem_ore_block", () -> new BlockItemBase(NORSE_GEM_ORE_BLOCK.get()));
	
	
}
