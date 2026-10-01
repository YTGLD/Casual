package com.ytgld.spontaneous_creation;

import com.ytgld.spontaneous_creation.block.InitBlockItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;


public class TagsProvider extends ItemTagsProvider {
    public static final TagKey<Item> Rizome = create("rizome");
    public TagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider, SpontaneousCreation.MODID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.CROPS_WHEAT)
                .add(InitBlockItem.RichInFlourRhizomes_.getKey());
        tag(Rizome)
                .add(InitBlockItem.HungRhizome_Item.getKey())
                .add(InitBlockItem.PeelHungRhizome_Item.getKey())
        ;
        tag(ItemTags.PLANKS)
                .add(InitBlockItem.HungRizomePlanks_Item.getKey())

        ;
        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(InitBlockItem.RhizomeMelonSeed_.getKey())

        ;
    }

    private static TagKey<Item> create(String name) {
        return ItemTags.create(SpontaneousCreation.fromNamespaceAndPath(name));
    }
}
