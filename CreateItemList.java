package ordering_system;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateItemList {

	ArrayList<Item> itemList=new ArrayList<Item>();
	
	public CreateItemList()
	{
		itemList.add(new Item("001","Book",350));
		itemList.add(new Item("002","Umbrella",5000));
		itemList.add(new Item("003","Pen",250));
		itemList.add(new Item("004","Correction Pen",1350));
		itemList.add(new Item("005","Pencil",150));
		itemList.add(new Item("006","Eraser",100));
		itemList.add(new Item("007","Cola",450));
		itemList.add(new Item("008","Ruler",250));
		itemList.add(new Item("009","Tape",800));
		itemList.add(new Item("010","Scissor",2300));
		itemList.add(new Item("011","Glue",350));
		itemList.add(new Item("012","Crayon",1800));
	}

	public ArrayList<Item> getItemList()
	{
		return itemList;
	}
	public Item getItem(String itemID)
	{
		Item obj=null;
		for(int i=0;i<itemList.size();i++)
			if(itemList.get(i).getItemID().equals(itemID))
				obj=itemList.get(i);
		return obj;
	}

}
