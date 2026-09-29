package com.ytgld.spontaneous_creation.block.other.planks;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class HungRizomePlanks extends Block {
    public HungRizomePlanks(Properties properties) {
        super(properties);
    }
    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of(this.asItem().getDefaultInstance());
    }
}


