package com.tcsg.hello.model;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class UserMap {
	private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public static void addUser(String userid, String userData) {
		map.put(userid, userData);
	}
	
	public static void removeUser(String userid) {
		map.remove(userid);
	}
	
	public static String getUsers() {
		String content = "";

	    Iterator<String> valueIterator = map.values().iterator();
	    while(valueIterator.hasNext()){
	    	content += valueIterator.next();
	    	if (valueIterator.hasNext()) content += "|";
	    }

		return content;	
	}
	
	public static void updateUsers(String usersData) {
		map.clear();
		if (!usersData.isEmpty()) {
			String[] userList = usersData.split("\\|");
			for (String item : userList) {
				String[] user = item.split("\\~");
				map.put(user[0], item);
			}
		}
	}	
	
}
