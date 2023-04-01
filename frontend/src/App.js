import './App.css';
import React from "react";
import { Route, Routes } from "react-router-dom";
import EkorazredPageWrapper from "./components/components/wrappers/EkorazredPageWrapper";

function App() {
    return(
        <div className="App">
            <Routes>
                <Route path={"/ekorazredi"} element={<EkorazredPageWrapper/>}/>
            </Routes>
        </div>
    );
}

export default App;
