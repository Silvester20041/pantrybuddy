import { useEffect, useState } from "react";
import api from "../services/api";

function ExpiringSoon() {

    const [items, setItems] = useState([]);

    useEffect(() => {
        api.get("/expiring-soon")
            .then(response => setItems(response.data))
            .catch(error => console.error(error));
    }, []);

    return (
        <div style={{ padding: "20px" }}>
            <h1>⚠ Expiring Soon</h1>

            {items.length === 0 ? (
                <p>No items expiring soon.</p>
            ) : (
                <ul>
                    {items.map(item => (
                        <li key={item.id}>
                            {item.name} - {item.expiryDate}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default ExpiringSoon;