import { useEffect, useState } from "react";
import api from "../services/api";

function PantryItems() {

    const [items, setItems] = useState([]);
    const [search, setSearch] = useState("");
    const [category, setCategory] = useState("All");

    const [showModal, setShowModal] = useState(false);
    const [selectedItem, setSelectedItem] = useState(null);

    useEffect(() => {
        api.get("/items")
            .then(response => {
                setItems(response.data);
            })
            .catch(error => console.error(error));
    }, []);

    const deleteItem = (id) => {

        api.delete(`/items/${id}`)
            .then(() => {

                setItems(
                    items.filter(item => item.id !== id)
                );

            })
            .catch(error => console.error(error));
    };

    const openEditModal = (item) => {
        setSelectedItem(item);
        setShowModal(true);
    };

    const updateItem = () => {

        api.put(`/items/${selectedItem.id}`, selectedItem)
            .then(() => {

                setItems(
                    items.map(item =>
                        item.id === selectedItem.id
                            ? selectedItem
                            : item
                    )
                );

                setShowModal(false);

            })
            .catch(error => console.error(error));
    };

    return (
        <div style={{ padding: "20px" }}>

            <h1>🥫 Pantry Items</h1>

            <input
                type="text"
                placeholder="Search item..."
                value={search}
                onChange={(e) => setSearch(e.target.value)}
                style={{
                    padding: "10px",
                    marginBottom: "20px",
                    width: "300px"
                }}
            />

            <select
                value={category}
                onChange={(e) => setCategory(e.target.value)}
                style={{
                    padding: "10px",
                    marginLeft: "10px"
                }}
            >
                <option value="All">All</option>
                <option value="Dairy">Dairy</option>
                <option value="Vegetable">Vegetable</option>
                <option value="Fruit">Fruit</option>
                <option value="Grains">Grains</option>
                <option value="Protein">Protein</option>
            </select>

            <table border="1" cellPadding="10" style={{ marginTop: "20px" }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Quantity</th>
                        <th>Expiry Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    {
                        items
                            .filter(item =>
                                item.name.toLowerCase().includes(search.toLowerCase())
                            )
                            .filter(item =>
                                category === "All" ||
                                item.category === category
                            )
                            .map(item => (
                                <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.name}</td>
                                    <td>{item.category}</td>
                                    <td>{item.quantity}</td>
                                    <td>{item.expiryDate}</td>

                                    <td>
                                        <button
                                            onClick={() => openEditModal(item)}
                                        >
                                            Edit
                                        </button>

                                        <button
                                            onClick={() => deleteItem(item.id)}
                                            style={{ marginLeft: "10px" }}
                                        >
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                            ))
                    }
                </tbody>
            </table>

            {
                showModal && selectedItem && (

                    <div
                        style={{
                            position: "fixed",
                            top: 0,
                            left: 0,
                            width: "100%",
                            height: "100%",
                            backgroundColor: "rgba(0,0,0,0.5)"
                        }}
                    >

                        <div
                            style={{
                                backgroundColor: "white",
                                width: "400px",
                                margin: "100px auto",
                                padding: "20px",
                                borderRadius: "10px"
                            }}
                        >

                            <h2>Edit Pantry Item</h2>

                            <input
                                type="text"
                                value={selectedItem.name}
                                onChange={(e) =>
                                    setSelectedItem({
                                        ...selectedItem,
                                        name: e.target.value
                                    })
                                }
                                style={{
                                    width: "100%",
                                    padding: "10px",
                                    marginBottom: "10px"
                                }}
                            />

                            <input
                                type="number"
                                value={selectedItem.quantity}
                                onChange={(e) =>
                                    setSelectedItem({
                                        ...selectedItem,
                                        quantity: Number(e.target.value)
                                    })
                                }
                                style={{
                                    width: "100%",
                                    padding: "10px",
                                    marginBottom: "10px"
                                }}
                            />

                            <button onClick={updateItem}>
                                Save
                            </button>

                            <button
                                onClick={() => setShowModal(false)}
                                style={{ marginLeft: "10px" }}
                            >
                                Cancel
                            </button>

                        </div>

                    </div>

                )
            }

        </div>
    );
}

export default PantryItems;