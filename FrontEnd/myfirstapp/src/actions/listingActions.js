import axios from "axios";
import {CREATE_LISTING, GET_LISTING} from "./types";

export const getISBNListing = id => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8081/api/listings/isbn/${id}`);

        dispatch({
            type: GET_LISTING,
            payload: res.data
        });
    } catch (error) {
        console.log(error)
    }
};

export const createListing = listing => async dispatch => {
    try {
        const {condition, price, isbn, isPublisher, isSold, userId} = listing
        const res = await axios.post(`http://localhost:8081/api/listings`, {
            condition,
            price,
            isbn,
            isPublisher,
            isSold,
            userId
        });
        dispatch({
            type: CREATE_LISTING,
            payload: res.data
        });
    } catch (error){
        console.log(error)
    }
}




