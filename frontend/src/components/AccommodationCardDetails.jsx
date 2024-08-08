import React from 'react'
import {Link} from "react-router-dom";

const AccommodationCard = ({id, name, description, roomNumber, pricePerNight, maxGuests}) => {
    return (
        <div className="card bg-base-100 w-96 shadow-xl">
            <figure>
                <img
                    src=""
                    alt='"image placeholder text"'/>
            </figure>
            <div className="card-body">
                <h2 className="card-title">{name}</h2>
                <p>{description}</p>
                <p>Price per night: ${pricePerNight}</p>
                <p>Room number: {roomNumber}</p>
                <p>Max guests: {maxGuests}</p>
                <div className="card-actions justify-end">
                    <Link to={"/"} className="btn btn-primary">Back</Link>
                </div>
            </div>
        </div>
    )
}
export default AccommodationCard
