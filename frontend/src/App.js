import './App.css';
import React from "react";
import { Route, Routes } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import AppHeader from "./container/appHeader/appHeader";

import HomePageWrapper from "./components/components/wrappers/HomePageWrapper";
import EkorazredPageWrapper from "./components/components/wrappers/EkorazredPageWrapper";

function App() {
    return(
        <div className="App">
            <AppHeader/>
            <Routes>
                <Route path={"/"} element={<HomePageWrapper/>}/>
                <Route path={"/ekorazredi"} element={<EkorazredPageWrapper/>}/>
            </Routes>
        </div>
    );
}

export default App;
