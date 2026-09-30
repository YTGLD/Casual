package com.ytgld.spontaneous_creation.block.other.planks;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class HungRizomePlanksStairs extends StairBlock {
    public HungRizomePlanksStairs(BlockState baseState, Properties properties) {
        super(baseState, properties);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of(this.asItem().getDefaultInstance());
    }
}

