import React from "react";
import './Card.css';

function Card(props) {
  const {children, title} = props;

  return (
    <div className="EkorazredList">
      {title && <h2>{title}</h2>}
      {children}
    </div>
  )
}

export default Card;
