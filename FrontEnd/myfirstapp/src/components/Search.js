import React, {Component} from "react";
import { connect } from "react-redux";
import {getBook, getAuthorBook, getTitleBook, getGenreBook, getBooksBySearchTerm} from "../actions/bookActions";
import BookCard from "./Layout/BookCard";

class Search extends Component {

renderSearchedBooks() {
        return this.props.book.map(book => {
            return (
                <React.Fragment key={book.isbn}>
                    <BookCard book={book} />
                </React.Fragment>
            )
        })
    }

render() {


        return (
            <div className="landing">
                <div>
                    <div className="d-flex p-2 bd-highlight  justify-content-center">{this.props.book ? this.renderSearchedBooks(): ''}</div>
                </div>
            </div>


        );
    }


}

const mapStateToProps = state => {
    return {book: state.book.searchedBooks};
};

export default connect(
    mapStateToProps,
    {getBooksBySearchTerm}
)(Search);