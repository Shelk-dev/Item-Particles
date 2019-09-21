package org.shelk.customsrunnables;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.shelk.ItemParticles;
import org.shelk.ParticleEffect;
import org.shelk.utils.MathParticles;

public class AuraRunnable extends BukkitRunnable {

	private HashMap<Player, Integer> auracount = new HashMap<>();
	
	
	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			if (ItemParticles.getParticleEffectItem(p) != null) {
				for(ParticleEffect pe : ItemParticles.getParticleEffectItem(p)) {
				if (pe.getShape().equals("aura")) {
			
				float rgb1 = MathParticles.getRGB(1, pe);
				float rgb2 = MathParticles.getRGB(2, pe);
				float rgb3 = MathParticles.getRGB(3, pe);
				
					if (auracount.get(p) == null) auracount.put(p, 3);

						MathParticles.playCircle(p, p.getLocation().add(0,((float)auracount.get(p) / 10),0), pe, 1, rgb1, rgb2, rgb3);
						auracount.put(p, auracount.get(p) + 3);
						if (auracount.get(p) == 18) auracount.put(p, 3);
	   		
		    
				}
		}
			}
    }

  }

}
