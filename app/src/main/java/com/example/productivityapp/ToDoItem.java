package com.example.productivityapp;
//Author Philip Huang
import java.util.HashMap;
import java.util.Map;

public class ToDoItem implements Comparable<ToDoItem> {

    private String item;
    private boolean complete;

    public ToDoItem()
    {
        item = "";
        complete = false;
    }

    public ToDoItem(String text)
    {
        item = text;
        complete = false;
    }

    public void completeTask()
    {
        complete = true;
    }

    public void setItem(String text)
    {
        item = text;
    }

    public boolean getComplete()
    {
        return complete;
    }

    public String getItem()
    {
        return item;
    }

    public int compareTo(ToDoItem other)
    {
        return getItem().compareTo(other.getItem());
    }

    public Map<String, Object> toMap()
    {
        HashMap<String, Object> result = new HashMap<String, Object>();

        result.put("item", item);
        result.put("complete", complete);

        return result;
    }
}
