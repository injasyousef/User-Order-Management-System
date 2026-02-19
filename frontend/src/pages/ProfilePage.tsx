import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { getCurrentUser } from "../api/userService";



const ProfilePage = () => {
    const [user, setUser] = useState<any>(null);

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const data = await getCurrentUser();
                setUser(data);
            } catch {
                console.log("Using mock profile data");
            }
        };

        fetchUser();
    }, []);

    return (
        <div>
            <Navbar />
            <div style={{ padding: "20px" }}>
                <h2>Profile</h2>
                <p><strong>Name:</strong> {user?.name}</p>
                <p><strong>Email:</strong> {user?.email}</p>
                <p><strong>Role:</strong> {user?.role}</p>
            </div>
        </div>
    );
};

export default ProfilePage;
