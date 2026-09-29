package com.ytgld.spontaneous_creation.block;

import com.ytgld.spontaneous_creation.SpontaneousCreation;
import com.ytgld.spontaneous_creation.block.other.HungRhizome;
import com.ytgld.spontaneous_creation.block.other.PeelHungRhizome;
import com.ytgld.spontaneous_creation.block.storage.StorageMelonSlice;
import com.ytgld.spontaneous_creation.block.unground.RhizomeMelon;
import com.ytgld.spontaneous_creation.block.unground.RhizomeVine;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitBlockItem {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SpontaneousCreation.MODID);
    public static final DeferredBlock<Block> RhizomeVine_ = BLOCKS.registerBlock("rhizome_vine", (properties)->
                    new RhizomeVine(properties.strength(1.0f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> RhizomeMelon_ = BLOCKS.registerBlock("rhizome_melon", (properties)->
            new RhizomeMelon(properties.strength(1.0f)
                    .lightLevel((state)->10).sound(SoundType.WOOD)
                    .randomTicks()
            ));
    public static final DeferredBlock<Block> StorageMelonSlice_ = BLOCKS.registerBlock("storage_melon_slice", (properties)->
            new StorageMelonSlice(properties.strength(1.0f)
                    .lightLevel((state)->12).sound(SoundType.WOOD)
                    .randomTicks()
            ));
    public static final DeferredBlock<Block> HungRhizome_ = BLOCKS.registerBlock("hung_rhizome", (properties)->
            new HungRhizome(properties.strength(1.5f)
                    .sound(SoundType.WOOD)
                    .randomTicks()
            ));
    public static final DeferredBlock<Block> PeelHungRhizome_ = BLOCKS.registerBlock("peel_hung_rhizome", (properties)->
            new PeelHungRhizome(properties.strength(1.5f)
                    .sound(SoundType.WOOD)
                    .randomTicks()
            ));
    //------------------------------------------------------------------------------------------------------------------------------
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(SpontaneousCreation.MODID);
    public static final DeferredItem<BlockItem> RhizomeVine_Item = BLOCK_ITEMS.registerSimpleBlockItem(
            "rhizome_vine", RhizomeVine_);
    public static final DeferredItem<BlockItem> RhizomeMelonSeed_ = BLOCK_ITEMS.registerSimpleBlockItem(
            "rhizome_melon_seed", RhizomeMelon_);
    public static final DeferredItem<BlockItem> StorageMelonSlice_Item = BLOCK_ITEMS.registerSimpleBlockItem(
            "storage_melon_slice", StorageMelonSlice_);
    public static final DeferredItem<BlockItem> HungRhizome_Item = BLOCK_ITEMS.registerSimpleBlockItem(
            "hung_rhizome", HungRhizome_);
    public static final DeferredItem<BlockItem> PeelHungRhizome_Item = BLOCK_ITEMS.registerSimpleBlockItem(
            "peel_hung_rhizome", PeelHungRhizome_);


    //------------------------------------------------------------------------------------------------------------------------------
    public static final DeferredItem<Item> RichInFlourRhizomes_ = BLOCK_ITEMS.registerItem(
            "richin_flour_rhizomes", (properties)->new Item(properties.food(new FoodProperties.Builder()
                    .nutrition(2).saturationModifier(1).build())));
    public static final DeferredItem<Item> MelonSlice_ = BLOCK_ITEMS.registerItem(
            "melon_slice", (properties)->new Item(properties.food(new FoodProperties.Builder()
                    .nutrition(6).saturationModifier(0.6f).build())));




//------------------------------------------------------------------------------------------------------------------------------
}
