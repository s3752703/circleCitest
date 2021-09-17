import React, {Component} from 'react'
import {withRouter} from 'react-router-dom';
import {logout} from "../../actions/securityActions";

import {connect} from "react-redux";

import PropTypes from "prop-types";
import {getBooksBySearchTerm} from "../../actions/bookActions";


class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchType: "Title",
            search: null,
            term: ''
        }

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam]: val});
    }

    handleSubmit(e) {
        this.props.history.push(`/search/${this.state.searchType}/${this.state.search}`)
    }
    onSearchTermChange = e => {
        this.setState({term : e.target.value})
    }

    onSearchSubmit = e => {
        e.preventDefault();
        this.props.getBooksBySearchTerm(this.state.term.toLowerCase());
    }

    onResetClick = e => {
        e.preventDefault();
        window.location.reload();
    }

    render() {

        const {validToken} = this.props.security;
        const guestLinks = (
            <ul className="navbar-nav">
                <li className="nav-item ml-auto">
                    <a className="nav-link" href="http://localhost:3000/orders">
                        Orders
                    </a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="http://localhost:3000/login">
                        Login
                    </a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="http://localhost:3000/register">
                        Sign Up
                    </a>
                </li>
            </ul>
        );
        const userLinks = (
            <ul className="navbar-nav">
                <li className="nav-item ml-auto">
                    <a href="http://localhost:3000/login" className="nav-link" onClick={logout()}>
                        Log Out
                    </a>
                </li>
                <li className="nav-item">
                    <a href="/" className="nav-link">
                        Profile
                    </a>
                </li>
                <li className="nav-item">
                    <a href="/" className="nav-link">
                        Cart
                    </a>
                </li>
            </ul>
        );

        return (
            <div className={"container-fluid navbar navbar-expand-lg navbar-dark bg-dark"}>
                <div className={"row w-100"}>
                    <div className={"col"}>
                        <a className="navbar-brand" href="/">Bookeroo</a>
                    </div>
                    <div className={"col-6 w-50 mx-auto"}>
                        {
                            this.props.location.pathname == '/'?
                                <form className="d-flex">
                                    <input  className="form-control me-2" type="search" placeholder="Search by title, author, ISBN or genre..."
                                            aria-label="Search" onChange={e => this.onSearchTermChange(e)} />
                                    <button className="btn btn-outline-success" type="submit" onClick={e => this.onSearchSubmit(e)}>Search</button>
                                    <button className="btn btn-outline-danger ml-1" type="button" onClick={e => this.onResetClick(e)}>Reset</button>
                                </form>:
                                ''
                        }
                    </div>
                    <div className={"col"}>
                        {validToken ? userLinks : guestLinks}
                    </div>
                </div>
            </div>

            )
            }
            }

            Header.propTypes = {
            security: PropTypes.object.isRequired
        };

            function mapStateToProps(state) {
            return {
            security: state.security,
                searchedBooks: state.book.searchedBooks
        }

        };

            export default withRouter(connect(mapStateToProps, {getBooksBySearchTerm})(Header))

