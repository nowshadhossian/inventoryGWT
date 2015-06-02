package com.anontech.db;

public enum PurposeEnum {

	 NULL(0, "NULL"),
	 REPAIR(1, "Repair"),
	 OFFICIAL_USE(2, "Official Use"),
	 NEWS(1, "News"),
	 PROGRAM(1, "Program"),
	 LIVE(1, "Live");
	 
	private final int id;
	private final String name;
	public static final int TOTAL_COUNT = 6;
	
	private PurposeEnum(int c, String n) {
	    id = c;
	    name = n;
	}
	
	private static final PurposeEnum[] list = {NULL, REPAIR, OFFICIAL_USE, NEWS, PROGRAM, LIVE};
	
	public int toInt() {
	    return id;
	}
	
	public String getName() {
	    return name;
	}
	
	public static PurposeEnum fromInt(Integer c) {
	    if (c == null) {
	        return PurposeEnum.NULL;
	    }
	    if (c < 0 || c > TOTAL_COUNT) {
	        throw new RuntimeException("Unknown id for fromInt in Purpose, id [" + c + "]");
	    }
	    return list[c];
	}
	
	public static PurposeEnum[] getPurposeList() {
	    return list;
	}
	 
}
