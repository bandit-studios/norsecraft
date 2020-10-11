package dev.mauritzen.norsecraft.util;

import dev.mauritzen.norsecraft.Norsecraft;
import dev.mauritzen.norsecraft.blocks.BlockItemBase;
import dev.mauritzen.norsecraft.blocks.NorseGemBlock;
import dev.mauritzen.norsecraft.blocks.NorseGemOreBlock;
import dev.mauritzen.norsecraft.entities.BoarEntity;
import dev.mauritzen.norsecraft.entities.RavenEntity;
import dev.mauritzen.norsecraft.items.Dropnir;
import dev.mauritzen.norsecraft.items.IdunApple;
import dev.mauritzen.norsecraft.items.ItemBase;
import dev.mauritzen.norsecraft.items.NorseGuide;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Norsecraft.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Norsecraft.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Norsecraft.MOD_ID);
	
	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	// Items
	public static final RegistryObject<Item> NORSE_GEM = ITEMS.register("norsegem", ItemBase::new);
	public static final RegistryObject<Item> NORSE_GUIDE = ITEMS.register("norse_guide", NorseGuide::new);
	public static final RegistryObject<Item> DROPNIR = ITEMS.register("dropnir", Dropnir::new);

	// Food
	public static final RegistryObject<Item> IDUN_APPLE = ITEMS.register("idun_apple", IdunApple::new);

	// Blocks
	public static final RegistryObject<Block> NORSE_GEM_BLOCK = BLOCKS.register("norsegem_block", NorseGemBlock::new);
	public static final RegistryObject<Block> NORSE_GEM_ORE_BLOCK = BLOCKS.register("norsegem_ore_block", NorseGemOreBlock::new);
	
	// Block Items
	public static final RegistryObject<Item> NORSE_GEM_BLOCK_ITEM = ITEMS.register("norsegem_block", () -> new BlockItemBase(NORSE_GEM_BLOCK.get()));
	public static final RegistryObject<Item> NORSE_GEM_ORE_BLOCK_ITEM = ITEMS.register("norsegem_ore_block", () -> new BlockItemBase(NORSE_GEM_ORE_BLOCK.get()));
	
	// Entities
	public static final RegistryObject<EntityType<BoarEntity>> BOAR_ENTITY = 
			ENTITY_TYPES.register("boar",
					() -> EntityType.Builder.create(BoarEntity::new, EntityClassification.CREATURE)
					.size(1.0f, 1.0f)
					.build(new ResourceLocation(Norsecraft.MOD_ID, "boar").toString())
					);
	
	public static final RegistryObject<EntityType<RavenEntity>> RAVEN_ENTITY = 
			ENTITY_TYPES.register("raven",
					() -> EntityType.Builder.create(RavenEntity::new, EntityClassification.CREATURE)
					.size(1.0f, 1.0f)
					.build(new ResourceLocation(Norsecraft.MOD_ID, "raven").toString())
					);
	
}
