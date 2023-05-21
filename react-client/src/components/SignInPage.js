import React, {useContext, useEffect, useState} from "react";
import {GetHTTPRequestOptions, PostHttpRequestOptions} from "../utils/HTTPFunctions";
import EmailInput from "./inputs/EmailInput";
import PasswordInput from "./inputs/PasswordInput";
import Error from "../utils/Error";
import {useNavigate} from "react-router";
import {SetUserContext, UserContext} from "../App";
import ChatRoom from "./ChatRoom";
import {Navigate} from "react-router-dom";

export default function SignInPage(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [signInError, setSignInError] = useState(false);
    const [serviceError, setServiceError] = useState(false);
    const [successfulSignIn, setSuccessfulSignIn] = useState(false);

    const navigate = useNavigate();

    const userData = useContext(UserContext);
    const setUserData = useContext(SetUserContext);

    useEffect(()=>{

    }, [successfulSignIn])

    const userJoin = ()=>{
        let options = GetHTTPRequestOptions({userId: 1});

        console.log(JSON.stringify(options));
        fetch("http://localhost:8080/join/1", options)
            .then((response)=>response.json())
            .then((response)=>{userData(response); return response})
            .then(response=>console.log(response));
    }

    const sendSignupData = (e) => {
        e.preventDefault();

        var body = JSON.stringify({
            email:email,
            password:password
        });

        fetch("/signin", PostHttpRequestOptions(body))
            .then((response) => {
                console.log(response);
                setSignInError(false);
                setServiceError(false);
                if (response.status === 401){
                    setSignInError(true);
                }
                else if (response.status >= 500){
                    setServiceError(true);
                }
                else{
                    return response.json();
                }
            })
            .then((response)=>{
                setSuccessfulSignIn(true);
                setUserData(response);
            })
            .catch(error => console.log('error', error));
    }

    const handleErrors = () => {
        if (serviceError) return <Error message="service is unavailable now"></Error>
        else if (signInError) return <Error message="invalid email or password"></Error>
    }

    return (
        <div className="register">
            <EmailInput fieldName="email" className="textInput" onChangeFunction={ (e) => setEmail(e.target.value)}/>
            <PasswordInput fieldName="password" className="textInput" onChangeFunction={ (e) => setPassword(e.target.value)}/>
            <button onClick={sendSignupData}>submit</button>
            {handleErrors()}
            {successfulSignIn?<Navigate to="../chat"/>:""}
        </div>
    )
}