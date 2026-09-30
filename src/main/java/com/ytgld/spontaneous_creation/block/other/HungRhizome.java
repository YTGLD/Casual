package com.ytgld.spontaneous_creation.block.other;

import com.ytgld.spontaneous_creation.block.InitBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class HungRhizome extends Block {

    public static final BooleanProperty GRASS = BooleanProperty.create("grass");
    public static final BooleanProperty MUSHROOM = BooleanProperty.create("mushroom");
    public static final BooleanProperty HOLD = BooleanProperty.create("hold");

    public HungRhizome(Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(GRASS, false)
                        .setValue(MUSHROOM, false)
                        .setValue(HOLD, false)
        );
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return getStateWithConnections(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos(), this.defaultBlockState());
    }

    public static BlockState getStateWithConnections(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        BlockState blockState2 = blockGetter.getBlockState(blockPos.below());
        BlockState blockState3 = blockGetter.getBlockState(blockPos.above());
        BlockState blockState4 = blockGetter.getBlockState(blockPos.north());
        BlockState blockState5 = blockGetter.getBlockState(blockPos.east());
        BlockState blockState6 = blockGetter.getBlockState(blockPos.south());
        BlockState blockState7 = blockGetter.getBlockState(blockPos.west());

        int offset = blockPos.hashCode() +
                blockState2.hashCode() +
                blockState3.hashCode() +
                blockState4.hashCode() +
                blockState5.hashCode() +
                blockState6.hashCode() +
                blockState7.hashCode();
        offset /= 14;
        offset  = offset % 10;
        if (offset < 0) {
            offset = -offset;
        }

        boolean mushroom = offset == 3;
        boolean grass = offset >= 5;
        boolean hold = offset <= 1;
        return blockState
                .trySetValue(MUSHROOM, mushroom)
                .trySetValue(GRASS, grass)
                .trySetValue(HOLD, hold)

                ;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GRASS,MUSHROOM,HOLD);
    }
    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of(this.asItem().getDefaultInstance());
    }
}

