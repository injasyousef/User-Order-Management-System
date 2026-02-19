import api from "./axios";
import type { Order } from "../types/order";

export const getOrders = async (): Promise<Order[]> => {
    const response = await api.get("/api/orders");
    return response.data;
};

export const createOrder = async (
    productName: string,
    price: number,
    quantity: number
) => {
    const response = await api.post("/api/orders", {
        productName,
        price,
        quantity,
    });

    return response.data;
};
