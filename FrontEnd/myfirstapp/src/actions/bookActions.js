import axios from "axios";
import {GET_BOOK, GET_SEARCHED_BOOKS} from "./types";

export const getRandBook = id => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8082/api/books/get/random/?size=${id}`);

        dispatch({
            type: GET_BOOK,
            payload: res.data
        });
    } catch (error) {
        console.log(error)
    }
};

export const getAuthorBook = id => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8082/api/books/get/author?author=${id}`);

        dispatch({
            type: GET_BOOK,
            payload: res.data
        });
    } catch (error) {

    }
};

export const getTitleBook = id => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8082/api/books/get/title?title=${id}`);

        dispatch({
            type: GET_BOOK,
            payload: res.data
        });
    } catch (error) {

    }
};
export const getGenreBook = id => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8082/api/books/get/genre?genre=${id}`);

        dispatch({
            type: GET_BOOK,
            payload: res.data
        });
    } catch (error) {

    }
};

export const getBook = id => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8082/api/books/get/isbn?ISBN=${id}`);

        dispatch({
            type: GET_BOOK,
            payload: [res.data]
        });
    } catch (error) {

    }
};

export const getBooksBySearchTerm = term => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8082/api/books/search`, {
            params: {
                term
            }
        });

        dispatch({
            type: GET_SEARCHED_BOOKS,
            payload: [res.data]
        });
    } catch (error) {

    }
}

