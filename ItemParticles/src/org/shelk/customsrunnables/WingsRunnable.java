package org.shelk.customsrunnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.shelk.ItemParticles;
import org.shelk.ParticleEffect;
import org.shelk.utils.MathParticles;

public class WingsRunnable extends BukkitRunnable {
    
	static boolean x = true;
	static boolean o = false;
	
	private static boolean[][] shape1 = {
			// 24 ->
			// 18 |
			//    v
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, x, o, o, o, o, o, o, o, o, x, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, x, x, o, o, o, o, o, o, o, o, x, x, o, o, o, o, o, o},
            {o, o, o, o, o, x, x, x, x, o, o, o, o, o, o, x, x, x, x, o, o, o, o, o},
            {o, o, o, o, o, x, x, x, x, o, o, o, o, o, o, x, x, x, x, o, o, o, o, o},
            {o, o, o, o, o, o, x, x, x, x, o, o, o, o, x, x, x, x, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, x, x, x, x, o, o, x, x, x, x, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, x, x, x, x, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, x, x, o, o, x, x, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, x, x, x, o, o, x, x, x, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, x, x, o, o, o, o, x, x, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, x, o, o, o, o, o, o, x, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
    	};
	private static boolean[][] shape2 = {
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, x, x, x, x, x, x, o, o, o, o, o, o, x, x, x, x, x, x, o, o, o},
            {o, o, o, o, o, x, x, x, x, x, o, o, o, o, x, x, x, x, x, o, o, o, o, o},
            {o, o, o, o, o, o, o, x, x, x, x, o, o, x, x, x, x, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, x, x, x, x, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, x, x, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
	};
	private static boolean[][] shape3 = {
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
			{o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, x, x, x, o, o, o, o, x, x, x, o, o, o, o, o, o, o},
            {o, o, o, o, o, x, x, x, x, x, o, o, o, o, x, x, x, x, x, o, o, o, o, o},
            {o, o, o, o, o, x, x, x, x, x, x, o, o, x, x, x, x, x, x, o, o, o, o, o},
            {o, o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o},
            {o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o},
            {o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o},
            {o, o, o, o, x, x, x, x, o, o, o, o, o, o, o, o, x, x, x, x, x, o, o, o},
            {o, o, o, x, x, x, x, x, o, o, o, o, o, o, o, o, x, x, x, x, x, o, o, o},
            {o, o, o, x, x, x, x, o, o, o, o, o, o, o, o, o, o, x, x, x, x, o, o, o},
            {o, o, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, o, o},
            {o, o, o, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, o, o, o},
    	};
	
	

		public WingsRunnable() {
			
		}
		
		@Override
		public void run() {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (ItemParticles.getParticleEffectItem(p) != null && ItemParticles.getParticleEffectItem(p).size() != 0) {
					    for(ParticleEffect pe : ItemParticles.getParticleEffectItem(p)) {

					    	// RGB SET 
					    	if (pe.getShape().equals("wings1")) {
					    		drawParticles(p.getLocation(), pe, p, shape1);
					    	}
					    	if (pe.getShape().equals("wings2")) {
					    		drawParticles(p.getLocation(), pe, p, shape2);
					    	}
					    	if (pe.getShape().equals("wings3")) {
					    		drawParticles(p.getLocation(), pe, p, shape3);
					    	}

					    
					    	
					    
				}
			}
		}

		}
		
		 private void drawParticles(Location location, ParticleEffect pe, Player p, boolean[][] shape){
			 
			    float rgb1 = MathParticles.getRGB(1, pe);
			    float rgb2 = MathParticles.getRGB(2, pe);
			    float rgb3 = MathParticles.getRGB(3, pe);
			 
		        double space = 0.20;
		        // - 1.5
		        double defX = location.getX() - 2.2;
		        double x = defX;
		        // + 2.8
		        double y = location.clone().getY() + 3.2;
		        // + 0.5
		        double z = location.clone().getZ();
		        double fire = -((location.getYaw() + 180) / 60);
		        
		        fire += (location.getYaw() < -180 ? 3.25 : 2.985);

		        for (int i = 0; i < shape.length; i++) {
		            for (int j = 0; j < shape[i].length; j++) {
		                if (shape[i][j]) {

		                    Location target = location.clone();
		                    target.setX(x);
		                    target.setY(y);
		                    target.setZ(z);

		                    Vector v = target.toVector().subtract(location.toVector());
		                    Vector v2 = getBackVector(location);
		                    v = rotateAroundAxisY(v, fire);
		                    v2.setY(0).multiply(-0.5D);

		                    location.add(v);
		                    location.add(v2);
		                    for (int k = 0; k < 3; k++)
		                        MathParticles.spawnParticle(location.getWorld(), pe, location, rgb1, rgb2, rgb3, p);
		                    location.subtract(v2);
		                    location.subtract(v);
		                }
		                x += space;
		            }
		            y -= space;
		            z += 0.01;
		            x = defX;
		            
		        }
		    }
		 
		    public static Vector rotateAroundAxisY(Vector v, double fire) {
		        double x, z, cos, sin;
		        cos = Math.cos(fire);
		        sin = Math.sin(fire);
		        x = v.getX() * cos + v.getZ() * sin;
		        z = v.getX() * -sin + v.getZ() * cos;
		        return v.setX(x).setZ(z);
		    }

		    public static Vector getBackVector(Location loc) {
		        final float newZ = (float) (loc.getZ() + (1 * Math.sin(Math.toRadians(loc.getYaw() + 90 * 1))));
		        final float newX = (float) (loc.getX() + (1 * Math.cos(Math.toRadians(loc.getYaw() + 90 * 1))));
		        return new Vector(newX - loc.getX(), 0, newZ - loc.getZ());
		    }

		             
		      
 }


