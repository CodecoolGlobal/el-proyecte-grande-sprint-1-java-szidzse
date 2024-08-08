import React from 'react'
import {Link} from "react-router-dom";

const AccommodationCard = ({id, pricePerNight, location}) => {
    return (
        <div className="card bg-base-100 w-96 shadow-xl">
            <figure>
                <img
                    src=""
                    alt='"image placeholder text"'/>
            </figure>
            <div className="card-body">
                <p>{location.city}, {location.state ?? ""}, {location.country}</p>
                <p>${pricePerNight} / night</p>
                <div className="card-actions justify-end">
                    <Link to={`/accommodation/${id}`} className="btn btn-primary">See Details</Link>
                </div>
            </div>
        </div>
    )
}
export default AccommodationCard
