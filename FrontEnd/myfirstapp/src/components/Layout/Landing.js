import React, {Component} from "react";
import { connect } from "react-redux";
import {getRandBook, getBooksBySearchTerm} from "../../actions/bookActions";
import cover from "isbn-cover"
import {Link} from "react-router-dom";
import BookCard from "./BookCard";
class Landing extends Component {

    componentDidMount(){
        this.props.getRandBook(6);
    }


    renderBooks() {
        return this.props.book.map(book => {
            return (
                <BookCard book={book} />
            )
        })
    }

    renderSearchedBooks() {
        return this.props.searchedBooks[0].map(book => {

            return (
                <BookCard book={book} />
            )
        })
    }

    render() {


        return (
            <div className="landing">
                <div>
                    <div className={"container"}>
                        <p className={"display-1 mx-auto text-center mt-5"}>{ this.props.book ? 'Recently added': 'Results'}</p>
                    </div>
                    <div className="d-flex p-2 bd-highlight  justify-content-center">{this.props.book ? this.renderBooks() : ''}</div>
                    <div className="d-flex p-2 bd-highlight  justify-content-center">{this.props.searchedBooks ? this.renderSearchedBooks(): ''}</div>
                </div>
            </div>


        );
    }
}

const mapStateToProps = state => {
    return {book: state.book.book, searchedBooks: state.book.searchedBooks};
};

export default connect(
    mapStateToProps,
    { getRandBook, getBooksBySearchTerm }
)(Landing);