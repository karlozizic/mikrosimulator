import './App.css';
import React, { Component }  from 'react';
import { Route, Routes } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import AppHeader from "./container/appHeader/appHeader";

import HomePageWrapper from "./components/components/wrappers/HomePageWrapper";
import EkorazredComponent from "./container/ekorazredPage/EkorazredComponent";
import CreateEkorazredComponent from "./container/ekorazredPage/CreateEkorazredComponent";
// import UpdateEkorazredComponent from "./container/ekorazredPage/UpdateEkorazredComponent";

function App() {
    return(
        <div className="App">
            <AppHeader/>
            <Routes>
                <Route path={"/"} element={<HomePageWrapper/>}/>
                <Route path={"/ekorazredi"} element={<EkorazredComponent/>}/>
                <Route path={"/add-ekorazred"} element={<CreateEkorazredComponent/>}/>
                {/*<Route path={"/update-ekorazred/:id"} element={<UpdateEkorazredComponent/>}/>*/}
            </Routes>
        </div>
    );
}

export default App;
