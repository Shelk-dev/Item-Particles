package org.shelk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.shelk.bStats.MetricsLite;
import org.shelk.commandhandler.ItemParticlesHandler;
import org.shelk.customsrunnables.AuraRunnable;
import org.shelk.customsrunnables.OtherParticlesRunnable;
import org.shelk.listener.InventoryClickListener;
import org.shelk.listener.ParticleMoveEvent;


public class ItemParticles extends JavaPlugin {
	
	public static ArrayList<ParticleEffect> listparticleeffect = new ArrayList<>();
	private final String fileName = "savedata.yml"; 
	private File file = new File(this.getDataFolder(), fileName);
	
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public void onEnable() {
		// bStats
		MetricsLite metrics;
		if (!Bukkit.getVersion().contains("1.7")) metrics = new MetricsLite(this);
		 
		// Serialization & Deserialization
		ConfigurationSerialization.registerClass(ParticleEffect.class, "ItemParticle");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		ArrayList<ParticleEffect> deserialization = (ArrayList<ParticleEffect>) conf.get("arraylist");
		if (deserialization != null && deserialization.size() > 0) {listparticleeffect = deserialization;}
		else {
			listparticleeffect = new ArrayList<ParticleEffect>();
		}
		
		// Initializing commands 
		getCommand("itemparticles").setExecutor(new ItemParticlesHandler());
		getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
		// Initializing the particle move event
		getServer().getPluginManager().registerEvents(new ParticleMoveEvent(), this);
		
		// Initializing runnables 
		OtherParticlesRunnable opr = new OtherParticlesRunnable();
		opr.runTaskTimer(this, 0L, 20L);
		AuraRunnable ar = new AuraRunnable();
		ar.runTaskTimer(this, 0L, 4L);
		
				}
			
	@Override
	public void onDisable() {
		//Serialization
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		conf.set("arraylist", listparticleeffect);
		try {
			conf.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		
	}

}
	@SuppressWarnings("deprecation")
	public static ItemStack getHands(int choice, ParticleEffect pe, Player p) {
		if (Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.7")) return p.getItemInHand();
		ItemStack mainhand = p.getInventory().getItemInMainHand();
		ItemStack secondhand = null;
		
		if (pe.getHands() != null && pe.getHands().equals("lefthand")) mainhand = p.getInventory().getItemInOffHand();
		if (pe.getHands() != null && pe.getHands().equals("both")) {
			secondhand = p.getInventory().getItemInOffHand();
		} else {
			secondhand = mainhand.clone();
		}
		
		if (choice == 1) return mainhand;
		if (choice == 2) return secondhand;
		return null; 
	}
	
	public static ArrayList<ParticleEffect> getParticleEffectItem(Player p) {
		
		if (p.hasPermission("itemparticles.display")) {
		ArrayList<ParticleEffect> al = new ArrayList<>();
		if (listparticleeffect != null && listparticleeffect.size() > 0) {
			for(ParticleEffect pe : ItemParticles.listparticleeffect) {
				if (pe != null) {
					
			ItemStack gethand = getHands(1, pe, p);
			ItemStack otherhand = getHands(2, pe, p);

			if (pe.getItem() == null || (gethand == null && otherhand == null)) continue;
			if (pe.getItem() == null || gethand.getType() == pe.getItem() || otherhand.getType() == pe.getItem()) {
			if (pe.getName() == null || gethand.hasItemMeta() && gethand.getItemMeta().getDisplayName().equals(pe.getName()) || otherhand.hasItemMeta() && otherhand.getItemMeta().getDisplayName().equals(pe.getName())) {
			if (pe.getLore() == null || gethand.hasItemMeta() && gethand.getItemMeta().getLore().equals(pe.getLore()) || otherhand.hasItemMeta() && otherhand.getItemMeta().getLore().equals(pe.getLore())) {
			// THIS PLAYER HAS AN ITEM WITH A ITEM PARTICLE
		    if (pe.getParticle() == null) continue;
		    if (pe.getShape() == null) continue;
		    al.add(pe);
			}
			}
		}
			
			}
			}
		}
		if (al != null && al.size() > 0) return al;
		}
		return null;
	}
	
	
}
