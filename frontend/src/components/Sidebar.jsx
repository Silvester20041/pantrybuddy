import { Link } from "react-router-dom";

function Sidebar() {
  return (
    <div
      style={{
        width: "220px",
        height: "100vh",
        background: "#2E7D32",
        color: "white",
        padding: "20px"
      }}
    >
      <h2>🥫 Pantry Buddy</h2>

      <div style={{ marginTop: "30px" }}>
        <p><Link to="/" style={{ color: "white" }}>📊 Dashboard</Link></p>

        <p><Link to="/items" style={{ color: "white" }}>🥫 Pantry Items</Link></p>

        <p><Link to="/recipes" style={{ color: "white" }}>🍲 Recipes</Link></p>

        <p><Link to="/upload" style={{ color: "white" }}>📸 Upload Receipt</Link></p>

        <p><Link to="/low-stock" style={{ color: "white" }}>⚠ Low Stock</Link></p>
        <p><Link to="/add-item" style={{ color: "white" }}>➕ Add Item</Link></p>
      </div>
    </div>
  );
}

export default Sidebar;