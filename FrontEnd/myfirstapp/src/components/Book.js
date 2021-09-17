import React, { Component } from "react";

import { connect } from "react-redux";
import {getBook} from "../actions/bookActions";
import {createListing, getISBNListing} from "../actions/listingActions"
import {withRouter} from "react-router-dom";

class Book extends Component {

    state = {
        price : undefined,
        condition: undefined
    }

    componentDidMount(){
        this.props.getISBNListing(this.props.location.state.book.isbn);
    }

    onListingCreate = (e) => {
        e.preventDefault();
        const order = {
            condition: this.state.condition,
            price: this.state.price,
            isbn: this.props.location.state.book.isbn,
            isPublisher: false,
            isSold: false,
            userId: "",
            orderId:""
        }
        this.props.createListing(order);
        setTimeout(function(){ alert(window.location.reload()); }, 1500);
    }

    onPriceChange = (e) => {
        this.setState({price: parseFloat(e.target.value)});
    }

    onConditionChange = (e) => {
        this.setState({condition: e.target.value})
    }

    renderBookReviews(){
        const book = this.props.location.state.book;
        return book.bookReviewList.map(review => {
            return (
                <div style={{border: '1px grey solid'}} className={"my-3 p-3"}>
                    <p>User id: {review.user_id}</p>
                    <p>Posted on {review.create_at}</p>
                    <p>Star: {review.star}</p>
                    <p>content {review.content}</p>
                </div>
            )
        })
    }

    onBuyCLick = e => {

    }

    renderListings(){
        return this.props.listings.map(listing => {
            return (
                <div style={{border: '1px grey solid'}} className={"my-3 p-3 container"}>
                    <div className={"row"}>
                        <div className={"col"}><p>User id: {listing.userId}</p>
                            <p>Condition: {listing.condition}</p>
                            <p>Price {listing.price}</p>
                            <p>Added: {listing.create_At}</p>
                        </div>
                        <div className={"col"}>
                            <button type="button" className="btn btn-primary">Buy</button>
                        </div>
                    </div>
                </div>
            )
        })
    }

    render() {
        const book = this.props.location.state.book;
        const bookImageUrl = `http://covers.openlibrary.org/b/isbn/${book.isbn}-M.jpg`;
        return (
            <div className="container">
                <div className="row">
                    <div className="col-4">
                        <img src={bookImageUrl} className={"img-fluid"} alt={"book cover"} />
                    </div>
                    <div className="col">
                        <p className="fw-bold display-4">{book.title}</p>
                        <div className={"my-3"}>
                            <span className={"fs-5 mr-5"}>Genre</span>
                            <button type="button" className="btn btn-primary" disabled>{book.genre}</button>
                        </div>
                        <p>Written By: {book.author} </p>
                        <div className={"book-description"}>
                            {book.description}
                        </div>
                    </div>
                </div>
                <div className={"row"}>
                    <div className={"col-8"}>
                        <p className="display-5">Reviews</p>
                        <div className={"overflow-auto"} style={{maxHeight: '500px'}}>
                            {this.renderBookReviews()}
                        </div>
                    </div>
                    <div className={"col"}>
                        <span className="display-5">Listings</span>
                        <span>
                            <button type="button" className="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">Create</button>
                        </span>
                        <div>
                            {this.props.listings ? this.renderListings() : ''}
                        </div>
                    </div>
                </div>

                {/* Modal window for create listing */}
                <div className="modal fade" id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel">Create Listing</h5>
                                <button type="button" className="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div className="modal-body">
                                <p>Condition</p>
                                <select className="form-select" aria-label="Default select example" onChange={e => this.onConditionChange(e)}>
                                    <option value="NEW">New</option>
                                    <option value="USED" selected>Used</option>
                                </select>
                                <div className="mb-3">
                                    <label htmlFor="price" className="form-label">Price</label>
                                    <input type="number" className="form-control" id="price" min={1} onChange={e => this.onPriceChange(e)} />
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close
                                </button>
                                <button type="button" className="btn btn-primary" data-bs-dismiss="modal" onClick={e => this.onListingCreate(e)}>Create</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


const mapStateToProps = state => {
   return {book: state.book.book, listings: state.listing.listings};
};

export default withRouter(connect(mapStateToProps,{ getBook, createListing, getISBNListing })(Book));
