package com.ytgld.spontaneous_creation;

import com.mojang.logging.LogUtils;
import com.ytgld.spontaneous_creation.block.InitBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(SpontaneousCreation.MODID)
public class SpontaneousCreation {
    public static final String MODID = "spontaneous_creation";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SpontaneousCreation(IEventBus modEventBus, ModContainer modContainer) {
        InitBlockItem.BLOCKS.register(modEventBus);
        InitBlockItem.BLOCK_ITEMS.register(modEventBus);
        Tab.CREATIVE_MODE_TABS.register(modEventBus);
    }
}




