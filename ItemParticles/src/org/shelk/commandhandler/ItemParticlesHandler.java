package org.shelk.commandhandler;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.shelk.ItemParticles;
import org.shelk.ParticleEffect;
import org.shelk.utils.XMaterial;

public class ItemParticlesHandler implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("itemparticles")) {
			
			
			Player p = (Player) sender;
			if (!p.hasPermission("itemparticles.modify")) {
				p.sendMessage("§cYou don't have the permission to do this command.");
				return false;
			}
			if (sender instanceof Player) {
				if (args.length == 0) {
					openPrincipalGui(p,1);
				} else {
					if (args.length == 1) {
						
						if (args[0].equals("modifyamount")) {
							p.sendMessage("§cIf you want to modify the amount of your particles, use the command §b/itemparticles modifyamount <id> <amountofparticles>§c.");
							return false;
						}
						ItemStack getitem;
						if (Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.7")) {
							getitem = p.getItemInHand();
						} else {
							getitem = p.getInventory().getItemInMainHand();
						}
						
						
						
						
						if (getitem.getType() == XMaterial.AIR.parseMaterial()) {
							p.sendMessage("§cYou must have an item in hand !");
							return false;
						}
						
						for(ParticleEffect pe : ItemParticles.listparticleeffect) {
							if (pe.getId().equals(args[0])) {
								p.sendMessage("§cId already taken !");
								return false;
							}
						}

					    String name = null;
						List<String> lore = null;
						Material type = getitem.getType();
						
						if (getitem.hasItemMeta() && getitem.getItemMeta().getDisplayName() != null) name = getitem.getItemMeta().getDisplayName();
						if (getitem.hasItemMeta() && getitem.getItemMeta().getLore() != null) lore = getitem.getItemMeta().getLore();
					
						
						ParticleEffect pe = new ParticleEffect(name,lore,type, args[0], null, null, 0, null, null);
						ItemParticles.listparticleeffect.add(pe);
						p.sendMessage("§aYou created a new item particle !");
						openPrincipalGui(p, 1);
						
					} else {
						
						if (args[0].equals("modifyamount")) {
							
							for(ParticleEffect pe : ItemParticles.listparticleeffect) {
								if (pe.getId().equals(args[1])) {
									
									try {
										@SuppressWarnings("unused")
										int trycatch = Integer.parseInt(args[2]);
								    } catch (NumberFormatException | NullPointerException nfe) {
								    	p.sendMessage("§cWrong arguments ! Are you sure that the amount of particles you wrote is a number ?");
								        return false;
								    }
									
									pe.setAmount(Integer.parseInt(args[2]));
									p.sendMessage("§cThe amount of particles has been set on §b" + args[2] + ".");
									
									
									return false;
								}
							}
							p.sendMessage("§cNo item particle found with this id !");
							
						} else {
							p.sendMessage("§cWrong arguments !");
						}
						
						
					}
					
				}
			}
		}
		
		return false;
		
	}
		
		
	public static ItemStack getItemStack(String name, List<String> lore, Material type) {
		ItemStack is = new ItemStack(type);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	public static void openPrincipalGui(Player p, int page) {
		
		
		
		Inventory inv = Bukkit.createInventory(null, 9*6, ChatColor.DARK_BLUE + "ItemParticles");
		int i = 1;
		if (page != 1) i = 0;
		
		int numberinthelist = 0;
		if (page == 1) {
			inv.setItem(0, getItemStack("§aCreate a new item particle", null, XMaterial.BLAZE_ROD.parseMaterial()));
		}
		for (ParticleEffect pe : ItemParticles.listparticleeffect) {
			if (page != 1) {
				if (numberinthelist < 52 * (page - 1)) {
					numberinthelist++;
					continue;
				}
			}

			ItemStack is = new ItemStack(XMaterial.MAGMA_CREAM.parseMaterial());
			ItemMeta im = is.getItemMeta();
			if (pe.getId() != null) im.setDisplayName("§d§l" + pe.getId());
			
			
			String name = "none";
			String type = "none";
			String lore = "none";
			if (pe.getName() != null) name = pe.getName();
			if (pe.getItem() != null) type = pe.getItem().toString();
			if (pe.getLore() != null) lore = "set";
			
			
			im.setLore(Arrays.asList(
					"§b§lItem's name: " + name,
					"§b§lItem type: " + type,
					"§b§lItem's lore: " + lore,
					"" + numberinthelist));
					
					
			is.setItemMeta(im);
			inv.setItem(i, is);
			
			if (i == 52) {
				ItemStack nextpage = new ItemStack(XMaterial.PAPER.parseItem());
				ItemMeta pagemeta = nextpage.getItemMeta();
				pagemeta.setDisplayName("§c>Page " + (page + 1));
				nextpage.setItemMeta(pagemeta);
				inv.setItem(53, nextpage);
				break;
				
				
				}
			i++;
			numberinthelist++;			}
			
		p.openInventory(inv);
		}
		
		
		
		
	}
	


