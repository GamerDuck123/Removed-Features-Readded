package com.gamerduck.rfr.entity.renderer;

import com.gamerduck.rfr.RFRMod;
import com.gamerduck.rfr.entity.models.HumanEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

public class HumanEntityRenderer<T extends MobEntity> extends MobEntityRenderer<T, HumanEntityModel<T>> {

    private final String texturePath;

    public HumanEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer layer, String texturePath) {
        super(context, new HumanEntityModel(context.getPart(layer)), 0.5f);
        this.texturePath = texturePath;
    }

    @Override
    public Identifier getTexture(T entity) {
        return new Identifier(RFRMod.modid, texturePath);
    }
}