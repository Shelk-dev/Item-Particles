package org.shelk.customsrunnables;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.shelk.ItemParticles;
import org.shelk.ParticleEffect;
import org.shelk.utils.MathParticles;

public class PulseRunnable extends BukkitRunnable {

	private HashMap<Player, Double> pulsecount = new HashMap<>();
	private ArrayList<Player> mode = new ArrayList<>();
	
	
	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			if (ItemParticles.getParticleEffectItem(p) != null) {
				for(ParticleEffect pe : ItemParticles.getParticleEffectItem(p)) {
				if (pe.getShape().equals("pulse")) {
			
				float rgb1 = MathParticles.getRGB(1, pe);
				float rgb2 = MathParticles.getRGB(2, pe);
				float rgb3 = MathParticles.getRGB(3, pe);
				if (pulsecount.get(p) == null) {
					pulsecount.put(p, 5d);
				}
				for(int i = 5; i < pulsecount.get(p); i+=5) {
					MathParticles.playCircle(p, p.getLocation(), pe, (double)i / 10, rgb1, rgb2, rgb3);
				}
				if (pulsecount.get(p) == 25) mode.add(p);
				if (pulsecount.get(p) == 0) mode.remove(p);
				
				if (mode.contains(p)) pulsecount.put(p, pulsecount.get(p) - 5);
				if (!mode.contains(p)) pulsecount.put(p, pulsecount.get(p) + 5);
				
				}
		}
			}
    }

	}

}
