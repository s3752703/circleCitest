import React, {Component} from 'react'

class Footer extends Component {
    render() {
        return (


            <div>

                <div class="container-fluid p-3 bg-dark text-white fixed-bottom">
                    <ul class="nav justify-content-center border-bottom pb-3 mb-3">
                        <li class="nav-item">
                            <a href="about" class="nav-link" style={{color: "white"}}>
                                About us
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="contact" class="nav-link" style={{color: "white"}}>
                                Contact
                            </a>
                        </li>
                    </ul>
                </div>
            </div>


        )
    }
}

export default Footer;