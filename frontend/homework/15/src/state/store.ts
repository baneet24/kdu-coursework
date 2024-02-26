import {configureStore} from '@reduxjs/toolkit';
import {todoListReducer} from './Reducer/toDoListSlice';

export const store = configureStore({
    reducer: {
        todoList: todoListReducer
    }
})

export type RootState = ReturnType<typeof store.getState >