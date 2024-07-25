import React, {useEffect, useState} from 'react'
import AccommodationContainer from "../../components/Container/AccommodationContainer.jsx";

const AccommodationsPage = () => {
    const [accommodations, setAccommodations] = useState([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("/api/accommodation/all")
                const data = await response.json()
                setAccommodations(data)
                console.log(data)
            } catch (error) {
                console.log("Error fetching data: ", error)
            } finally {
                setLoading(false)
            }
        }
        fetchData()
    }, [])

    if (loading) {
        return <div>Loading...</div>
    }

    return (
        <div>
            <ul>
                {accommodations && accommodations.map((accommodation, index) => {
                    <div key={index}>
                        <AccommodationContainer accommodation={accommodation} />
                    </div>
                })}
            </ul>
        </div>
    )
}
export default AccommodationsPage
