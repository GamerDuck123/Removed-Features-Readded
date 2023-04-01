package com.gamerduck.rfr;

import com.gamerduck.rfr.entity.HerobrineEntity;
import com.gamerduck.rfr.entity.RFREntities;
import com.gamerduck.rfr.entity.SteveEntity;
import com.gamerduck.rfr.entity.models.HumanEntityModel;
import com.gamerduck.rfr.entity.renderer.HumanEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class RFRClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_STEVE_LAYER = new EntityModelLayer(new Identifier(RFRMod.modid, "steve"), "main");
    public static final EntityModelLayer MODEL_HEROBRINE_LAYER = new EntityModelLayer(new Identifier(RFRMod.modid, "herobrine"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(RFREntities.STEVE, (context) ->
                new HumanEntityRenderer<SteveEntity>(context, MODEL_STEVE_LAYER, "textures/entity/steve/steve.png"));
        EntityModelLayerRegistry.registerModelLayer(MODEL_STEVE_LAYER, () ->
                HumanEntityModel.getTexturedModelData(Dilation.NONE));

        EntityRendererRegistry.register(RFREntities.HEROBRINE, (context) ->
                new HumanEntityRenderer<HerobrineEntity>(context, MODEL_HEROBRINE_LAYER, "textures/entity/herobrine/herobrine.png"));
        EntityModelLayerRegistry.registerModelLayer(MODEL_HEROBRINE_LAYER,  () ->
                HumanEntityModel.getTexturedModelData(Dilation.NONE));
    }
}
