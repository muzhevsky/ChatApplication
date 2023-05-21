import React, {useContext, useEffect, useState} from 'react'
import {over} from 'stompjs';
import SockJS from 'sockjs-client';
import {GetHTTPRequestOptions, PostHttpRequestOptions} from "../utils/HTTPFunctions";
import {Navigate} from "react-router-dom";
import {SetUserContext, UserContext} from "../App";

export default function ChatRoom() {
    const [chatData, setChatData] = useState([]);
    const [authorized, setAuthorized] = useState(false);
    const [update, setUpdate] = useState(0);

    const userData = useContext(UserContext);
    const setUserData = useContext(SetUserContext);

    let stompClient;

    useEffect(() => {
        let options = GetHTTPRequestOptions({userId: userData.userId});

        console.log(JSON.stringify(options));
        fetch("http://localhost:8080/join/1", options)
            .then((response) => response.json())
            .then(response => console.log(response));

        setAuthorized(true);
        connect();
    }, [userData]);

    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/chat');
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    }

    const onConnected = () => {
        stompClient.subscribe('/user/topic/messages', onServerMessage);
        console.log(stompClient);
    }

    const onServerMessage = (payload) => {
        console.log(payload);
        let payloadData = JSON.parse(payload.body);
        chatData.push(payloadData);
        console.log(payloadData);
        setUpdate(update+1);
    }

    const onError = (err) => {
        console.log(err);
    }

    const handleMessage = (event) => {
        const {value} = event.target;
        setUserData({...userData, "message": value});
    }
    const sendValue = (message) => {
        if (stompClient) {
            let chatMessage = {
                senderId: userData.userId,
                text: message
            };
            console.log(chatMessage);
            stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
            setUserData({...userData, "message": ""});
        }
    }

    function render() {
        if (!authorized) return "";
        let messages = [];
        chatData.forEach(element => messages.push(<li>{element.text}</li>));

        console.log(messages);

        let render = userData.id > 0 ?
            <div className="container">
                <div className="chat-box">
                    <div className="chat-content">
                        <ul className="chat-messages">
                            {messages}
                        </ul>
                        <div className="send-message">
                            <input type="text" className="input-message" placeholder="enter the message"
                                   value={userData.message} onChange={handleMessage}/>
                            <button type="button" className="send-button" onClick={() => {
                                sendValue(userData.message)
                            }}>send
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            : <Navigate to="../signin"/>
        return render;
    }


    return render();
}
