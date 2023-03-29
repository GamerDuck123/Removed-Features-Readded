package com.gamerduck.rfr;

import com.gamerduck.rfr.entity.RFREntities;
import com.gamerduck.rfr.entity.SteveEntity;
import com.gamerduck.rfr.entity.models.HerobrineEntityModel;
import com.gamerduck.rfr.entity.models.SteveEntityModel;
import com.gamerduck.rfr.entity.renderer.HerobrineEntityRenderer;
import com.gamerduck.rfr.entity.renderer.SteveEntityRenderer;
import com.gamerduck.rfr.items.RFRItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class RFRClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_STEVE_LAYER = new EntityModelLayer(new Identifier("rfr", "steve"), "main");
    public static final EntityModelLayer MODEL_HEROBRINE_LAYER = new EntityModelLayer(new Identifier("rfr", "herobrine"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(RFREntities.STEVE, (context) -> new SteveEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(MODEL_STEVE_LAYER, () ->
                SteveEntityModel.getTexturedModelData(Dilation.NONE, false));

        EntityRendererRegistry.register(RFREntities.HEROBRINE, (context) -> new HerobrineEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(MODEL_HEROBRINE_LAYER,  () ->
                SteveEntityModel.getTexturedModelData(Dilation.NONE, false));
    }
}
