import {CREATE_LISTING, GET_LISTING} from "../actions/types";

const initialState = {

};

export default function(state = initialState, action) {
    switch (action.type) {

        case GET_LISTING:
            return {
                listings: action.payload
            }
        case CREATE_LISTING:
            return {
                newListing: [...state, action.payload]
            }
        default:
            return state;
    }
}