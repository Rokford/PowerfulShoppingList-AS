package com.mobinautsoftware.powerfulmealplanner;


import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utilities
{
    public static String[] navigationItemsArray = {App.getContext().getResources().getString(R.string.shopping_list), App.getContext().getResources().getString(R.string.recipies_list), App.getContext().getResources().getString(R.string.callendar), App.getContext().getResources().getString(R.string.help)};

    public static final String CATEGORY_MEAT = "Meats & fish";
    public static final String CATEGORY_DIARY = "Dairy & bread";
    public static final String CATEGORY_FRUITS = "Fruits & vegetables";
    public static final String CATEGORY_CEREALS = "Cereals & spices";
    public static final String CATEGORY_TINNED = "Tinned & frozen";
    public static final String CATEGORY_OTHER = "Other";

    public static String[] shoppingListTabsItemsArray = {CATEGORY_MEAT, CATEGORY_DIARY, CATEGORY_FRUITS, CATEGORY_CEREALS, CATEGORY_TINNED, CATEGORY_OTHER};

    public static String formatDateforDB(Date date)
    {
        return new SimpleDateFormat("ddMMyyyy").format(date);
    }

    public static ArrayList<Date> getDates(Date date1, Date date2)
    {
        ArrayList<Date> dates = new ArrayList<Date>();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2))
        {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static ArrayList<Date> getAllDatesForMonth(int month)
    {
        final Calendar calendar = Calendar.getInstance();

        ArrayList<Date> dates = new ArrayList<Date>();

        int currentMonth;

//        if (month > 0)
            currentMonth = month;
//        else
//            currentMonth = calendar.get(Calendar.MONTH) + 1;

        int currentYear = calendar.get(Calendar.YEAR);

        Log.e("month: ", Integer.valueOf(currentMonth).toString());
        Log.e("year: ", Integer.valueOf(currentYear).toString());

        calendar.clear();

        calendar.set(Calendar.YEAR, currentYear);
        calendar.add(Calendar.MONTH, currentMonth);


        int numDays = calendar.getActualMaximum(Calendar.DATE);

        for (int i = 1; i < numDays + 1; i++)
        {
            calendar.set(Calendar.DAY_OF_MONTH, i);

            Date dd = calendar.getTime();

            dates.add(dd);
        }

        return dates;
    }

    public static ArrayList<ShoppingItem> removeDuplicatesFormShoppingItemsList(ArrayList<ShoppingItem> shoppingItems)
    {
        final ArrayList<ShoppingItem> shoppingItemsWithoutDuplicates = new ArrayList<ShoppingItem>();

        while (shoppingItems.size() > 0)
        {
            ShoppingItem firstItem = shoppingItems.get(0);

            for (int i = 1; i < shoppingItems.size(); i++)
            {
                ShoppingItem iteratedItem = shoppingItems.get(i);

                if (iteratedItem.getItem().equals(firstItem.getItem()) && iteratedItem.getUnit().equals(firstItem.getUnit()))
                {
                    Float f = Float.parseFloat(firstItem.getQuantity().length() > 0 ? firstItem.getQuantity() : "0") + Float.parseFloat(iteratedItem.getQuantity().length() > 0 ? iteratedItem.getQuantity() : "0");

                    if (f % 1 == 0) firstItem.setQuantity(Integer.valueOf(f.intValue()).toString());
                    else firstItem.setQuantity(f.toString());

                    shoppingItems.remove(iteratedItem);
                }
            }

            shoppingItemsWithoutDuplicates.add(firstItem);
            shoppingItems.remove(firstItem);
        }

        return shoppingItemsWithoutDuplicates;
    }

    public static int iconForTabString(String tabString)
    {
        if (tabString.equals("Meats & fish"))
            return R.drawable.meat_fish;
        else if (tabString.equals("Dairy & bread"))
            return R.drawable.bread_diary;
        else if (tabString.equals("Fruits & vegetables"))
            return R.drawable.fruits_vegetables;
        else if (tabString.equals("Cereals & spices"))
            return R.drawable.spices_cerelas;
        else if (tabString.equals("Tinned & frozen"))
            return R.drawable.tinned_frozen;
        else
            return R.drawable.other;
    }
}
