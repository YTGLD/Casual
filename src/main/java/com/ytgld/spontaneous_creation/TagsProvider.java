package com.ytgld.spontaneous_creation;

import com.ytgld.spontaneous_creation.block.InitBlockItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class TagsProvider extends ItemTagsProvider {
    public TagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, SpontaneousCreation.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.CROPS_WHEAT)
                .add(InitBlockItem.RichInFlourRhizomes_.getKey());
    }
}
