import React, {Component} from "react";
import { connect } from "react-redux";
import {getISBNListing} from "../actions/listingActions";
import {getBook} from "../actions/bookActions";

class Listing extends Component {

    componentDidMount(){
        this.props.getBook(this.props.match.params.id)
    this.props.getISBNListing(this.props.match.params.id)
    }
    renderListing(){
        if (this.props.listing) {
            return(
                <div className="col-md-12 text-center" style={{height: '800px' }} >
                    <h1 className="display-4 mb-4">
                        {this.props.book.title} available listings
                    </h1>

                    <hr/>
                <ul className="list-group">
                    {this.props.listing.map(function (listing, index) {
                        if(!listing.sold) {
                            return <div className={"center"} style={{width: '200px'}}>
                                <li className="list-group-item">{listing.condition}: ${listing.price}</li>

                            </div>

                        }
                        }
                    )
                    }
                </ul>
                </div>
                    )


         }else return (
            <div className="col-md-12 text-center" style={{height: '800px' }} >
                <h1 className="display-3 mb-4">No listings found for this book </h1>


            </div>)
    }
    render() {

        return (
            this.renderListing()

        );
    }
}

const mapStateToProps = state => {
    return {listing: state.listing.listing,
    book: state.book.book[0]};
};

export default connect(
    mapStateToProps,
    {getISBNListing, getBook}
)(Listing);