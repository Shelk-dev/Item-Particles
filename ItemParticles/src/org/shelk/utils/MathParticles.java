package org.shelk.utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.shelk.ParticleEffect;

public class MathParticles {
	
	public static void playCircle(Player p, Location loc, ParticleEffect pe, double radius, float rgb1, float rgb2, float rgb3) {
        for(int i = 0;i < 50; i++){
            double angle = 2 * Math.PI * i / 50;
            Location point = loc.clone().add(radius * Math.sin(angle), 0.0d, radius * Math.cos(angle));
            spawnParticle(p.getWorld(), pe, point, rgb1, rgb2, rgb3,p);
        }
        
	}
	
	public static float getRGB(int whichrgb, ParticleEffect pe) {
		if (pe.getColor() == null) return 0;
		int color = 0;
		if (whichrgb == 1) color = pe.getColor().getRed();
		if (whichrgb == 2) color = pe.getColor().getGreen();
		if (whichrgb == 3) color = pe.getColor().getBlue();
		float rgb;
		rgb = (float) ((float) ((float)color / 1000) * 3.92);
		if (rgb == 0 && whichrgb == 1) rgb+= 0.001;
		return rgb;
		
	}

	public static void spawnParticle(World world, ParticleEffect pe, Location point, float rgb1, float rgb2, float rgb3, Player p) {
		if (Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.13")) {
			Color color = Color.RED;
			if (pe.getColor() != null) color = pe.getColor();
			Particle.DustOptions dustOptions = null;
			if (pe.getParticle() == XParticle.REDSTONE) dustOptions = new Particle.DustOptions(color, 1);
			
			world.spawnParticle(Particle.valueOf((String) pe.getParticle().name()), point.getX(), point.getY(), point.getZ() , 0, 0, 0, 0, 1, dustOptions);
		} else {
			if (!Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.7")) {
				world.spawnParticle(Particle.valueOf((String) pe.getParticle().name()), point.getX(), point.getY(), point.getZ() , 0, rgb1, rgb2, rgb3, 1);
			} else {
				
				if (pe.getParticle() == XParticle.REDSTONE) {
					org.inventivetalent.particle.ParticleEffect.valueOf(pe.getParticle().name()).sendColor(Bukkit.getOnlinePlayers(), point, pe.getColor());
					return;
				}
				org.inventivetalent.particle.ParticleEffect.valueOf(pe.getParticle().name()).send(Bukkit.getOnlinePlayers(), point.getX(), point.getY(), point.getZ(), 0, 0, 0, 0.01, 1);
				
				
			}
		}
	}
	
	
}
