package com.ytgld.spontaneous_creation;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(value = SpontaneousCreation.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = SpontaneousCreation.MODID, value = Dist.CLIENT)
public class SpontaneousCreationClient {
    public SpontaneousCreationClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event) {
        event.createProvider(TagsProvider::new);
        event.createProvider(SCBlockTagsProvider::new);
    }

}
