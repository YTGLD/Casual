package com.ytgld;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModelHandler {
    public static VoxelShape column(double sizeXZ, double minY, double maxY) {
        return column(sizeXZ, sizeXZ, minY, maxY);
    }

    public static VoxelShape column(double sizeX, double sizeZ, double minY, double maxY) {
        double halfX = sizeX / (double)2.0F;
        double halfZ = sizeZ / (double)2.0F;
        return Block.box((double)8.0F - halfX, minY, (double)8.0F - halfZ, (double)8.0F + halfX, maxY, (double)8.0F + halfZ);
    }
}
