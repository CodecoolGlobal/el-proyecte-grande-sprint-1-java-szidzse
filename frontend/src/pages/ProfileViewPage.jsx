import React, { useState, useEffect } from 'react';
import MemberProfile from '../components/forms/MemberProfilForm';
import { useAuth } from '../components/AuthProvider';

const fetchMemberData = async (userEmail) => {
    try {
        const response = await fetch(`/api/member/${userEmail}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log(data);
        return data;
    } catch (error) {
        console.error('Failed to fetch member data:', error);
        return null;
    }
};

const ProfilViewPage = () => {
    const [memberData, setMemberData] = useState(null);
    const { userEmail } = useAuth();

    useEffect(() => {
        if (userEmail) {
            const loadMemberData = async () => {
                const data = await fetchMemberData(userEmail);
                setMemberData(data);
            };
            loadMemberData();
        }
    }, [userEmail]);

    return (
        <div>
            {memberData ? (
                <MemberProfile memberData={memberData} />
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
};

export default ProfilViewPage;
