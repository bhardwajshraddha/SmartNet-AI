import { useState } from "react";
import api from "../services/api";

function UploadCard({ onUploadSuccess }) {
  const [selectedFile, setSelectedFile] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    if (e.target.files.length > 0) {
      setSelectedFile(e.target.files[0]);
    }
  };

  const handleUpload = async () => {
    if (!selectedFile) {
      alert("Please select a PCAP or PCAPNG file.");
      return;
    }

    setLoading(true);

    const formData = new FormData();
    formData.append("file", selectedFile);

    try {
      const response = await api.post("/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });

      console.log("Upload Response:", response.data);

      alert("Analysis Completed Successfully!");

      if (onUploadSuccess) {
        onUploadSuccess();
      }
    } catch (error) {
      console.error("Upload Error:", error);

      alert("Upload Failed!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="bg-slate-900 rounded-2xl border border-slate-700 shadow-lg p-8 mb-8">
      <h2 className="text-2xl font-bold text-white mb-6">Upload PCAP File</h2>

      <label
        className="
          flex
          flex-col
          items-center
          justify-center
          border-2
          border-dashed
          border-sky-500
          rounded-xl
          p-10
          cursor-pointer
          hover:bg-slate-800
          transition
        "
      >
        <div className="text-5xl mb-3">📁</div>

        <p className="text-lg font-semibold text-white">
          Drag & Drop your PCAP file
        </p>

        <p className="text-slate-400 mt-2">or click here to browse</p>

        <input
          type="file"
          accept=".pcap,.pcapng"
          onChange={handleChange}
          className="hidden"
        />
      </label>

      {selectedFile && (
        <div className="mt-5 rounded-lg bg-slate-800 p-4">
          <p className="text-green-400 font-medium">Selected File:</p>

          <p className="text-white break-all">{selectedFile.name}</p>
        </div>
      )}

      <button
        onClick={handleUpload}
        disabled={loading}
        className="
          mt-6
          w-full
          bg-sky-600
          hover:bg-sky-700
          disabled:bg-slate-600
          disabled:cursor-not-allowed
          text-white
          py-3
          rounded-xl
          font-semibold
          transition
        "
      >
        {loading ? "Analyzing..." : "Analyze Network"}
      </button>
    </div>
  );
}

export default UploadCard;
