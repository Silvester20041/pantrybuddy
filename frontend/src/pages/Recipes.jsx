import { useEffect, useState } from "react";
import api from "../services/api";

function Recipes() {

    const [recipes, setRecipes] = useState([]);

    useEffect(() => {

        api.get("/recipes/suggestions")
            .then(response => {
                setRecipes(response.data);
            })
            .catch(error => {
                console.error(error);
            });

    }, []);

    return (
        <div style={{ padding: "20px" }}>
            <h1>🍲 AI Recipe Suggestions</h1>

            {recipes.length === 0 ? (
                <p>Loading...</p>
            ) : (
                recipes.map((recipe, index) => (
                    <div
                        key={index}
                        style={{
                            border: "1px solid #ccc",
                            padding: "15px",
                            marginBottom: "10px",
                            borderRadius: "8px"
                        }}
                    >
                        {recipe}
                    </div>
                ))
            )}
        </div>
    );
}

export default Recipes;