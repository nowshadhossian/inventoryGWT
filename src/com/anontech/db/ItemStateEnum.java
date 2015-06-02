package com.anontech.db;

public enum ItemStateEnum {
	 NULL(0, "NULL"),
	 NOTRETURNED(1, "Not Returned"),
	 RETURNED(2, "Returned"),
	 DAMAGED(3, "Damaged"),
	 LOST(4, "Lost"),
	 MINORDAMAGE(5, "Minor Damage");
	 
	private final int id;
	private final String name;
	public static final int TOTAL_COUNT = 6;
	
	private ItemStateEnum(int c, String n) {
	    id = c;
	    name = n;
	}
	
	private static final ItemStateEnum[] list = {NULL, NOTRETURNED, RETURNED, DAMAGED, LOST, MINORDAMAGE};
	
	public int toInt() {
	    return id;
	}
	
	public String getName() {
	    return name;
	}
	
	public static ItemStateEnum fromInt(Integer c) {
	    if (c == null) {
	        return ItemStateEnum.NULL;
	    }
	    if (c < 0 || c > TOTAL_COUNT) {
	        throw new RuntimeException("Unknown id for fromInt in Item State, id [" + c + "]");
	    }
	    return list[c];
	}
	
	public static ItemStateEnum[] getItemStateList() {
	    return list;
	}
}
