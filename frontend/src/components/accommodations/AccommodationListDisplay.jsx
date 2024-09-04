import React from 'react';
import AccommodationCard from './AccommodationCard';
import { InformationCircleIcon } from '@heroicons/react/24/outline'; // Heroicons v2 importálás
import { Typography } from '@material-tailwind/react'; // Material Tailwind Typography importálása

const AccommodationListDisplay = ({ accommodationList }) => {
  return (
    <>
      {accommodationList.length > 0 ? (
        accommodationList.map((accommodation) => (
          <AccommodationCard key={accommodation.accommodationId} accommodation={accommodation} />
        ))
      ) : (
        <div className="flex flex-col items-center justify-center py-8">
          <InformationCircleIcon className="w-16 h-16 text-gray-400" />
          <Typography variant="h6" className="text-gray-700 mt-4">
          No Results Found
          </Typography>
          <Typography variant="body2" className="text-gray-500 mt-2 text-center">
          Unfortunately, we couldn't find any accommodations that match your search. Please try different keywords!          </Typography>
        </div>
      )}
    </>
  );
};

export default AccommodationListDisplay;
