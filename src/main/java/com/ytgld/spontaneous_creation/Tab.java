package com.ytgld.spontaneous_creation;

import com.ytgld.spontaneous_creation.block.InitBlockItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Tab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpontaneousCreation.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MODE_TAB_CREATIVE_MODE_TAB_DEFERRED_HOLDER =
            CREATIVE_MODE_TABS.register("spontaneous_creation", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.spontaneous_creation"))
            .icon(() -> InitBlockItem.RhizomeVine_Item.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(InitBlockItem.RhizomeVine_Item.get());
                output.accept(InitBlockItem.RhizomeMelonSeed_.get());
                output.accept(InitBlockItem.RichInFlourRhizomes_.get());
                output.accept(InitBlockItem.MelonSlice_.get());
                output.accept(InitBlockItem.StorageMelonSlice_Item.get());
                output.accept(InitBlockItem.HungRhizome_Item.get());
                output.accept(InitBlockItem.PeelHungRhizome_Item.get());
                output.accept(InitBlockItem.HungRizomePlanks_Item.get());
                output.accept(InitBlockItem.HungRizomePlanksStairs_Item.get());
                output.accept(InitBlockItem.HungRizomeFence__Item.get());
            }).build());

}
