package com.vetpetmon.hammertime;

import com.mojang.logging.LogUtils;
import com.vetpetmon.hammertime.core.HammerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Hammertime.MODID)
public class Hammertime {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "hammertime";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Items which will all be registered under the "hammertime" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Create base-game hammers
    public static final RegistryObject<Item>
        DIAMOND_HAMMER =    ITEMS.register("diamond_hammer",    () -> newHammer(Tiers.DIAMOND)),
        NETHERITE_HAMMER =  ITEMS.register("netherite_hammer",  () -> new HammerItem(Tiers.NETHERITE, 8,-2.9f, new Item.Properties().stacksTo(1).fireResistant(), 0)),
        STONE_HAMMER =      ITEMS.register("stone_hammer",      () -> newHammer(Tiers.STONE)),
        IRON_HAMMER =       ITEMS.register("iron_hammer",       () -> newHammer(Tiers.IRON)),
        GOLD_HAMMER =       ITEMS.register("gold_hammer",       () -> newHammer(Tiers.GOLD));

    public static HammerItem newHammer(Tier tier) {
        return new HammerItem(tier, 8,-2.9f, new Item.Properties().stacksTo(1), 0);
    }

    public Hammertime(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
//        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(STONE_HAMMER);
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(IRON_HAMMER);
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(GOLD_HAMMER);
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(DIAMOND_HAMMER);
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(NETHERITE_HAMMER);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
