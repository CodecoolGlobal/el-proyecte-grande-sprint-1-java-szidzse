import { Card, CardBody, CardFooter, Typography, Button } from "@material-tailwind/react";
import { GalleryWithCarousel } from "./GalleryWithCarousel.jsx";

export default function AccommodationCard() {
    const accommodationId = 1;

    return (
        <Card className="max-w-sm mx-auto shadow-cardHover rounded-4xl">
            <div className="w-full h-48 rounded-t-4xl overflow-hidden">
                <GalleryWithCarousel accommodationId={accommodationId} />
            </div>

            <CardBody className="p-4">
                <div className="mb-2">
                    <Typography variant="h5" className="text-primary font-semibold">
                        Cozy Studio Apartment
                    </Typography>
                    <Typography variant="small" className="text-gray-500">
                        Budapest, Hungary
                    </Typography>
                </div>

                <Typography className="text-gray-700 text-sm">
                    lorem ipsum etc
                </Typography>

                <Typography variant="h6" className="text-accent mt-4">
                    80 USD / night
                </Typography>
            </CardBody>

            <CardFooter className="flex justify-between items-center p-4">
                <Button size="sm" className="bg-primary text-white hover:bg-secondary">
                    See Details
                </Button>
                <Typography variant="small" className="text-gray-500">
                    ★ 4.8 (120)
                </Typography>
            </CardFooter>
        </Card>
    );
}
