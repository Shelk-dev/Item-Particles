package org.shelk.listener;


import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.shelk.ItemParticles;
import org.shelk.ParticleEffect;
import org.shelk.utils.MathParticles;
import org.shelk.utils.XParticle;

public class ParticleMoveEvent implements Listener {
	
	public HashMap<Player, Float> auracount = new HashMap<>();
	

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
				if (ItemParticles.getParticleEffectItem(p) != null && ItemParticles.getParticleEffectItem(p).size() != 0) {
					for (ParticleEffect pe : ItemParticles.getParticleEffectItem(p)) {
							// this player has an item with a particle
							if (pe.getParticle() == null) continue;
							if (pe.getShape() == null) continue;

							   float rgb1 = MathParticles.getRGB(1, pe);
							   float rgb2 = MathParticles.getRGB(2, pe);
							   float rgb3 = MathParticles.getRGB(3, pe);
							   
							
							   	int amounttrail = 3;
							   	if (pe.getParticle() == XParticle.EXPLOSION_LARGE || pe.getParticle() == XParticle.LAVA) amounttrail = 1;
							   	int amounthat = 8;
							   	if (pe.getParticle() == XParticle.EXPLOSION_LARGE || pe.getParticle() == XParticle.LAVA) amounthat = 1;
			   
							   	if (pe.getAmount() != 0) {
							   		amounttrail = pe.getAmount();
							   		amounthat = pe.getAmount();
							   	}
			    	
			    
			    
							   	//trail
							   	if (pe.getShape().equals("trail")) {
							   		for(int i = 0; i < amounttrail; i++) {
							   			MathParticles.spawnParticle(p.getWorld(), pe, p.getLocation().add(0.0,0.5,0.0), rgb1, rgb2, rgb3, p);
							   		}
							   	}
							   	//hat
							   	if (pe.getShape().equals("hat")) {
							   		for(int i = 0; i < amounthat; i++) {
						
							   			MathParticles.spawnParticle(p.getWorld(),pe,p.getLocation().add(0.0,2.4,0.0),rgb1,rgb2,rgb3,p);
							   		}
							   	}
							   	
					}
				
				
		}
							
				
			}
			

}
