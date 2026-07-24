import { useState } from "react";
import toast from "react-hot-toast";
import api from "../services/api";

function UploadCard({ onUploadSuccess }) {
  const [selectedFile, setSelectedFile] = useState(null);
  const [uploading, setUploading] = useState(false);
  const [progress, setProgress] = useState(0);

  const handleChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleUpload = async () => {
    if (!selectedFile) {
      toast.error("Please select a PCAP file.");
      return;
    }

    const formData = new FormData();
    formData.append("file", selectedFile);

    try {
      setUploading(true);
      setProgress(0);

      await api.post("/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },

        onUploadProgress: (event) => {
          const percent = Math.round((event.loaded * 100) / event.total);

          setProgress(percent);
        },
      });

      toast.success("Analysis Completed Successfully!");

      if (onUploadSuccess) {
        onUploadSuccess(selectedFile.name);
      }

      setSelectedFile(null);
    } catch (error) {
      console.error(error);

      toast.error("Upload Failed");
    } finally {
      setUploading(false);
      setProgress(0);
    }
  };

  return (
    <div className="bg-slate-900 rounded-2xl border border-slate-700 p-8 mb-8">
      <h2 className="text-2xl font-bold text-white mb-6">Upload PCAP File</h2>

      <label
        className="
        flex
        flex-col
        items-center
        justify-center
        border-2
        border-dashed
        border-cyan-500
        rounded-xl
        p-10
        cursor-pointer
        hover:bg-slate-800
        transition"
      >
        <div className="text-5xl mb-3">📁</div>

        <p className="text-white text-lg font-semibold">
          Drag & Drop your .pcap file
        </p>

        <p className="text-slate-400 mt-2">or click here to browse</p>

        <input
          type="file"
          accept=".pcap"
          onChange={handleChange}
          className="hidden"
        />
      </label>

      {selectedFile && (
        <div className="mt-5 text-green-400">
          Selected File: <strong>{selectedFile.name}</strong>
        </div>
      )}

      {uploading && (
        <div className="mt-5">
          <div className="flex justify-between text-white mb-2">
            <span>Uploading...</span>
            <span>{progress}%</span>
          </div>

          <div className="w-full h-3 bg-slate-700 rounded-full overflow-hidden">
            <div
              className="bg-cyan-500 h-3 transition-all duration-300"
              style={{ width: `${progress}%` }}
            />
          </div>
        </div>
      )}

      <button
        onClick={handleUpload}
        disabled={uploading}
        className="
        mt-6
        w-full
        bg-cyan-600
        hover:bg-cyan-700
        disabled:bg-slate-700
        text-white
        px-8
        py-3
        rounded-xl
        font-semibold
        transition"
      >
        {uploading ? "Analyzing..." : "Analyze Network"}
      </button>
    </div>
  );
}

export default UploadCard;
