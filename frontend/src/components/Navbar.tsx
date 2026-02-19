import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

const Navbar = () => {
    const { user, logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate("/");
    };

    return (
        <nav style={{ padding: "10px", background: "#eee" }}>
            <Link to="/orders">Orders</Link> |{" "}
            <Link to="/profile">Profile</Link> |{" "}
            <span>{user?.name}</span> |{" "}
            <button onClick={handleLogout}>Logout</button>
        </nav>
    );
};

export default Navbar;
