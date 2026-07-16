import { useState } from "react";
import api from "../services/api";

function AddItem() {

    const [item, setItem] = useState({
        name: "",
        category: "Vegetable",
        quantity: 1,
        expiryDate: ""
    });

    const handleChange = (e) => {
        setItem({
            ...item,
            [e.target.name]: e.target.value
        });
    };

    const saveItem = () => {

        api.post("/items", item)
            .then(() => {

                alert("Item Added Successfully!");

                setItem({
                    name: "",
                    category: "Vegetable",
                    quantity: 1,
                    expiryDate: ""
                });

            })
            .catch(err => console.log(err));

    };

    return (
        <div style={{ padding: "20px" }}>

            <h1>➕ Add Pantry Item</h1>

            <br/>

            <input
                name="name"
                placeholder="Item Name"
                value={item.name}
                onChange={handleChange}
            />

            <br/><br/>

            <select
                name="category"
                value={item.category}
                onChange={handleChange}
            >
                <option>Vegetable</option>
                <option>Dairy</option>
                <option>Fruit</option>
                <option>Grains</option>
                <option>Protein</option>
            </select>

            <br/><br/>

            <input
                type="number"
                name="quantity"
                value={item.quantity}
                onChange={handleChange}
            />

            <br/><br/>

            <input
                type="date"
                name="expiryDate"
                value={item.expiryDate}
                onChange={handleChange}
            />

            <br/><br/>

            <button onClick={saveItem}>
                Save Item
            </button>

        </div>
    );
}

export default AddItem;