import { useEffect, useState } from "react";
import StatCard from "./StatCard";
import { getDashboardSummary } from "../services/dashboardService";

function DashboardCards() {
  const [summary, setSummary] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadSummary();
  }, []);

  async function loadSummary() {
    try {
      const data = await getDashboardSummary();
      setSummary(data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  }

  if (loading || !summary) {
    return <div className="text-white text-xl">Loading Dashboard...</div>;
  }

  return (
    <div className="grid grid-cols-4 gap-6">
      <StatCard
        title="Total Packets"
        value={summary.totalPackets}
        color="text-cyan-400"
      />

      <StatCard
        title="Total Flows"
        value={summary.totalFlows}
        color="text-green-400"
      />

      <StatCard
        title="Threats"
        value={summary.totalThreats}
        color="text-red-400"
      />

      <StatCard
        title="Avg Packet Size"
        value={`${summary.averagePacketSize.toFixed(2)} B`}
        color="text-yellow-400"
      />
    </div>
  );
}

export default DashboardCards;
