package com.example.productivityapp;

public class ToDoItem implements Comparable<ToDoItem> {
    private String item;
    private boolean complete;

    ToDoItem()
    {
        item = "";
        complete = false;
    }

    ToDoItem(String text)
    {
        item = text;
        complete = false;
    }

    public void completeTask()
    {complete = true;}

    public void setItem(String text)
    { item = text;}

    public boolean getComplete()
    {   return complete; }

    public String getItem()
    {   return item;}

    public int compareTo(ToDoItem anotherOne)
    {
        return getItem().compareTo(anotherOne.getItem());
    }


}
