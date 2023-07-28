package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventAttackEntity;
import link.liycxc.api.events.impl.EventUpdate;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;
import net.minecraft.block.Block;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;

public class BloodParticlesMod extends Mod {

	private EntityLivingBase target;
	
	public BloodParticlesMod() {
		super("Blood Particles", "Show blood particles when attack entity", ModCategory.RENDER);
	}
	
	@Override
	public void setup() {
		this.addBooleanSetting("Blood", this, true);
		this.addBooleanSetting("Sound", this, true);
		this.addSliderSetting("Multiplier", this, 2, 1, 10, true);
	}

	@EventTarget
	public void onUpdate(EventUpdate event) {
		if(mc.objectMouseOver != null & mc.objectMouseOver.entityHit != null) {
			if(mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
				target = (EntityLivingBase) mc.objectMouseOver.entityHit;
			}
		}
	}
	
	@EventTarget
	public void onAttack(EventAttackEntity event) {
		
    	if(NekoCat.instance.settingsManager.getSettingByName(this, "Blood").getValBoolean()) {
      		 for (int i = 0; i < NekoCat.instance.settingsManager.getSettingByName(this, "Multiplier").getValDouble(); i++) {
                 mc.theWorld.spawnParticle(EnumParticleTypes.BLOCK_CRACK, target.posX, target.posY + target.height - 0.75, target.posZ, 0, 0, 0, Block.getStateId(Blocks.redstone_block.getDefaultState()));
    		 }
    	}
    	
  		 if(NekoCat.instance.settingsManager.getSettingByName(this, "Sound").getValBoolean()) {
   			 mc.getSoundHandler().playSound(new PositionedSoundRecord(new ResourceLocation("dig.stone"), 4.0F, 1.2F, ((float) target.posX), ((float) target.posY), ((float) target.posZ)));
   		 }
	}
}
