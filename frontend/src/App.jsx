import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

import Dashboard from "./pages/Dashboard";
import PantryItems from "./pages/PantryItems";
import Recipes from "./pages/Recipes";
import UploadReceipt from "./pages/UploadReceipt";
import ExpiringSoon from "./pages/ExpiringSoon";
import LowStock from "./pages/LowStock";
import Sidebar from "./components/Sidebar";
import AddItem from "./pages/AddItem";

function App() {
  return (
    <BrowserRouter>

     <div
  style={{
    display: "flex"
  }}
>

  <Sidebar />

  <div
    style={{
      flex: 1,
      padding: "20px",
      background: "#f5f7fa",
      minHeight: "100vh"
    }}
  >

    <Routes>
      <Route path="/" element={<Dashboard />} />
      <Route path="/items" element={<PantryItems />} />
      <Route path="/recipes" element={<Recipes />} />
      <Route path="/upload" element={<UploadReceipt />} />
      <Route path="/expiring" element={<ExpiringSoon />} />
      <Route path="/low-stock" element={<LowStock />} />
      <Route path="/add-item" element={<AddItem />} />
    </Routes>

  </div>

</div>

    </BrowserRouter>
  );
}

export default App;