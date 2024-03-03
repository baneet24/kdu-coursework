import { RootState } from "../../../state/store";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { handleDelete } from "../../../state/Reducer/toDoListSlice";

const styles = {
  list: {
    paddingLeft: "1rem",
  },

  listItem: {
    display: "flex",
    justifyContent: "space-between",
    fontSize: "20px",
    marginRight: "27px",
    border: "solid 2px #eeeeee",
    padding: "1rem 1.5rem",
  },

  buttonClass: {
    color: "white",
    backgroundColor: "red",
    border: "none",
    padding: "0.6rem",
    fontSize: "13px",
    borderRadius: "0.2rem",
  },

  heading: {
    fontSize: "32px",
    fontWeight: "400",
    marginLeft: "10px",
  },
};

export const TodoList = () => {
  const searchItem = useSelector(
    (state: RootState) => state.todoList.searchItem
  );
  const todoList = useSelector((state: RootState) => state.todoList.todoList);
  const reduxDispatch = useDispatch();

  const newList = todoList.filter((item) =>
    item.toLowerCase().includes(searchItem.toLowerCase())
  );

  const Delete = (index: number) => {
    reduxDispatch(handleDelete(index));
  };

  return (
    <>
      <h2 style={styles.heading}>Items</h2>
      {newList.length === 0 ? (
        <p>No matches found</p>
      ) : (
        <ul style={styles.list}>
          {newList.map((item, index) => (
            <li key={index} style={styles.listItem}>
              {item}
              <button onClick={() => Delete(index)} style={styles.buttonClass}>
                &#x2715;
              </button>
            </li>
          ))}
        </ul>
      )}
    </>
  );
};
