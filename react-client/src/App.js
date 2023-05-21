import React, {createContext, useContext, useState} from 'react'
import ChatRoom from './components/ChatRoom'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import SignInPage from "./components/SignInPage";

export const UserContext = createContext({});
export const SetUserContext = createContext((prevState) => {});
const App = () => {

    const [userData, setUserData] = useState({});

    return (
        <UserContext.Provider value={userData}>
            <SetUserContext.Provider value={setUserData}>
              <BrowserRouter>
                  <Routes>
                      <Route path="/chat" element={<ChatRoom/>}/>
                      <Route path="/signin" element={<SignInPage/>}/>
                  </Routes>
              </BrowserRouter>
            </SetUserContext.Provider>
        </UserContext.Provider>
    )
}

export default App;

