package com.mobinautsoftware.powerfulmealplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class RecipeListAdapter extends BaseAdapter
{
    private ArrayList<String> recipeNamesList;
    private ViewHolder holder;
    private LayoutInflater inflater;

    private Boolean[] checkedRecipes;
    // private SparseBooleanArray mSelectedItemsIds;

    private boolean withCheckboxes = false;

    public RecipeListAdapter(Context context, boolean withCheckboxes)
    {
        // mSelectedItemsIds = new SparseBooleanArray();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (withCheckboxes)
        {
            this.withCheckboxes = true;
        }
    }

    @Override
    public int getCount()
    {
        return recipeNamesList.size();
    }

    @Override
    public String getItem(int position)
    {
        return recipeNamesList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        holder = null;

        if (convertView == null)
        {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.recipe_list_view_item, null);

            holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTextView.setText(recipeNamesList.get(position));

        if (withCheckboxes)
        {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if (isChecked)
                        checkedRecipes[position] = true;
                    else
                        checkedRecipes[position] = false;
                }
            });
        }
        else
            holder.checkBox.setVisibility(View.GONE);

        return convertView;
    }

    public static class ViewHolder
    {
        public TextView nameTextView;
        public CheckBox checkBox;
    }

    public ArrayList<String> getRecipeNamesListList()
    {
        return recipeNamesList;
    }

    public void setRecipeItemsList(ArrayList<String> recipeItemsList)
    {
        Collections.sort(recipeItemsList);

        this.recipeNamesList = recipeItemsList;

        checkedRecipes = new Boolean[recipeNamesList.size()];

        for (int i = 0; i < checkedRecipes.length; i++)
        {
            checkedRecipes[i] = new Boolean(false);
        }
    }

    public Boolean[] getCheckedRecipes()
    {
        return checkedRecipes;
    }
}
