import { useEffect, useState } from "react";
import { getOrders, createOrder } from "../api/orderService";
import type { Order } from "../types/order";
import LoadingSpinner from "../components/LoadingSpinner";
import Navbar from "../components/Navbar";

const OrdersPage = () => {
    const [orders, setOrders] = useState<Order[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    const [productName, setProductName] = useState("");
    const [price, setPrice] = useState(0);
    const [quantity, setQuantity] = useState(1);

    const fetchOrders = async () => {
        try {
            const data = await getOrders();
            setOrders(data);
        } catch {
            setError("Failed to load orders");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchOrders();
    }, []);

    const handleCreateOrder = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            await createOrder(productName, price, quantity);
            setProductName("");
            setPrice(0);
            setQuantity(1);
            fetchOrders();
        } catch {
            setError("Failed to create order");
        }
    };

    return (
        <div>
            <Navbar />
            <h2 style={{ padding: "20px" }}>My Orders</h2>

            {loading && <LoadingSpinner />}
            {error && <p style={{ color: "red" }}>{error}</p>}

            <ul>
                {orders.map((order) => (
                    <li key={order.id}>
                        {order.productName} - {order.quantity} × ${order.price}
                    </li>
                ))}
            </ul>

            <h3>Create Order</h3>

            <form onSubmit={handleCreateOrder}>
                <input
                    placeholder="Product Name"
                    value={productName}
                    onChange={(e) => setProductName(e.target.value)}
                    required
                />
                <input
                    type="number"
                    placeholder="Price"
                    value={price}
                    onChange={(e) => setPrice(Number(e.target.value))}
                    required
                />
                <input
                    type="number"
                    placeholder="Quantity"
                    value={quantity}
                    onChange={(e) => setQuantity(Number(e.target.value))}
                    required
                />
                <button type="submit">Create</button>
            </form>
        </div>
    );
};

export default OrdersPage;
