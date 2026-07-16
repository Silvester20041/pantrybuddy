import { useEffect, useState } from "react";
import api from "../services/api";

function LowStock() {

    const [items, setItems] = useState([]);

    useEffect(() => {
        api.get("/low-stock")
            .then(response => setItems(response.data))
            .catch(error => console.error(error));
    }, []);

    return (
        <div style={{ padding: "20px" }}>
            <h1>⚠ Low Stock Items</h1>

            {items.map(item => (
                <div key={item.id}>
                    {item.name} - Qty: {item.quantity}
                </div>
            ))}
        </div>
    );
}

export default LowStock;