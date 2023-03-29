package com.gamerduck.rfr.entity.renderer;

import com.gamerduck.rfr.RFRClient;
import com.gamerduck.rfr.entity.SteveEntity;
import com.gamerduck.rfr.entity.models.SteveEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SteveEntityRenderer extends MobEntityRenderer<SteveEntity, SteveEntityModel> {

    public SteveEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SteveEntityModel(context.getPart(RFRClient.MODEL_STEVE_LAYER), false), 0.5f);
    }

    @Override
    public Identifier getTexture(SteveEntity entity) {
        return new Identifier("rfr", "textures/entity/steve/steve.png");
    }
}