import { Card, CardBody, CardFooter, Typography, Button } from "@material-tailwind/react";
import { GalleryWithCarousel } from "./GalleryWithCarousel.jsx";
import {useNavigate} from "react-router-dom";

export default function AccommodationCard(props) {
    const {accommodationId, name, description, pricePerNight, location} = props;
    const navigate = useNavigate();

    const handleSeeDetailsClick = () => {
        navigate(`/accommodation/${accommodationId}`)
    }

    return (
        <Card className="max-w-sm mx-auto shadow-cardHover rounded-4xl">
            <div className="w-full h-48 rounded-t-4xl overflow-hidden">
                <GalleryWithCarousel accommodationId={accommodationId} />
            </div>

            <CardBody className="p-4">
                <div className="mb-2">
                    <Typography variant="h5" className="text-primary font-semibold">
                        {name}
                    </Typography>
                    <Typography variant="small" className="text-gray-500">
                        {location.city}, {location.state ? location.state : ""}, {location.country}
                    </Typography>
                </div>

                <Typography className="text-gray-700 text-sm">
                    {description}
                </Typography>

                <Typography variant="h6" className="text-accent mt-4">
                    {pricePerNight} USD / night
                </Typography>
            </CardBody>

            <CardFooter className="flex justify-between items-center p-4">
                <Button onClick={handleSeeDetailsClick} size="sm" className="bg-primary text-white hover:bg-secondary">
                    See Details
                </Button>
                <Typography variant="small" className="text-gray-500">
                    ★ 4.8 (120)
                </Typography>
            </CardFooter>
        </Card>
    );
}
