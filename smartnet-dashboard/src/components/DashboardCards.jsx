import SummaryCard from "./SummaryCard";

function DashboardCards({ summary }) {
  if (!summary) {
    return (
      <div className="text-white text-center mt-10">Loading Dashboard...</div>
    );
  }

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-6">
      <SummaryCard title="Total Packets" value={summary.totalPackets} />

      <SummaryCard title="Total Flows" value={summary.totalFlows} />

      <SummaryCard title="TCP Packets" value={summary.tcpPackets} />

      <SummaryCard title="UDP Packets" value={summary.udpPackets} />

      <SummaryCard title="HTTP Packets" value={summary.httpPackets} />

      <SummaryCard title="Threats" value={summary.totalThreats} />
    </div>
  );
}

export default DashboardCards;
