import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {log} from "typings/dist/support/cli";

class BookCard extends Component {

    render() {
        return (
            <div className="card m-3" style={{width: '18rem', height: '30rem'}} key={this.props.book.isbn}>
                <div className={"mx-auto"} style={{width: '150px', height: '200px'}}>
                    <img src={`http://covers.openlibrary.org/b/isbn/${this.props.book.isbn}-M.jpg`}
                         className="card-img-top" alt="cover-img"/>
                </div>
                <div className="card-body mt-5 h-100" style={{maxHeight: '200px'}}>
                    <h5 className="card-title">{this.props.book.title}</h5>
                    <div className={" overflow-hidden"} style={{maxHeight: '100px'}}>
                        <p className="card-text">{this.props.book.description}</p>
                    </div>
                    <div className={"mt-auto"}>
                        <Link className="btn btn-primary btn-block"
                              to={{pathname: `book/${this.props.book.isbn}`, state: {book: this.props.book}}}>View</Link>
                    </div>
                </div>
            </div>
        );
    }
}

export default BookCard;