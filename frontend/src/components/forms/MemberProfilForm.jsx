import React, { useState } from 'react';
import { Card, Typography, Button } from "@material-tailwind/react";

const MemberProfile = ({ memberData }) => {
  const [showAccommodations, setShowAccommodations] = useState(false);

  const handleToggleAccommodations = () => {
    setShowAccommodations(prevState => !prevState);
  };

  return (
    <Card color="transparent" shadow={false}>
      <Typography variant="h4" color="blue-gray">
        Profile Details
      </Typography>

      <div className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
        <div className="mb-1 flex flex-col gap-6">
          <Typography variant="h6" color="blue-gray" className="-mb-3">
            First Name
          </Typography>
          <Typography
            size="lg"
            className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
          >
            {memberData.firstName}
          </Typography>

          <Typography variant="h6" color="blue-gray" className="-mb-3">
            Last Name
          </Typography>
          <Typography
            size="lg"
            className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
          >
            {memberData.lastName}
          </Typography>

          <Typography variant="h6" color="blue-gray" className="-mb-3">
            Email
          </Typography>
          <Typography
            size="lg"
            className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
          >
            {memberData.email}
          </Typography>

          <Typography variant="h6" color="blue-gray" className="-mb-3">
            Phone Number
          </Typography>
          <Typography
            size="lg"
            className=" !border-t-blue-gray-200 focus:!border-t-gray-900"
          >
            {memberData.phoneNumber}
          </Typography>

          {/* Gomb a szálláshelyek listájának megjelenítéséhez */}
          <Button onClick={handleToggleAccommodations} color="blue-gray" className="mt-4">
            {showAccommodations ? 'Hide Accommodations' : 'Show Accommodations'}
          </Button>

          {showAccommodations && memberData.accommodations && (
            <div className="mt-4">
              <Typography variant="h6" color="blue-gray">
                Accommodations
              </Typography>
              <ul className="list-disc pl-5">
                {memberData.accommodations.map((acc, index) => (
                  <li key={index}>
                    <Typography size="lg" color="gray">
                      <div>{`Name: ${acc.name}`}</div>
                      <div>{`Description: ${acc.description}`}</div>
                      <div>{`Room Number: ${acc.roomNumber}`}</div>
                      <div>{`Price Per Night: ${acc.pricePerNight}`}</div>
                      <div>{`Max Guests: ${acc.maxGuests}`}</div>
                      <div>{`Country: ${acc.location.country}`}</div>
                      <div>{`City: ${acc.location.city}`}</div>
                    </Typography>
                  </li>
                ))}
              </ul>
            </div>
          )}
        </div>
      </div>
    </Card>
  );
};

export default MemberProfile;
