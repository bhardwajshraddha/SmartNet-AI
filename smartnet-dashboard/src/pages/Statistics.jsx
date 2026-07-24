import { useEffect, useState } from "react";

import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";
import DashboardCards from "../components/DashboardCards";
import ProtocolChart from "../components/ProtocolChart";

import { getDashboardSummary } from "../services/dashboardService";

function Statistics() {
  const [summary, setSummary] = useState(null);

  const loadData = async () => {
    try {
      const data = await getDashboardSummary();
      setSummary(data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  return (
    <div className="flex min-h-screen bg-slate-950">
      <Sidebar />

      <div className="flex-1 flex flex-col">
        <Topbar />

        <main className="p-8">
          <div className="mb-8">
            <h1 className="text-4xl font-bold text-white">Statistics</h1>

            <p className="text-slate-400 mt-2">
              Network traffic statistics and protocol analysis.
            </p>
          </div>

          <DashboardCards summary={summary} />

          <div className="mt-8">
            <ProtocolChart />
          </div>
        </main>
      </div>
    </div>
  );
}

export default Statistics;
