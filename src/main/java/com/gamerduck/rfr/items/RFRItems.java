package com.gamerduck.rfr.items;

import com.gamerduck.rfr.items.materials.RubyArmorMaterial;
import com.gamerduck.rfr.items.materials.RubyToolMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.gamerduck.rfr.RFRMod.*;
import static com.gamerduck.rfr.entity.RFREntities.HEROBRINE;
import static com.gamerduck.rfr.entity.RFREntities.STEVE;
import static com.gamerduck.rfr.blocks.RFRBlocks.*;

public class RFRItems {

    public static final Item RUBY;
    public static ToolItem RUBY_SHOVEL;
    public static ToolItem RUBY_SWORD;
    public static ToolItem RUBY_PICKAXE;
    public static ToolItem RUBY_AXE;
    public static ToolItem RUBY_HOE;
    public static final Item RUBY_HELMET;
    public static final Item RUBY_CHESTPLATE;
    public static final Item RUBY_LEGGINGS;
    public static final Item RUBY_BOOTS;
    public static final Item HEROBRINE_SPAWN_EGG;
    public static final Item STEVE_SPAWN_EGG;
    public static final BlockItem DIRT_SLAB_ITEM;
    public static final BlockItem RUBY_ORE_ITEM;
    public static final BlockItem DEEPSLATE_RUBY_ORE_ITEM;
    public static final BlockItem RUBY_BLOCK_ITEM;

    public RFRItems() {}

    private static <T> T register(String id, T item) {
        return (T) Registry.register(Registries.ITEM, new Identifier(modid, id), (Item) item);
    }

    static {
        HEROBRINE_SPAWN_EGG = register("herobrine_spawn_egg", new SpawnEggItem(HEROBRINE, 0x04abab, 0x2b3767, new FabricItemSettings()));
        STEVE_SPAWN_EGG = register("steve_spawn_egg", new SpawnEggItem(STEVE, 0xc04abab, 0x2b3767, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.addAfter(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG, HEROBRINE_SPAWN_EGG);
            content.addAfter(HEROBRINE_SPAWN_EGG, STEVE_SPAWN_EGG);
        });


        DIRT_SLAB_ITEM = register("dirt_slab", new BlockItem(DIRT_SLAB, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> content.addAfter(Items.DIRT, DIRT_SLAB));

        RUBY_ORE_ITEM = register("ruby_ore", new BlockItem(RUBY_ORE, new FabricItemSettings()));
        DEEPSLATE_RUBY_ORE_ITEM = register("deepslate_ruby_ore", new BlockItem(DEEPSLATE_RUBY_ORE, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Items.DEEPSLATE_DIAMOND_ORE, RUBY_ORE);
            content.addAfter(RUBY_ORE_ITEM, DEEPSLATE_RUBY_ORE);
        });

        RUBY_BLOCK_ITEM = register("ruby_block", new BlockItem(RUBY_BLOCK, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> content.addAfter(Items.DIAMOND_BLOCK, RUBY_BLOCK));

        RUBY = register("ruby", new Item(new FabricItemSettings()));

        RUBY_SWORD = register("ruby_sword", (new SwordItem(RubyToolMaterial.INSTANCE, 3, -2.4F, new Item.Settings())));
        RUBY_SHOVEL = register("ruby_shovel", (new ShovelItem(RubyToolMaterial.INSTANCE, 1.5F, -3.0F, new Item.Settings())));
        RUBY_PICKAXE = register("ruby_pickaxe", new PickaxeItem(RubyToolMaterial.INSTANCE, 1, -2.8F, new Item.Settings()));
        RUBY_AXE = register("ruby_axe", (new AxeItem(RubyToolMaterial.INSTANCE, 5.0F, -3.0F, new Item.Settings())));
        RUBY_HOE = register("ruby_hoe", (new HoeItem(RubyToolMaterial.INSTANCE, -3, 0.0F, new Item.Settings())));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.addAfter(Items.DIAMOND_HOE, RUBY_SHOVEL);
            content.addAfter(RUBY_SHOVEL, RUBY_PICKAXE);
            content.addAfter(RUBY_PICKAXE, RUBY_AXE);
            content.addAfter(RUBY_AXE, RUBY_HOE);
        });

        RUBY_HELMET = register("ruby_helmet", new ArmorItem(RubyArmorMaterial.INSTANCE, ArmorItem.Type.HELMET, new Item.Settings()));
        RUBY_CHESTPLATE = register("ruby_chestplate", new ArmorItem(RubyArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
        RUBY_LEGGINGS = register("ruby_leggings", new ArmorItem(RubyArmorMaterial.INSTANCE, ArmorItem.Type.LEGGINGS, new Item.Settings()));
        RUBY_BOOTS = register("ruby_boots", new ArmorItem(RubyArmorMaterial.INSTANCE, ArmorItem.Type.BOOTS, new Item.Settings()));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.addAfter(Items.DIAMOND_SWORD, RUBY_SWORD);
            content.addAfter(Items.DIAMOND_AXE, RUBY_AXE);
            content.addAfter(Items.DIAMOND_BOOTS, RUBY_HELMET);
            content.addAfter(RUBY_HELMET, RUBY_CHESTPLATE);
            content.addAfter(RUBY_CHESTPLATE, RUBY_LEGGINGS);
            content.addAfter(RUBY_LEGGINGS, RUBY_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.addAfter(Items.DIAMOND, RUBY));
    }
}
