import React, {useState} from "react";
export function FeaturedImageGallery({ images }) {
    const [active, setActive] = useState(images.length > 0 ? images[0] : "")

    const formatImage = (base64String) => `data:image/jpeg;base64,${base64String}`;

    return (
        <div className="grid gap-4">
            <div>
                <img
                    className="h-auto w-full max-w-full rounded-lg object-cover object-center md:h-[480px]"
                    src={formatImage(active)}
                    alt="Accommodation image"
                />
            </div>
            <div className="grid grid-cols-5 gap-4">
                {images.map((base64Image, index) => (
                    <div key={index}>
                        <img
                            onClick={() => setActive(base64Image)}
                            src={formatImage(base64Image)}
                            className="h-20 max-w-full cursor-pointer rounded-lg object-cover object-center"
                            alt={`Gallery image ${index + 1}`}
                        />
                    </div>
                ))}
            </div>
        </div>
    );
}