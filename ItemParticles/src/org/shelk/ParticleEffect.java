package org.shelk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.shelk.utils.XParticle;

@SerializableAs("ItemParticle")
public class ParticleEffect implements ConfigurationSerializable {
	private String name = null;
	private List<String> lore = null;
	private Material type = null;
	private String id = null;
	private String shape = null;
	private XParticle pa = null;
	private int amount;
	private String hands = null;
	private Color color = null;
	
	public ParticleEffect(String name, List<String> lore, Material type, String id, String shape, XParticle pa, Integer amount, String hands, Color color) {
		this.name = name;
		this.lore = lore;
		this.type = type;
		this.id = id;
		this.shape = shape;
		this.pa = pa;
		this.amount = amount;
		this.hands = hands;
		this.color = color;
	}

	public String getName() {
		return name;
	}
	
	public List<String> getLore() {
		return this.lore;
	}
	
	public Material getItem() {
		return this.type;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getShape() {
		return this.shape;
	}
	
	public void setShape(String shape) {
		this.shape = shape;
	}
	
	public XParticle getParticle() {
		return this.pa;
	}
	
	public void setParticle(XParticle flame) {
		this.pa = flame;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getHands() {
		return this.hands;
	}
	public void setHands(String hands) {
		this.hands = hands;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<String , Object>();

        map.put("name", this.name);
        map.put("lore", this.lore);
        map.put("material", this.type.toString());
        map.put("id", this.id);
        map.put("shape", this.shape);
        String name = null;
        if (this.pa != null) name = this.pa.name();
        map.put("particle", name);
        map.put("amount", this.amount);
        map.put("hands", this.hands);
        map.put("color", this.color);
        
        
        
   
        return map;
	}

	@SuppressWarnings("unchecked")
	public static ParticleEffect deserialize(Map<String, Object> args) {
		String name = (String) args.get("name");
		List<String> lore = (List<String>) args.get("lore");
		Material type = Material.getMaterial((String) args.get("material"));
		String id = (String) args.get("id");
		String shape = (String) args.get("shape");
		XParticle pa = XParticle.FLAME; 
		for (XParticle xp : XParticle.values()){
			if (xp.name().equals(args.get("particle"))) {
				pa = XParticle.valueOf((String) args.get("particle"));
			}
		}
		int amount = (int) args.get("amount");
		String hands = (String) args.get("hands");
		Color color = (Color) args.get("color");
		return new ParticleEffect(name,lore,type,id,shape,pa,amount,hands,color);
    }

	
}
