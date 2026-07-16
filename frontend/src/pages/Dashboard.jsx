import { useEffect, useState } from "react";
import api from "../services/api";
import "../styles/Dashboard.css";
import CategoryChart from "../components/CategoryChart";

function Dashboard() {

  const [data, setData] = useState(null);

  useEffect(() => {
    console.log("Dashboard Loaded");
    api.get("/dashboard")
      .then(res => setData(res.data))
      .catch(err => console.log(err));
  }, []);

  if (!data) {
    return <h2>Loading...</h2>;
  }

  return (
  <div className="dashboard-container">
    <h1>🥫 Pantry Buddy Dashboard</h1>

    <div className="card-grid">

      <div className="card">
        <h2>Total Items</h2>
        <h1>{data.totalItems}</h1>
      </div>

      <div className="card">
        
        <h2>🥬 Vegetables</h2>

        <h1>{data.vegetableItems}</h1>
      </div>

      <div className="card">
        
        <h2>🥛 Dairy</h2>

        <h1>{data.dairyItems}</h1>
      </div>

      <div className="card">
        
        <h2>🌾 Grains</h2>

        <h1>{data.grainItems}</h1>
      </div>

      <div className="card">
        
        <h2>🍎 Fruits</h2>

        <h1>{data.fruitItems}</h1>
      </div>

      <div className="card">
        
        <h2>⚠ Expiring Soon</h2>

        <h1>{data.expiringSoon}</h1>
      </div>

      <div className="card">
        
        <h2>🥩 Protein</h2>
        <h1>{data.proteinItems}</h1>
      </div>

    </div>
    <div
  style={{
    marginTop: "40px",
    display: "flex",
    justifyContent: "center"
  }}
>
  <CategoryChart data={data} />
</div>
<div
  style={{
    marginTop: "40px",
    display: "grid",
    gridTemplateColumns: "repeat(2,1fr)",
    gap: "20px"
  }}
>

  <div className="card">
    <h2>Inventory Health</h2>
    <progress
      value={data.totalItems - data.expiringSoon}
      max={data.totalItems}
      style={{ width: "100%" }}
    />
    <p>
      {data.totalItems - data.expiringSoon} Healthy Items
    </p>
  </div>

  <div className="card">
    <h2>Expiring Products</h2>
    <progress
      value={data.expiringSoon}
      max={data.totalItems}
      style={{ width: "100%" }}
    />
    <p>{data.expiringSoon} Need Attention</p>
  </div>

</div>
<div
  className="card"
  style={{ marginTop: "30px" }}
>

<h2>Today's Summary</h2>

<p>🥫 Total Products : {data.totalItems}</p>

<p>⚠ Expiring Soon : {data.expiringSoon}</p>

<p>🥬 Vegetables : {data.vegetableItems}</p>

<p>🥛 Dairy : {data.dairyItems}</p>

<p>🌾 Grains : {data.grainItems}</p>

</div>
  </div>
);
}

export default Dashboard;   