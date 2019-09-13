package org.shelk.listener;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.shelk.ItemParticles;
import org.shelk.ParticleEffect;
import org.shelk.commandhandler.ItemParticlesHandler;
import org.shelk.utils.XMaterial;
import org.shelk.utils.XParticle;
public class InventoryClickListener implements Listener {

	private HashMap<Player, ParticleEffect> currentparticleeffectgui = new HashMap<>();
	
	@EventHandler 
	public void onInteract(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if (e.getView().getTitle().equals(ChatColor.DARK_BLUE + "ItemParticles")) {
			if (e.getCurrentItem() == null) return;
			

			if (e.getCurrentItem().getType() == XMaterial.BLAZE_ROD.parseMaterial()) {
				
				p.closeInventory();
				p.sendMessage("§aDo the command /itemparticles [id]");
				e.setCancelled(true);
				return;
				
			}

			if (e.getCurrentItem().getType() == XMaterial.MAGMA_CREAM.parseMaterial()) {
				e.setCancelled(true);
				if (currentparticleeffectgui.get(p) != null) {
					currentparticleeffectgui.remove(p);
				}
				
				ParticleEffect getparticle = ItemParticles.listparticleeffect.get(Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(3)));
				currentparticleeffectgui.put(p, getparticle);
				
				p.closeInventory();
				
				Inventory gui = Bukkit.createInventory(null, 9, "§dModify your item particle");
				gui.setItem(0, ItemParticlesHandler.getItemStack("§cLeave", Arrays.asList("§5Left click to come back to the menu"), XMaterial.PAPER.parseMaterial()));
				gui.setItem(1, ItemParticlesHandler.getItemStack("§cShape", null, XMaterial.BLAZE_POWDER.parseMaterial()));
				gui.setItem(2, ItemParticlesHandler.getItemStack("§dParticle", null, XMaterial.EMERALD.parseMaterial()));
				gui.setItem(3, ItemParticlesHandler.getItemStack("§eAmount of particles", null, XMaterial.ARROW.parseMaterial()));
				gui.setItem(4, ItemParticlesHandler.getItemStack("§aOn which hands does it work ?", null, XMaterial.GOLDEN_AXE.parseMaterial()));
				gui.setItem(5, ItemParticlesHandler.getItemStack("§bPermission", null, XMaterial.NAME_TAG.parseMaterial()));
				gui.setItem(8, ItemParticlesHandler.getItemStack("§4Delete the item particle", null, XMaterial.REDSTONE_BLOCK.parseMaterial()));
				p.openInventory(gui);
				
				
			}
			
			if (e.getCurrentItem().getType() == XMaterial.PAPER.parseMaterial()) {
				e.setCancelled(true);
				int page = Integer.parseInt(e.getCurrentItem().getItemMeta().getDisplayName().replace("§c>Page ", ""));
				p.closeInventory();
				ItemParticlesHandler.openPrincipalGui(p,page);
				
			}
			
			
			
			
		}
		if (e.getView().getTitle().equals("§dModify your item particle")) {
			if (e.getCurrentItem() == null) return;
				if (e.getCurrentItem().getType() == XMaterial.PAPER.parseMaterial()) {
						p.closeInventory();
						currentparticleeffectgui.remove(p);
						ItemParticlesHandler.openPrincipalGui(p, 1);
						e.setCancelled(true);
			}
				if (e.getCurrentItem().getType() == XMaterial.REDSTONE_BLOCK.parseMaterial()) {
					e.setCancelled(true);
					p.sendMessage("§cItem particle with id §b" + currentparticleeffectgui.get(p).getId() + " §chas been deleted.");
					ItemParticles.listparticleeffect.remove(currentparticleeffectgui.get(p));
					currentparticleeffectgui.remove(p);
					p.closeInventory();
					ItemParticlesHandler.openPrincipalGui(p, 1);
					
					
					
				}
				if (e.getCurrentItem().getType() == XMaterial.ARROW.parseMaterial()){
					p.sendMessage("§aUse the command /itemparticles modifyamount " + currentparticleeffectgui.get(p).getId() + " <amount of particles>");
					p.closeInventory();
				}
				
				if (e.getCurrentItem().getType() == XMaterial.NAME_TAG.parseMaterial()){
					p.sendMessage("§aUse the command /itemparticles modifypermission " + currentparticleeffectgui.get(p).getId() + " <permission>");
					p.closeInventory();
				}
				
				if (e.getCurrentItem().getType() == XMaterial.GOLDEN_AXE.parseMaterial()) {
					if (Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.7")) {
						p.sendMessage("§cThis option is not available in 1.7/1.8.");
						p.closeInventory();
						return;
					}
					e.setCancelled(true);
					p.closeInventory();
					Inventory newinv = Bukkit.createInventory(null, 9, "§eHands");
					newinv.setItem(0, ItemParticlesHandler.getItemStack("§eOnly left hand", null, XMaterial.IRON_SWORD.parseMaterial()));
					newinv.setItem(1, ItemParticlesHandler.getItemStack("§eOnly right hand", null, XMaterial.GOLDEN_SWORD.parseMaterial()));
					newinv.setItem(2, ItemParticlesHandler.getItemStack("§dBoth", null, XMaterial.DIAMOND_SWORD.parseMaterial()));
					p.openInventory(newinv);
				}
				
				 if (e.getCurrentItem().getType() == XMaterial.BLAZE_POWDER.parseMaterial()) {
					e.setCancelled(true);
					p.closeInventory();
					Inventory newinv = Bukkit.createInventory(null, 9, "§cShape");
					newinv.setItem(0, ItemParticlesHandler.getItemStack("§cCircle", null, XMaterial.ENDER_PEARL.parseMaterial()));
					newinv.setItem(1, ItemParticlesHandler.getItemStack("§eTrail", null, XMaterial.POWERED_RAIL.parseMaterial()));
					newinv.setItem(2, ItemParticlesHandler.getItemStack("§dHat", null, XMaterial.DIAMOND_HELMET.parseMaterial()));
					newinv.setItem(3, ItemParticlesHandler.getItemStack("§6Sphere", null, XMaterial.FIREWORK_STAR.parseMaterial()));
					newinv.setItem(4, ItemParticlesHandler.getItemStack("§7Disk", null, XMaterial.COMPASS.parseMaterial()));
					newinv.setItem(5, ItemParticlesHandler.getItemStack("§aAura", null, XMaterial.LEAD.parseMaterial()));
					newinv.setItem(6, ItemParticlesHandler.getItemStack("§bStar", null, XMaterial.NETHER_STAR.parseMaterial()));
					newinv.setItem(7, ItemParticlesHandler.getItemStack("§2Wings", null, XMaterial.FEATHER.parseMaterial()));
					
					p.openInventory(newinv);
					
				}
				if (e.getCurrentItem().getType() == XMaterial.EMERALD.parseMaterial()) {
					e.setCancelled(true);
					p.closeInventory();
					
					Inventory newinv = Bukkit.createInventory(null, 27, "§cParticle");
					newinv.setItem(0, ItemParticlesHandler.getItemStack("§cFlame", null, XMaterial.TORCH.parseMaterial()));
					newinv.setItem(1, ItemParticlesHandler.getItemStack("§aHappy villager", null, XMaterial.EMERALD_BLOCK.parseMaterial()));
					newinv.setItem(2, ItemParticlesHandler.getItemStack("Cloud", null, XMaterial.QUARTZ_BLOCK.parseMaterial()));
					newinv.setItem(3, ItemParticlesHandler.getItemStack("§6Note", null, XMaterial.JUKEBOX.parseMaterial()));
					newinv.setItem(4, ItemParticlesHandler.getItemStack("§4Redstone", null, XMaterial.REDSTONE.parseMaterial()));
					newinv.setItem(5, ItemParticlesHandler.getItemStack("Snow", null, XMaterial.SNOWBALL.parseMaterial()));
					newinv.setItem(6, ItemParticlesHandler.getItemStack("§cLava", null, XMaterial.LAVA_BUCKET.parseMaterial()));
					newinv.setItem(7, ItemParticlesHandler.getItemStack("§bWater", null, XMaterial.WATER_BUCKET.parseMaterial()));
					newinv.setItem(8, ItemParticlesHandler.getItemStack("§cLava explosion", null, XMaterial.COAL_BLOCK.parseMaterial()));
					newinv.setItem(9, ItemParticlesHandler.getItemStack("§eExplosion", null, XMaterial.TNT.parseMaterial()));
					newinv.setItem(10, ItemParticlesHandler.getItemStack("§cHeart", null, XMaterial.APPLE.parseMaterial()));
					newinv.setItem(11, ItemParticlesHandler.getItemStack("Fireworks spark", null, XMaterial.FIREWORK_ROCKET.parseMaterial()));
					newinv.setItem(12, ItemParticlesHandler.getItemStack("§8Portal", null, XMaterial.OBSIDIAN.parseMaterial()));
					newinv.setItem(13, ItemParticlesHandler.getItemStack("§5Enhancement", null, XMaterial.ENCHANTING_TABLE.parseMaterial()));
					newinv.setItem(14, ItemParticlesHandler.getItemStack("§6Critical", null, XMaterial.DIAMOND_SWORD.parseMaterial()));
					newinv.setItem(15, ItemParticlesHandler.getItemStack("Bubbles", null, XMaterial.GLASS_BOTTLE.parseMaterial()));
					newinv.setItem(16, ItemParticlesHandler.getItemStack("§5Magic", null, XMaterial.BOOK.parseMaterial()));
					newinv.setItem(17, ItemParticlesHandler.getItemStack("§aSlime", null, XMaterial.SLIME_BALL.parseMaterial()));
					newinv.setItem(18, ItemParticlesHandler.getItemStack("§8Angry villager", null, XMaterial.CARVED_PUMPKIN.parseMaterial()));
					newinv.setItem(19, ItemParticlesHandler.getItemStack("§9Smoke", null, XMaterial.CLAY.parseMaterial()));
					newinv.setItem(20, ItemParticlesHandler.getItemStack("§bWater bubbles", null, XMaterial.GLASS_BOTTLE.parseMaterial()));
					p.openInventory(newinv);
				}
				
		}
		if (e.getView().getTitle().equals("§eHands")) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()){
				ParticleEffect getparticle = currentparticleeffectgui.get(p);
				p.closeInventory();
				e.setCancelled(true);
				ItemParticlesHandler.openPrincipalGui(p,1);
				String choix = null;
				if (e.getCurrentItem().getType() == XMaterial.IRON_SWORD.parseMaterial()) {choix = "lefthand";getparticle.setHands(choix);}
				if (e.getCurrentItem().getType() == XMaterial.GOLDEN_SWORD.parseMaterial()) {choix = "righthand";getparticle.setHands(choix);}
				if (e.getCurrentItem().getType() == XMaterial.DIAMOND_SWORD.parseMaterial()) {choix= "both";getparticle.setHands(choix);}
				p.sendMessage("§aThe hands on which the effect works were put on : §b" + choix);
				
			}
		}
		if (e.getView().getTitle().equals("§cShape")) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()) {
				e.setCancelled(true);
				ParticleEffect getparticle = currentparticleeffectgui.get(p);
				p.closeInventory();
				ItemParticlesHandler.openPrincipalGui(p,1);
				String choix = null;
				if (e.getCurrentItem().getType() == XMaterial.ENDER_PEARL.parseMaterial()) {choix = "circle";getparticle.setShape(choix);}
				if (e.getCurrentItem().getType() == XMaterial.POWERED_RAIL.parseMaterial()) {choix = "trail";getparticle.setShape(choix);}
				if (e.getCurrentItem().getType() == XMaterial.DIAMOND_HELMET.parseMaterial()) {choix= "hat";getparticle.setShape(choix);}
				if (e.getCurrentItem().getType() == XMaterial.FIREWORK_STAR.parseMaterial()) {choix="sphere";getparticle.setShape(choix);}
				if (e.getCurrentItem().getType() == XMaterial.COMPASS.parseMaterial()) {choix="disk";getparticle.setShape(choix);}
				if (e.getCurrentItem().getType() == XMaterial.LEAD.parseMaterial()) {choix="aura";getparticle.setShape(choix);}
				if (e.getCurrentItem().getType() == XMaterial.FEATHER.parseMaterial()) {
					p.closeInventory();
					e.setCancelled(true);
					Inventory wingschoose = Bukkit.createInventory(null, 9, "§cChoose wings");
					wingschoose.setItem(0, ItemParticlesHandler.getItemStack("§cType 1", null, XMaterial.FEATHER.parseMaterial()));
					wingschoose.setItem(1, ItemParticlesHandler.getItemStack("§cType 2", null, XMaterial.FEATHER.parseMaterial()));
					wingschoose.setItem(2, ItemParticlesHandler.getItemStack("§cType 3", null, XMaterial.FEATHER.parseMaterial()));
					wingschoose.setItem(3, ItemParticlesHandler.getItemStack("§cType 4", null, XMaterial.FEATHER.parseMaterial()));
					wingschoose.setItem(4, ItemParticlesHandler.getItemStack("§cType 5", null, XMaterial.FEATHER.parseMaterial()));
					p.openInventory(wingschoose);
					return;
				}
				if (e.getCurrentItem().getType() == XMaterial.NETHER_STAR.parseMaterial()) {
					p.closeInventory();
					e.setCancelled(true);
					Inventory starchoose = Bukkit.createInventory(null, 9, "§cChoose stars");
					starchoose.setItem(0, ItemParticlesHandler.getItemStack("§cType 1", null, XMaterial.NETHER_STAR.parseMaterial()));
					starchoose.setItem(1, ItemParticlesHandler.getItemStack("§cType 2", null, XMaterial.NETHER_STAR.parseMaterial()));
					starchoose.setItem(2, ItemParticlesHandler.getItemStack("§cType 3", null, XMaterial.NETHER_STAR.parseMaterial()));
					p.openInventory(starchoose);
					return;
				}
				
				p.sendMessage("§cThe shape has been set to §b" + choix);
				
				
			}
		}
		
		if (e.getView().getTitle().equals("§cChoose wings")) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()) {
				ParticleEffect getparticle = currentparticleeffectgui.get(p);
				e.setCancelled(true);
				String choix = null;
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cType 1")) {choix = "wings1"; getparticle.setShape("wings1");};
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cType 2")) {choix = "wings2"; getparticle.setShape("wings2");};
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cType 3")) {choix = "wings3"; getparticle.setShape("wings3");};
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cType 4")) {choix = "wings4"; getparticle.setShape("wings4");};
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cType 5")) {choix = "wings5"; getparticle.setShape("wings5");};
				p.sendMessage("§cYou chose the type " + choix + " for your wings !");
				p.closeInventory();
				return;
				
			}
		}
		if (e.getView().getTitle().equals("§cChoose stars")) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()) {
				ParticleEffect getparticle = currentparticleeffectgui.get(p);
				e.setCancelled(true);
				String choix = null;
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cType 1")) {choix = "star"; getparticle.setShape("star");};
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cType 2")) {choix = "star2"; getparticle.setShape("star2");};
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cType 3")) {choix = "star3"; getparticle.setShape("star3");};
				p.sendMessage("§cYou chose the type " + choix + " for your wings !");
				p.closeInventory();
				return;
				
			}
		}
		
		if (e.getView().getTitle().equals("§cParticle")) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()) {
				ParticleEffect getparticle = currentparticleeffectgui.get(p);
				e.setCancelled(true);
				ItemParticlesHandler.openPrincipalGui(p, 1);
				String choix = null;
				if (e.getCurrentItem().getType() == XMaterial.TORCH.parseMaterial()) {choix = "flame"; getparticle.setParticle(XParticle.FLAME);}
				if (e.getCurrentItem().getType() == XMaterial.EMERALD_BLOCK.parseMaterial()) {choix = "happyvillager"; getparticle.setParticle(XParticle.VILLAGER_HAPPY);}
				if (e.getCurrentItem().getType() == XMaterial.QUARTZ_BLOCK.parseMaterial()) {choix = "cloud"; getparticle.setParticle(XParticle.CLOUD);}
				if (e.getCurrentItem().getType() == XMaterial.JUKEBOX.parseMaterial()) {choix = "note"; getparticle.setParticle(XParticle.NOTE);}
				if (e.getCurrentItem().getType() == XMaterial.REDSTONE.parseMaterial()) {
					p.closeInventory();
					getparticle.setParticle(XParticle.REDSTONE);
					getparticle.setColor(Color.RED);
					e.setCancelled(true);
					Inventory colorchoose = Bukkit.createInventory(null, 9, "§cChoose a color");
					colorchoose.setItem(0, XMaterial.RED_WOOL.parseItem());
					colorchoose.setItem(1, XMaterial.BLUE_WOOL.parseItem());
					colorchoose.setItem(2, XMaterial.YELLOW_WOOL.parseItem());
					colorchoose.setItem(3, XMaterial.GREEN_WOOL.parseItem());
					colorchoose.setItem(4, XMaterial.PURPLE_WOOL.parseItem());
					colorchoose.setItem(5, XMaterial.ORANGE_WOOL.parseItem());
					colorchoose.setItem(6, XMaterial.PINK_WOOL.parseItem());
					colorchoose.setItem(7, XMaterial.WHITE_WOOL.parseItem());
					colorchoose.setItem(8, XMaterial.BLACK_WOOL.parseItem());
					p.openInventory(colorchoose);
					return;
				}
				if (e.getCurrentItem().getType() == XMaterial.SNOWBALL.parseMaterial()) {choix = "snow"; getparticle.setParticle(XParticle.SNOW_SHOVEL);}
				if (e.getCurrentItem().getType() == XMaterial.LAVA_BUCKET.parseMaterial()) {choix = "lava"; getparticle.setParticle(XParticle.DRIP_LAVA);}
				if (e.getCurrentItem().getType() == XMaterial.WATER_BUCKET.parseMaterial()) {choix = "water"; getparticle.setParticle(XParticle.DRIP_WATER);}
				if (e.getCurrentItem().getType() == XMaterial.COAL_BLOCK.parseMaterial()) {choix = "lavaexplosion"; getparticle.setParticle(XParticle.LAVA);}
				if (e.getCurrentItem().getType() == XMaterial.TNT.parseMaterial()) {choix = "explosion"; getparticle.setParticle(XParticle.EXPLOSION_LARGE);}
				if (e.getCurrentItem().getType() == XMaterial.APPLE.parseMaterial()) {choix = "heart"; getparticle.setParticle(XParticle.HEART);}
				if (e.getCurrentItem().getType() == XMaterial.FIREWORK_ROCKET.parseMaterial()) {choix = "firework"; getparticle.setParticle(XParticle.FIREWORKS_SPARK);}
				if (e.getCurrentItem().getType() == XMaterial.OBSIDIAN.parseMaterial()) {choix = "portal"; getparticle.setParticle(XParticle.PORTAL);}
				if (e.getCurrentItem().getType() == XMaterial.ENCHANTING_TABLE.parseMaterial()) {choix = "enhancement"; getparticle.setParticle(XParticle.ENCHANTMENT_TABLE);}
				if (e.getCurrentItem().getType() == XMaterial.DIAMOND_SWORD.parseMaterial()) {choix = "crit"; getparticle.setParticle(XParticle.CRIT);}
				if (e.getCurrentItem().getType() == XMaterial.GLASS_BOTTLE.parseMaterial() && e.getCurrentItem().getItemMeta().getDisplayName().equals("Bubbles")) {choix = "bubbles"; getparticle.setParticle(XParticle.SPELL);}
				if (e.getCurrentItem().getType() == XMaterial.BOOK.parseMaterial()) {choix = "magic"; getparticle.setParticle(XParticle.SPELL_WITCH);}
				if (e.getCurrentItem().getType() == XMaterial.SLIME_BALL.parseMaterial()) {choix = "slime"; getparticle.setParticle(XParticle.SLIME);}
				if (e.getCurrentItem().getType() == XMaterial.CARVED_PUMPKIN.parseMaterial()) {choix = "angry villager"; getparticle.setParticle(XParticle.VILLAGER_ANGRY);}
				if (e.getCurrentItem().getType() == XMaterial.CLAY.parseMaterial()) {choix = "smoke"; getparticle.setParticle(XParticle.SMOKE_NORMAL);}
				if (e.getCurrentItem().getType() == XMaterial.GLASS_BOTTLE.parseMaterial() && e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§b")) {
					if (Bukkit.getVersion().contains("1.7")) {
						p.sendMessage("§cThis particle is not available in 1.7.");
						p.closeInventory();
						return;
					}
					choix = "water bubbles"; getparticle.setParticle(XParticle.WATER_BUBBLE);}
				if (getparticle.getColor() != null) getparticle.setColor(null);
				p.sendMessage("§cThe particle has been set to §b" + choix);
				p.closeInventory();
				ItemParticlesHandler.openPrincipalGui(p, 1);
			}
		}
		if (e.getView().getTitle().contentEquals("§cChoose a color")) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != XMaterial.AIR.parseMaterial()) {
				ParticleEffect getparticle = currentparticleeffectgui.get(p);
				e.setCancelled(true);
				String choix = null;
				if (e.getCurrentItem().equals(XMaterial.RED_WOOL.parseItem())) {choix = "red"; getparticle.setColor(Color.RED);};
				if (e.getCurrentItem().equals(XMaterial.BLUE_WOOL.parseItem())) {choix = "blue"; getparticle.setColor(Color.BLUE);};
				if (e.getCurrentItem().equals(XMaterial.YELLOW_WOOL.parseItem())) {choix = "yellow"; getparticle.setColor(Color.YELLOW);};
				if (e.getCurrentItem().equals(XMaterial.GREEN_WOOL.parseItem())) {choix = "green"; getparticle.setColor(Color.GREEN);};
				if (e.getCurrentItem().equals(XMaterial.PURPLE_WOOL.parseItem())) {choix = "purple"; getparticle.setColor(Color.PURPLE);};
				if (e.getCurrentItem().equals(XMaterial.ORANGE_WOOL.parseItem())) {choix = "orange"; getparticle.setColor(Color.ORANGE);};
				if (e.getCurrentItem().equals(XMaterial.PINK_WOOL.parseItem())) {choix = "pink"; getparticle.setColor(Color.fromRGB(255,192,203));};
				if (e.getCurrentItem().equals(XMaterial.WHITE_WOOL.parseItem())) {choix = "white"; getparticle.setColor(Color.WHITE);};
				if (e.getCurrentItem().equals(XMaterial.BLACK_WOOL.parseItem())) {choix = "black"; getparticle.setColor(Color.BLACK);};
				
				p.sendMessage("§cThe color of the redstone particle has been set to : " + choix);
				p.closeInventory();
				ItemParticlesHandler.openPrincipalGui(p, 1);
			}
			
		}
		}

	
}
