import React from "react";

function Ekorazred(props){
    const {id, naziv} = props.ekorazred; 
    return(
    <p>{id} {naziv}</p>
    );
}

export default Ekorazred;