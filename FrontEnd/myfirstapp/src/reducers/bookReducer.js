import { GET_BOOK, GET_SEARCHED_BOOKS} from "../actions/types";

const initialState = {
book:[]
};

export default function(state = initialState, action) {
    switch (action.type) {

        case GET_BOOK:
            return {
                book: action.payload
            };
        case GET_SEARCHED_BOOKS:
            return {
                searchedBooks: action.payload
            }
        default:
            return state;
    }
}