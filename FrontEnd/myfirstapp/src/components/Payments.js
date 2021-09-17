import Axios from 'axios';
import React, { useEffect, useState } from 'react'

const Payments = (props) => {

    const [paymentPayload, setPaymentPayload] = useState();
    const [redirectForPaymentURL, setRedirectForPaymentURL] = useState();
    const [paymentInfo, setPaymentInfo] = useState()

    
    const parseSuccessfullPaymentPayload = (res) => {
        const data = res.slice(1).split("&");
        const map = {}
        for (let i = 0; i < data.length; i++) {
            const temp = data[i].split("=");
            map[temp[0]] = temp[1];
        }
        return map;
    }

    useEffect(() => {
        setPaymentPayload({
            price: 100,
            currency: "AUD",
            method: "paypal",
            intent: "order",
            description: "Test"
        })
    }, [])

    useEffect(() => {
        if( props.history.location.search) {
            setPaymentInfo(parseSuccessfullPaymentPayload(props.history.location.search))
        }
    }, [paymentInfo])

    onsubmit = async (e) => {
        e.preventDefault();
        const response = await Axios.post('http://localhost:8082/api/payments/pay', paymentPayload);
        setRedirectForPaymentURL(response.data.slice(9,));
    }

    return (
        <div>
            <form>
                <div class="mb-3">
                    <label for="priceInput" class="form-label">Price</label>
                    <input type="number" class="form-control" id="priceInput" aria-describedby="priceHelp"  value={100} />
                </div>
                <div class="mb-3">
                    <label for="currencyInput" class="form-label">Currency</label>
                    <input type="text" class="form-control" id="currencyInput" aria-describedby="priceHelp"  value="AUD"/>
                </div>
                <div class="mb-3">
                    <label for="methodInput" class="form-label">Method</label>
                    <input type="text" class="form-control" id="methodInput" aria-describedby="priceHelp"  value="paypal"/>
                </div>
                <div class="mb-3">
                    <label for="intentInput" class="form-label">Intent</label>
                    <input type="text" class="form-control" id="intentInput" aria-describedby="priceHelp" value="order" />
                </div>
                <div class="mb-3">
                    <label for="descriptionInput" class="form-label">Description</label>
                    <input type="text" class="form-control" id="descriptionInput" aria-describedby="priceHelp" value="Test"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <a href={redirectForPaymentURL} onClick={() => window.location.replace(redirectForPaymentURL)}>{redirectForPaymentURL}</a>
            {
                paymentInfo ?
                <div>
                    <p style={{ color:"green"}}>Payment Successful</p>
                    <table class="table w-50">
                        <thead>
                            <tr>
                                <th>Payment ID</th>
                                <th>Payer ID</th>
                                <th>Token</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td scope="row">{paymentInfo.paymentId}</td>
                                <td>{paymentInfo.PayerID}</td>
                                <td>{paymentInfo.token}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>:
                <p style={{ color:"red"}}>Payment not made</p>
            }
        </div>
    )
}

export default Payments
