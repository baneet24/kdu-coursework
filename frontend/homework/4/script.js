function addTodo() {
  // Function to Add TODO Item
  var input = document.getElementById("todo-input");
  var inputValue = input.value.trim();

  if (inputValue === "") {
    return;
  }

  var todoList = document.getElementById("todo-list");
  var listItem = document.createElement("li");
  listItem.className = "todo-item";

  var checkbox = document.createElement("input");
  checkbox.type = "checkbox";
  checkbox.className = "checkbox";

  // Create a button to delete the TODO item
  var deleteButton = document.createElement("button");
  deleteButton.className = "delete-btn";
  deleteButton.innerHTML = "DELETE";
  deleteButton.onclick = function () {
    todoList.removeChild(listItem);
  };

  // Append checkbox, task text, and delete button
  listItem.appendChild(checkbox);
  listItem.appendChild(document.createTextNode(inputValue));
  listItem.appendChild(deleteButton);
  todoList.appendChild(listItem);

  input.value = "";
}
