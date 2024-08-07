import React from 'react'

const Card = ({name, description, roomNumber, pricePerNight, maxGuests}) => {
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
                <p>$ {pricePerNight}</p>
                <div className="card-actions justify-end">
                    <button className="btn btn-primary">See Details</button>
                </div>
            </div>
        </div>
    )
}
export default Card
