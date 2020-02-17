import java.util.List;
import java.util.Scanner;

import controller.ListItemHelper;
import model.ListItems;

public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static ListItemHelper lih = new ListItemHelper();
	//this is the starter program

	private static void addAnItem() {
		// TODO Auto-generated method stub

		System.out.print("Enter a Brand: ");
		String store = in.nextLine();
		System.out.print("Enter an Color: ");
		String item = in.nextLine();

		ListItems toAdd = new ListItems(store, item);
		lih.insertItem(toAdd);

	}

	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the Brand to delete: ");
		String store = in.nextLine();
		System.out.print("Enter the Color to delete: ");
		String item = in.nextLine();
		ListItems toDelete = new ListItems(store, item);
		lih.deleteItem(toDelete);

	}

	private static void editAnItem() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Brand");
		System.out.println("2 : Search by Color");
		int searchBy = in.nextInt();
		in.nextLine();

		List<ListItems> foundItems;
		if	(searchBy	==	1)	{
			System.out.print("Enter	the	brand	name:	");
			
			String	brand	=	in.nextLine();
			foundItems	=	lih.searchForItemByBrand(brand);
			}	else	{
			System.out.print("Enter	the	color:	");
			String	itemName	=	in.nextLine();
			foundItems	=	lih.searchForItemByItem(itemName);
			}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (ListItems l : foundItems) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListItems toEdit = lih.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getColor() + " from " + toEdit.getBrand());
			System.out.println("1 : Update Brand");
			System.out.println("2 : Update Color");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Brand: ");
				String newBrand = in.nextLine();
				toEdit.setBrand(newBrand);
			} else if (update == 2) {
				System.out.print("New Color: ");
				String newColor = in.nextLine();
				toEdit.setColor(newColor);
			}

			lih.updateItem(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our awesome Lawn Mowing shopping list! ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add an item");
			System.out.println("*  2 -- Edit an item");
			System.out.println("*  3 -- Delete an item");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the awesome program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lih.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<ListItems> allItems = lih.showAllItems();
		for (ListItems singleItem : allItems) {
			System.out.println(singleItem.returnItemDetails());
		}

	}

}
