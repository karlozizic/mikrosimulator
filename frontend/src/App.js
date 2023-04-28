import './App.css';
import React  from 'react';
import { Route, Routes } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import AppHeader from "./container/appHeader/appHeader";
import AppFooter from "./container/appFooter/appFooter";

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

function App() {
    return(
        <div className="App">
            <AppHeader/>
            <Routes>
                <Route path={"/"} element={<HomePageWrapper/>}/>

                <Route path={"/ekorazredi"} element={<EkorazredComponent/>}/>
                <Route path={"/add-ekorazred"} element={<CreateEkorazredComponent/>}/>
                <Route path={"/update-ekorazred/:id"} element={<UpdateEkorazredComponent/>}/>

                <Route path={"/dionice"} element={<DionicaComponent/>}/>
                <Route path={"/add-dionica"} element={<CreateDionicaComponent/>}/>
                <Route path={"/update-dionica/:id"} element={<UpdateDionicaComponent/>}/>

                <Route path={"/drzave"} element={<DrzavaComponent/>}/>
                <Route path={"/add-drzava"} element={<CreateDrzavaComponent/>}/>
                <Route path={"/update-drzava/:id"} element={<UpdateDrzavaComponent/>}/>

                {/*<Route path={"/kategorije"} element={<KategorijaComponent/>}/>*/}
                {/*<Route path={"/add-kategorija"} element={<CreateKategorijaComponent/>}/>*/}
                {/*<Route path={"/update-kategorija/:id"} element={<UpdateKategorijaComponent/>}/>*/}

                {/*<Route path={"/vozila"} element={<VoziloComponent/>}/>*/}
                {/*<Route path={"/add-vozilo"} element={<CreateVoziloComponent/>}/>*/}
                {/*<Route path={"/update-vozilo/:id"} element={<UpdateVoziloComponent/>}/>*/}

                {/*<Route path={"/naplatnetocke"} element={<NaplatnaTockaComponent/>}/>*/}
                {/*<Route path={"/add-naplatnatocka"} element={<CreateNaplatnaTockaComponent/>}/>*/}
                {/*<Route path={"/update-naplatnatocka/:id"} element={<UpdateNaplatnaTockaComponent/>}/>*/}

                {/*<Route path={"/uredaj"} element={<UredajComponent/>}/>*/}
                {/*<Route path={"/add-uredaj"} element={<CreateUredajComponent/>}/>*/}
                {/*<Route path={"/update-uredaj/:id"} element={<UpdateUredajComponent/>}/>*/}

            </Routes>
            <AppFooter/>
        </div>
    );
}

export default App;
