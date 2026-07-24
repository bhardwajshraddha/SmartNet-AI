function UploadHistory({ fileName }) {
  return (
    <div className="bg-slate-900 border border-slate-700 rounded-2xl p-6 mt-8">
      <h2 className="text-2xl font-bold text-white mb-5">Upload History</h2>

      {fileName ? (
        <div className="space-y-3">
          <p className="text-white">
            <strong>Last Uploaded:</strong>
          </p>

          <div className="bg-slate-800 rounded-xl p-4">
            <p className="text-cyan-400 font-semibold">{fileName}</p>

            <p className="text-green-400 mt-2">✔ Successfully Analyzed</p>
          </div>
        </div>
      ) : (
        <p className="text-slate-400">No file uploaded yet.</p>
      )}
    </div>
  );
}

export default UploadHistory;
