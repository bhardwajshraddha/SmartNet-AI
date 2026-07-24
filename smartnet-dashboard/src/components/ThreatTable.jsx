function ThreatTable({ totalThreats }) {
  const hasThreats = totalThreats > 0;

  return (
    <div className="bg-slate-900 rounded-2xl shadow-lg border border-slate-700 p-6 mt-8">
      <h2 className="text-2xl font-bold text-white mb-6">Threat Analysis</h2>

      {!hasThreats ? (
        <div className="flex items-center justify-center py-10">
          <div className="text-center">
            <div className="text-6xl mb-4">🛡️</div>

            <h3 className="text-2xl font-bold text-green-400">
              No Threats Detected
            </h3>

            <p className="text-slate-400 mt-3">
              All analyzed network traffic appears to be safe.
            </p>
          </div>
        </div>
      ) : (
        <div className="text-white">Threats Found: {totalThreats}</div>
      )}
    </div>
  );
}

export default ThreatTable;
