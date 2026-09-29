package com.ytgld.spontaneous_creation.block.unground;

import com.ytgld.spontaneous_creation.block.InitBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RhizomeMelon extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    private final Block plant;

    public RhizomeMelon(BlockBehaviour.Properties properties) {
        super(properties);
        this.plant = InitBlockItem.RhizomeVine_.get();
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 5;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int size = state.getValue(AGE) * 4 - 4;
        if (size <= 1) {
            return Block.column(6, 2, 14);
        }
        if (size > 16) {
            size = 16;
        }
        return Block.column(size, 0.0, size);
    }
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockPos below = pos.below();

        if (!level.isEmptyBlock(below) || below.getY() < level.getMinY()) {
            return;
        }

        int currentAge = state.getValue(AGE);
        if (currentAge >= 5) {
            return;
        }

        boolean growDownwards = false;
        boolean pillarOnSupportBlock = false;
        BlockState aboveState = level.getBlockState(pos.above());

        var soilDecision = aboveState.canSustainPlant(
                level, pos.above(), Direction.DOWN, state
        );

        if (!soilDecision.isDefault()) {
            growDownwards = soilDecision.isTrue();
        } else if (aboveState.isSolidRender()) {
            growDownwards = true;
        } else if (aboveState.is(this.plant)) {
            int height = 1;

            for (int i = 0; i < 4; i++) {
                BlockState testState = level.getBlockState(pos.above(height + 1));

                if (!testState.is(this.plant)) {
                    var soilDecision2 = testState.canSustainPlant(
                            level, pos.above(height + 1), Direction.DOWN, state
                    );

                    if (!soilDecision2.isDefault()) {
                        pillarOnSupportBlock = soilDecision2.isTrue();
                    } else if (testState.isSolidRender()) {
                        pillarOnSupportBlock = true;
                    }
                    break;
                }

                height++;
            }

            if (height < 2 || height <= random.nextInt(pillarOnSupportBlock ? 5 : 4)) {
                growDownwards = true;
            }
        } else if (aboveState.isAir()) {
            growDownwards = true;
        }

        // 向下生长
        if (growDownwards
                && allNeighborsEmpty(level, below, null)
                && level.isEmptyBlock(pos.below(2))) {

            level.setBlock(
                    pos,
                    RhizomeVine.getStateWithConnections(
                            level, pos, this.plant.defaultBlockState()
                    ),
                    2
            );

            this.placeGrownFlower(
                    level,
                    below,
                    nextAge(currentAge, random)
            );

            return;
        }

        // 水平分支
        if (currentAge < 4) {
            int attempts = random.nextInt(4) + (pillarOnSupportBlock ? 1 : 0);
            boolean createdBranch = false;

            for (int i = 0; i < attempts; i++) {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                BlockPos target = pos.relative(direction);

                if (level.isEmptyBlock(target)
                        && level.isEmptyBlock(target.below())
                        && allNeighborsEmpty(level, target, direction.getOpposite())) {
                    level.setBlock(
                            target,
                            RhizomeVine.getStateWithConnections(
                                    level, pos, this.plant.defaultBlockState()
                            ),
                            2
                    );
                    this.placeGrownFlower(
                            level,
                            target.below(),
                            nextAge(currentAge, random)
                    );

                    createdBranch = true;
                }
            }

            if (createdBranch) {
                level.setBlock(
                        pos,
                        RhizomeVine.getStateWithConnections(
                                level, pos, this.plant.defaultBlockState()
                        ),
                        2
                );
            } else {
                this.placeDeadFlower(level, pos);
            }
        } else {
            this.placeDeadFlower(level, pos);
        }
    }

    private int nextAge(int age, RandomSource random) {
        return age < 5 && random.nextFloat() < 0.2F
                ? age + 1
                : age;
    }

    private void placeGrownFlower(Level level, BlockPos pos, int age) {
        level.setBlock(
                pos,
                this.defaultBlockState().setValue(AGE, age),
                2
        );
        level.levelEvent(1033, pos, 0);
    }

    private void placeDeadFlower(Level level, BlockPos pos) {
        level.setBlock(
                pos,
                this.defaultBlockState().setValue(AGE, 5),
                2
        );
        level.levelEvent(1034, pos, 0);
    }

    private static boolean allNeighborsEmpty(LevelReader level, BlockPos pos, @Nullable Direction ignore) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (direction != ignore && !level.isEmptyBlock(pos.relative(direction))) {
                return false;
            }
        }

        return true;
    }
    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess ticks,
            BlockPos pos,
            Direction directionToNeighbour,
            BlockPos neighbourPos,
            BlockState neighbourState,
            RandomSource random
    ) {
        if (!state.canSurvive(level, pos)) {
            ticks.scheduleTick(pos, this, 1);
        }

        return super.updateShape(
                state,
                level,
                ticks,
                pos,
                directionToNeighbour,
                neighbourPos,
                neighbourState,
                random
        );
    }
    @Override
    protected boolean canSurvive(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {
        BlockState aboveState = level.getBlockState(pos.above());
        if (aboveState.isSolidRender()) {
            return true;
        }
        if (aboveState.is(plant)) {
            return true;
        }
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> list = new ArrayList<>();
        int age = state.getValue(AGE);
        if (age < 3) {
            list.add(new ItemStack(
                    InitBlockItem.RhizomeMelonSeed_.asItem(),age
            ));
        }else {
            list.add(new ItemStack(
                    InitBlockItem.MelonSlice_.asItem(),age + params.getLevel().getRandom().nextInt(age)
            ));
        }
        return list;
    }
}
