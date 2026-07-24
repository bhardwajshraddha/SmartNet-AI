import { useMemo, useState } from "react";

function FlowTable({ flows = [] }) {
  const [selectedFlow, setSelectedFlow] = useState(null);
  const [search, setSearch] = useState("");

  const filteredFlows = useMemo(() => {
    const text = search.toLowerCase();

    return flows.filter((flow) => {
      return (
        flow.flowKey.sourceIp.toLowerCase().includes(text) ||
        flow.flowKey.destinationIp.toLowerCase().includes(text) ||
        flow.flowKey.protocol.toLowerCase().includes(text)
      );
    });
  }, [flows, search]);

  return (
    <>
      <div className="bg-slate-900 rounded-2xl shadow-lg border border-slate-700 p-6 mt-8 overflow-x-auto">
        <h2 className="text-2xl font-bold text-white mb-5">Network Flows</h2>

        <input
          type="text"
          placeholder="Search by Source IP, Destination IP or Protocol..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="w-full mb-5 p-3 rounded-xl bg-slate-800 border border-slate-700 text-white outline-none focus:border-cyan-400"
        />

        {filteredFlows.length === 0 ? (
          <div className="text-white text-center py-10">
            No Flow Data Available
          </div>
        ) : (
          <table className="w-full text-left">
            <thead className="border-b border-slate-700 text-slate-300">
              <tr>
                <th className="py-3">Source IP</th>
                <th>Destination IP</th>
                <th>Protocol</th>
                <th>Packets</th>
                <th>Bytes</th>
              </tr>
            </thead>

            <tbody>
              {filteredFlows.map((flow, index) => (
                <tr
                  key={index}
                  onClick={() => setSelectedFlow(flow)}
                  className="border-b border-slate-800 hover:bg-slate-800 cursor-pointer transition"
                >
                  <td className="py-3 text-cyan-400">
                    {flow.flowKey.sourceIp}
                  </td>

                  <td className="text-white">{flow.flowKey.destinationIp}</td>

                  <td className="text-green-400">{flow.flowKey.protocol}</td>

                  <td className="text-white">{flow.packetCount}</td>

                  <td className="text-white">{flow.totalBytes}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

      {selectedFlow && (
        <div className="fixed inset-0 bg-black/70 flex justify-center items-center z-50">
          <div className="bg-slate-900 border border-slate-700 rounded-2xl p-8 w-[520px] max-w-[95%]">
            <h2 className="text-2xl font-bold text-cyan-400 mb-6">
              Flow Details
            </h2>

            <div className="space-y-3 text-white">
              <p>
                <strong>Source IP:</strong> {selectedFlow.flowKey.sourceIp}
              </p>
              <p>
                <strong>Destination IP:</strong>{" "}
                {selectedFlow.flowKey.destinationIp}
              </p>
              <p>
                <strong>Source Port:</strong> {selectedFlow.flowKey.sourcePort}
              </p>
              <p>
                <strong>Destination Port:</strong>{" "}
                {selectedFlow.flowKey.destinationPort}
              </p>
              <p>
                <strong>Protocol:</strong> {selectedFlow.flowKey.protocol}
              </p>
              <p>
                <strong>Packets:</strong> {selectedFlow.packetCount}
              </p>
              <p>
                <strong>Total Bytes:</strong> {selectedFlow.totalBytes}
              </p>
              <p>
                <strong>Duration:</strong> {selectedFlow.duration} sec
              </p>
              <p>
                <strong>Packets / Second:</strong>{" "}
                {selectedFlow.packetsPerSecond.toFixed(2)}
              </p>
              <p>
                <strong>Average Packet Size:</strong>{" "}
                {selectedFlow.averagePacketSize.toFixed(2)} Bytes
              </p>
            </div>

            <button
              onClick={() => setSelectedFlow(null)}
              className="mt-8 w-full bg-cyan-600 hover:bg-cyan-700 text-white py-3 rounded-xl transition"
            >
              Close
            </button>
          </div>
        </div>
      )}
    </>
  );
}

export default FlowTable;
