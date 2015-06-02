package com.anontech.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {

    NULL(0, "NULL"),
    ADMIN(1, "Admin"),
    USER(2, "User");
    
    private final int id;
    private final String name;

    //used in listbox
    public static final Map<Integer, String> enumMap = new HashMap<Integer, String>();
    static {
    	for (RoleEnum type : RoleEnum.values()) {
    		if(type.id != 0)//Null enum is not added.. null should not appear in listbox 
    			enumMap.put(type.id, type.name);
    	}
    }
    public static final int TOTAL_COUNT = enumMap.size() +1;
    

    private RoleEnum(int c, String n) {
        id = c;
        name = n;
    }
    
    private static final RoleEnum[] list = {NULL, ADMIN, USER};

    public int toInt() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RoleEnum fromInt(Integer c) {
        if (c == null) {
            return RoleEnum.NULL;
        }
        if (c < 0 || c > TOTAL_COUNT) {
            throw new RuntimeException("Unknown id for fromInt in Role, id [" + c + "]");
        }
        return list[c];
    }

    public static RoleEnum[] getRoleList() {
        return list;
    }
}
