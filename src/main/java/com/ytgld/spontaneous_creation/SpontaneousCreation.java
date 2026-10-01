package com.ytgld.spontaneous_creation;

import com.mojang.logging.LogUtils;
import com.ytgld.spontaneous_creation.block.InitBlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import org.slf4j.Logger;

@Mod(SpontaneousCreation.MODID)
public class SpontaneousCreation {
    public static final String MODID = "spontaneous_creation";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SpontaneousCreation(IEventBus modEventBus, ModContainer modContainer) {
        InitBlockItem.BLOCKS.register(modEventBus);
        InitBlockItem.BLOCK_ITEMS.register(modEventBus);
        Tab.CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(this::event);
    }
    public void event(LootTableLoadEvent event) {
        LootTable table = event.getTable();
        if (event.getName().toString().contains("gameplay/sniffer_digging")) {
            table.addPool(LootPool.lootPool().name(MODID + "sniffer")
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(InitBlockItem.RhizomeMelonSeed_)
                            .when(LootItemRandomChanceCondition.randomChance(0.1f)))
                    .build());
        }
    }

    public static ResourceLocation fromNamespaceAndPath(String path){
        return ResourceLocation.fromNamespaceAndPath(MODID,path);
    }
}




