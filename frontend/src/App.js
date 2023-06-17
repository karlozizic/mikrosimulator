import './App.css';
import React  from 'react';
import { Route, Routes } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import AppHeader from "./container/appHeader/appHeader";

import HomePageWrapper from "./components/components/wrappers/HomePageWrapper";
import EkorazredComponent from "./container/ekorazredPage/EkorazredComponent";
import CreateEkorazredComponent from "./container/ekorazredPage/CreateEkorazredComponent";
import UpdateEkorazredComponent from "./container/ekorazredPage/UpdateEkorazredComponent";

import DionicaComponent from "./container/dionicaPage/DionicaComponent";
import CreateDionicaComponent from "./container/dionicaPage/CreateDionicaComponent";
import UpdateDionicaComponent from "./container/dionicaPage/UpdateDionicaComponent";

import DrzavaComponent from "./container/drzavaPage/DrzavaComponent";
import CreateDrzavaComponent from "./container/drzavaPage/CreateDrzavaComponent";
import UpdateDrzavaComponent from "./container/drzavaPage/UpdateDrzavaComponent";

import KategorijaComponent from "./container/kategorijaPage/KategorijaComponent";
import CreateKategorijaComponent from "./container/kategorijaPage/CreateKategorijaComponent";
import UpdateKategorijaComponent from "./container/kategorijaPage/UpdateKategorijaComponent";

import VoziloComponent from "./container/voziloPage/VoziloComponent";
import CreateVoziloComponent from "./container/voziloPage/CreateVoziloComponent";
import UpdateVoziloComponent from "./container/voziloPage/UpdateVoziloComponent";

import UredajComponent from "./container/uredajPage/UredajComponent";
import CreateUredajComponent from "./container/uredajPage/CreateUredajComponent";
import UpdateUredajComponent from "./container/uredajPage/UpdateUredajComponent";

import NaplatnaTockaComponent from "./container/naplatnaTockaPage/NaplatnaTockaComponent";
import CreateNaplatnaTockaComponent from "./container/naplatnaTockaPage/CreateNaplatnaTockaComponent";
import UpdateNaplatnaTockaComponent from "./container/naplatnaTockaPage/UpdateNaplatnaTockaComponent";

import Mikrosimulator from "./container/mikrosimulatorPage/Mikrosimulator";

function App() {
    return(
        <div className="App">
            <AppHeader/>
            <Routes>
                <Route path={"/"} element={<HomePageWrapper/>}/>

                <Route path={"/ekorazredi"} element={<EkorazredComponent/>}/>
                <Route path={"/add-ekorazred"} element={<CreateEkorazredComponent/>}/>
                <Route path={"/update-ekorazred/:id"} element={<UpdateEkorazredComponent/>}/>

                <Route path={"/drzave"} element={<DrzavaComponent/>}/>
                <Route path={"/add-drzava"} element={<CreateDrzavaComponent/>}/>
                <Route path={"/update-drzava/:id"} element={<UpdateDrzavaComponent/>}/>

                <Route path={"/kategorije"} element={<KategorijaComponent/>}/>
                <Route path={"/add-kategorija"} element={<CreateKategorijaComponent/>}/>
                <Route path={"/update-kategorija/:id"} element={<UpdateKategorijaComponent/>}/>

                <Route path={"/vozila"} element={<VoziloComponent/>}/>
                <Route path={"/add-vozilo"} element={<CreateVoziloComponent/>}/>
                <Route path={"/update-vozilo/:id"} element={<UpdateVoziloComponent/>}/>

                <Route path={"/uredaji"} element={<UredajComponent/>}/>
                <Route path={"/add-uredaj"} element={<CreateUredajComponent/>}/>
                <Route path={"/update-uredaj/:id"} element={<UpdateUredajComponent/>}/>

                <Route path={"/naplatnetocke"} element={<NaplatnaTockaComponent/>}/>
                <Route path={"/add-naplatnatocka"} element={<CreateNaplatnaTockaComponent/>}/>
                <Route path={"/update-naplatnatocka/:id"} element={<UpdateNaplatnaTockaComponent/>}/>

                <Route path={"/dionice"} element={<DionicaComponent/>}/>
                <Route path={"/add-dionica"} element={<CreateDionicaComponent/>}/>
                <Route path={"/update-dionica/:id"} element={<UpdateDionicaComponent/>}/>

                <Route path={"/simulator"} element={<Mikrosimulator/>}/>

            </Routes>
        </div>
    );
}

export default App;
