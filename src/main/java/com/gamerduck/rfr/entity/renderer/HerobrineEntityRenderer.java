package com.gamerduck.rfr.entity.renderer;

import com.gamerduck.rfr.RFRClient;
import com.gamerduck.rfr.entity.HerobrineEntity;
import com.gamerduck.rfr.entity.SteveEntity;
import com.gamerduck.rfr.entity.models.HerobrineEntityModel;
import com.gamerduck.rfr.entity.models.SteveEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class HerobrineEntityRenderer extends MobEntityRenderer<HerobrineEntity, HerobrineEntityModel> {

    public HerobrineEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new HerobrineEntityModel(context.getPart(RFRClient.MODEL_HEROBRINE_LAYER), false), 0.5f);
    }

    @Override
    public Identifier getTexture(HerobrineEntity entity) {
        return new Identifier("rfr", "textures/entity/herobrine/herobrine.png");
    }
}