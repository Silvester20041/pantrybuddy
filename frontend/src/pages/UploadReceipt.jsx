import { useState } from "react";
import api from "../services/api";

function UploadReceipt() {

    const [file, setFile] = useState(null);
    const [message, setMessage] = useState("");
    const [detectedItems, setDetectedItems] = useState([]);

    const handleUpload = async () => {

        if (!file) {
            alert("Select a file first");
            return;
        }

        const formData = new FormData();
        formData.append("file", file);

        try {

            const response = await api.post(
                "/receipts/process",
                formData,
                {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                }
            );

            setMessage("Upload Successful!");
            setDetectedItems(response.data);
            console.log(response.data);

        } catch (error) {
            console.error(error);
            setMessage("Upload Failed");
        }
    };

    return (
        <div style={{ padding: "20px" }}>

            <h1>📸 Upload Grocery Receipt</h1>

            <input
                type="file"
                onChange={(e) => setFile(e.target.files[0])}
            />

            <br /><br />

            <button onClick={handleUpload}>
                Upload
            </button>

            <p>{message}</p>
            <h2>Detected Items</h2>

<ul>
{
 detectedItems.map((item,index)=>(
   <li key={index}>
      {item.name}
   </li>
 ))
}
</ul>

        </div>
    );
}

export default UploadReceipt;