package com.ytgld.spontaneous_creation;

import com.ytgld.spontaneous_creation.block.InitBlockItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class SCBlockTagsProvider extends BlockTagsProvider {
    public SCBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SpontaneousCreation.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.FENCES)
                .add(InitBlockItem.HungRizomeFence_.getKey())

        ;
        tag(BlockTags.WOODEN_FENCES)
                .add(InitBlockItem.HungRizomeFence_.getKey())

        ;
        tag(BlockTags.PLANKS)
                .add(InitBlockItem.HungRizomePlanks_.getKey())

        ;

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(InitBlockItem.HungRizomeDoor_.getKey())
                .add(InitBlockItem.HungRizomeFence_.getKey())
                .add(InitBlockItem.HungRizomePlanks_.getKey())
                .add(InitBlockItem.HungRizomePlanksStairs_.getKey())
                .add(InitBlockItem.HungRizomeSlab_.getKey())
                .add(InitBlockItem.HungRhizome_.getKey())
                .add(InitBlockItem.PeelHungRhizome_.getKey())
                .add(InitBlockItem.StorageMelonSlice_.getKey())
                .add(InitBlockItem.RhizomeVine_.getKey())
                .add(InitBlockItem.RhizomeMelon_.getKey())
        ;
    }
}
