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
    const [price, setPrice] = useState<number>(0);
    const [quantity, setQuantity] = useState<number>(1);

    const fetchOrders = async () => {
        try {
            const data = await getOrders();
            setOrders(data);
        } catch {
            setError("Something Wrong");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchOrders();
    }, []);

    const handleCreateOrder = async (e: React.FormEvent) => {
        e.preventDefault();

        const newOrder: Order = {
            id: Date.now(),
            productName,
            price,
            quantity,
            orderDate: new Date().toISOString(),
        };

        try {
            await createOrder(productName, price, quantity);
            fetchOrders();
        } catch {
            setOrders((prev) => [...prev, newOrder]);
        }

        setProductName("");
        setPrice(0);
        setQuantity(1);
    };

    return (
        <div>
            <Navbar />

            <div style={{ padding: "30px" }}>
                <h2>My Orders</h2>

                {loading && <LoadingSpinner />}
                {error && (
                    <p style={{ color: "orange", marginBottom: "15px" }}>
                        {error}
                    </p>
                )}

                <div
                    style={{
                        display: "flex",
                        gap: "40px",
                        alignItems: "flex-start",
                    }}
                >
                    <div style={{ flex: 1 }}>
                        <h3>Order List</h3>

                        {orders.length === 0 && !loading && (
                            <p>No orders yet.</p>
                        )}

                        {orders.map((order) => (
                            <div
                                key={order.id}
                                style={{
                                    border: "1px solid #ddd",
                                    padding: "15px",
                                    marginBottom: "10px",
                                    borderRadius: "6px",
                                }}
                            >
                                <h4>{order.productName}</h4>
                                <p>Quantity: {order.quantity}</p>
                                <p>Price: ${order.price}</p>
                                <p>
                                    Total: $
                                    {order.price * order.quantity}
                                </p>
                                <p
                                    style={{
                                        fontSize: "12px",
                                        color: "gray",
                                    }}
                                >
                                    {new Date(
                                        order.orderDate
                                    ).toLocaleString()}
                                </p>
                            </div>
                        ))}
                    </div>

                    <div
                        style={{
                            flex: 1,
                            maxWidth: "400px",
                            border: "1px solid #ddd",
                            padding: "20px",
                            borderRadius: "8px",
                        }}
                    >
                        <h3>Create Order</h3>

                        <form onSubmit={handleCreateOrder}>
                            <div style={{ marginBottom: "15px" }}>
                                <label
                                    style={{
                                        display: "block",
                                        marginBottom: "5px",
                                        fontWeight: "bold",
                                    }}
                                >
                                    Product Name
                                </label>
                                <input
                                    type="text"
                                    placeholder="Enter product name"
                                    value={productName}
                                    onChange={(e) =>
                                        setProductName(e.target.value)
                                    }
                                    required
                                    style={{
                                        width: "100%",
                                        padding: "8px",
                                    }}
                                />
                            </div>

                            <div style={{ marginBottom: "15px" }}>
                                <label
                                    style={{
                                        display: "block",
                                        marginBottom: "5px",
                                        fontWeight: "bold",
                                    }}
                                >
                                    Price ($)
                                </label>
                                <input
                                    type="number"
                                    placeholder="Enter price"
                                    value={price}
                                    onChange={(e) =>
                                        setPrice(Number(e.target.value))
                                    }
                                    required
                                    style={{
                                        width: "100%",
                                        padding: "8px",
                                    }}
                                />
                            </div>

                            <div style={{ marginBottom: "20px" }}>
                                <label
                                    style={{
                                        display: "block",
                                        marginBottom: "5px",
                                        fontWeight: "bold",
                                    }}
                                >
                                    Quantity
                                </label>
                                <input
                                    type="number"
                                    placeholder="Enter quantity"
                                    value={quantity}
                                    onChange={(e) =>
                                        setQuantity(Number(e.target.value))
                                    }
                                    required
                                    style={{
                                        width: "100%",
                                        padding: "8px",
                                    }}
                                />
                            </div>

                            <button
                                type="submit"
                                style={{
                                    padding: "10px 20px",
                                    width: "100%",
                                    cursor: "pointer",
                                }}
                            >
                                Create Order
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default OrdersPage;
