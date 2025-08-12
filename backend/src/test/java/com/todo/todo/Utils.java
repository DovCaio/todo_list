package com.todo.todo;

public class Utils {

    public class TodoItemFactory {
        public static TodoItem createTodoItem(String title, String description) {
            TodoItem item = new TodoItem();
            item.setTitle(title);
            item.setDescription(description);
            return item;
        }
    }

}
